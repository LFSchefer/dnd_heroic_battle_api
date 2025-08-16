package co.simplon.dnd_heroic_battle_api.services.Impl;

import co.simplon.dnd_heroic_battle_api.config.JwtProvider;
import co.simplon.dnd_heroic_battle_api.dtos.user.Tokens;
import co.simplon.dnd_heroic_battle_api.dtos.user.UserCreateDto;
import co.simplon.dnd_heroic_battle_api.dtos.user.UserLoginDto;
import co.simplon.dnd_heroic_battle_api.dtos.user.UserView;
import co.simplon.dnd_heroic_battle_api.entities.User;
import co.simplon.dnd_heroic_battle_api.mappers.UserMapper;
import co.simplon.dnd_heroic_battle_api.repositories.UserRepository;
import co.simplon.dnd_heroic_battle_api.services.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@Service
@Transactional
public class UserServiceImpl implements UserService {


    private final UserRepository repo;
    private final PasswordEncoder encoder;
    private final JwtProvider jwt;
    private final ObjectMapper mapper;

    public UserServiceImpl(UserRepository repo, PasswordEncoder encoder, JwtProvider jwt, ObjectMapper mapper) {
        this.repo = repo;
        this.encoder = encoder;
        this.jwt = jwt;
        this.mapper = mapper;
    }

    @Override
    public void create(UserCreateDto input) {
        String hashPassword = encoder.encode(input.password());
        repo.save(UserMapper.createDtoToEntity(input, hashPassword));
    }

    @Override
    public UserView login(UserLoginDto input) {
        User user = repo.findByEmail(input.email())
                .orElseThrow(() -> new BadCredentialsException("Wrong email or password"));
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
        tokenBody = mapper.readValue(decodedToken, new TypeReference<Map<String, String>>() {
        });
        refreshBody = mapper.readValue(decodedRefresh, new TypeReference<Map<String, String>>() {
        });
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
