package co.simplon.dnd_heroic_battle_api.services;

import co.simplon.dnd_heroic_battle_api.dtos.user.UserCreateDto;
import co.simplon.dnd_heroic_battle_api.dtos.user.UserLoginDto;
import co.simplon.dnd_heroic_battle_api.dtos.user.UserView;

public interface UserService {

    void create( UserCreateDto input);

    UserView login(UserLoginDto input);
}
