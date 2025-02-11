package co.simplon.dnd_heroic_battle_api.services.Impl;

import java.time.Instant;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import co.simplon.dnd_heroic_battle_api.config.JwtProvider;
import co.simplon.dnd_heroic_battle_api.dtos.user.Tokens;
import co.simplon.dnd_heroic_battle_api.dtos.user.UserCreateDto;
import co.simplon.dnd_heroic_battle_api.dtos.user.UserLoginDto;
import co.simplon.dnd_heroic_battle_api.dtos.user.UserView;
import co.simplon.dnd_heroic_battle_api.entities.User;
import co.simplon.dnd_heroic_battle_api.mappers.UserMapper;
import co.simplon.dnd_heroic_battle_api.repositories.UserRepository;
import co.simplon.dnd_heroic_battle_api.services.UserService;
import jakarta.security.auth.message.AuthException;
import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

	@Autowired
    private UserRepository repo;
	@Autowired
    private PasswordEncoder encoder;
	@Autowired
    private JwtProvider jwt;
	@Autowired
    private ObjectMapper mapper;

    @Override
    @Transactional
    public void create(UserCreateDto input) {
    	String hashPassword = encoder.encode(input.password());
       repo.save(UserMapper.createDtoToEntity(input, hashPassword));
    }

    @Override
    public UserView login(UserLoginDto input) {
    	User user = repo.findByEmail(input.email())
    			.orElseThrow( () -> new BadCredentialsException("Wrong email or password"));
    	boolean match = encoder.matches(input.password(), user.getUserPassword());
    	if (match) {
    		Tokens token = jwt.generate(user.getUserId().toString());
			return UserMapper.entityToUserView(user, token);
		}
    	throw new BadCredentialsException("Wrong email or password");
    }

	@Override
	public Tokens renewalToken(Tokens tokens) throws JsonProcessingException {
		String token = tokens.token();
		String refreshToken = tokens.refreshToken();
		String tokenSplit = token.split("[.]")[1];
		String refreshSplit = refreshToken.split("[.]")[1];
		byte[] tokenBytes = Base64.getDecoder().decode(tokenSplit.getBytes());
		byte[] refreshBytes = Base64.getDecoder().decode(refreshSplit.getBytes());
		String decodedToken = new String(tokenBytes);
		String decodedRefresh = new String(refreshBytes);
		Map<String, String> tokenBody = new HashMap<String, String>();
		Map<String, String> refreshBody = new HashMap<String, String>();
		tokenBody = mapper.readValue(decodedToken, new TypeReference<Map<String,String>>(){});
		refreshBody = mapper.readValue(decodedRefresh, new TypeReference<Map<String,String>>(){});
		String tokenRef = tokenBody.get("ref");
		String refreshRef = refreshBody.get("ref");
		long tokenExp = Long.parseLong(tokenBody.get("exp"));
		long refreshExp = Long.parseLong(refreshBody.get("exp"));
		long now = Instant.now().getEpochSecond();
		boolean matchRef = tokenRef.equals(refreshRef);
		if (matchRef && now > tokenExp && now < refreshExp) {
			return jwt.generate(tokenBody.get("sub"));
		}
		throw new BadCredentialsException("bad tokens");
	}
}
