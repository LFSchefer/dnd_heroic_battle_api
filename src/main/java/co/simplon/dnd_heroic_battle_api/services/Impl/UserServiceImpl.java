package co.simplon.dnd_heroic_battle_api.services.Impl;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
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
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    private final UserRepository repo;
    private final PasswordEncoder encoder;
    private final JwtProvider jwt;
    private final ObjectMapper mapper;

    @Override
    @Transactional
    public void create(UserCreateDto input) {
    	String hashPassword = encoder.encode(input.password());
       repo.save(UserMapper.createDtoToEntity(input, hashPassword));
    }

    @Override
    @Transactional
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
	public Tokens renewalToken(String token) throws AuthException {
		String[] split = token.split("[.]");
		String base64 = split[1];
		byte[] bytes = Base64.getDecoder().decode(base64.getBytes());
		String decoded = new String(bytes);
		Map<String, String> body = new HashMap<String, String>();
		try {
			body = mapper.readValue(decoded, new TypeReference<Map<String,String>>(){});
			String subject = body.get("sub");
			return jwt.generate(subject);
		} catch (JsonProcessingException e) {
			throw new AuthException("bad token");
		}
	}
}
