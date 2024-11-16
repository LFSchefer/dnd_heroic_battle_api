package co.simplon.dnd_heroic_battle_api.services.Impl;

import co.simplon.dnd_heroic_battle_api.dtos.user.UserCreateDto;
import co.simplon.dnd_heroic_battle_api.dtos.user.UserLoginDto;
import co.simplon.dnd_heroic_battle_api.dtos.user.UserView;
import co.simplon.dnd_heroic_battle_api.mappers.UserMapper;
import co.simplon.dnd_heroic_battle_api.repositories.UserRepository;
import co.simplon.dnd_heroic_battle_api.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repo;

    @Override
    public void create(UserCreateDto input) {
       repo.save(UserMapper.createDtoToEntity(input));
    }

    @Override
    public UserView login(UserLoginDto input) {
        return UserMapper.entityToUserView(repo.findByEmailAndUserPassword(input.email(),input.password()));
    }
}
