package co.simplon.dnd_heroic_battle_api.services;

import co.simplon.dnd_heroic_battle_api.dtos.user.Tokens;
import co.simplon.dnd_heroic_battle_api.dtos.user.UserCreateDto;
import co.simplon.dnd_heroic_battle_api.dtos.user.UserLoginDto;
import co.simplon.dnd_heroic_battle_api.dtos.user.UserView;
import jakarta.security.auth.message.AuthException;

import java.nio.file.AccessDeniedException;

public interface UserService {

    void create( UserCreateDto input);

    UserView login(UserLoginDto input);

	Tokens renewalToken(String token) throws AccessDeniedException, AuthException;
}
