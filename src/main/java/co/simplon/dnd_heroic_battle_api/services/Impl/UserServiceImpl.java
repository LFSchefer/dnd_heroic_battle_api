package co.simplon.dnd_heroic_battle_api.services.Impl;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.simplon.dnd_heroic_battle_api.config.JwtProvider;
import co.simplon.dnd_heroic_battle_api.dtos.user.UserCreateDto;
import co.simplon.dnd_heroic_battle_api.dtos.user.UserLoginDto;
import co.simplon.dnd_heroic_battle_api.dtos.user.UserView;
import co.simplon.dnd_heroic_battle_api.entities.User;
import co.simplon.dnd_heroic_battle_api.mappers.UserMapper;
import co.simplon.dnd_heroic_battle_api.repositories.UserRepository;
import co.simplon.dnd_heroic_battle_api.services.UserService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    private final UserRepository repo;
    
    private final PasswordEncoder encoder;
    
    private final JwtProvider jwt;

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
    		String token = jwt.create(user.getUserId().toString());
			return UserMapper.entityToUserView(user, token);
		}
    	throw new BadCredentialsException("Wrong email or password");
    }
}
