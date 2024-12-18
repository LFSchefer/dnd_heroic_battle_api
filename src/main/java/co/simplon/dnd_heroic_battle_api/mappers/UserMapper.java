package co.simplon.dnd_heroic_battle_api.mappers;

import co.simplon.dnd_heroic_battle_api.dtos.user.Tokens;
import co.simplon.dnd_heroic_battle_api.dtos.user.UserCreateDto;
import co.simplon.dnd_heroic_battle_api.dtos.user.UserView;
import co.simplon.dnd_heroic_battle_api.entities.User;

public final class UserMapper {

    public static User createDtoToEntity(UserCreateDto input, String hashPassword) {
        return User.builder()
                .userName(input.userName())
                .userPassword(hashPassword)
                .email(input.email())
                .build();
    }

    public static UserView entityToUserView(User user, Tokens token) {
        return new UserView(user.getUserName(), user.getEmail(), token);
    }
}
