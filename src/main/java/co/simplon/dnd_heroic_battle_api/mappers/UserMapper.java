package co.simplon.dnd_heroic_battle_api.mappers;

import co.simplon.dnd_heroic_battle_api.dtos.user.UserCreateDto;
import co.simplon.dnd_heroic_battle_api.dtos.user.UserView;
import co.simplon.dnd_heroic_battle_api.entities.User;

public final class UserMapper {

    public static User createDtoToEntity(UserCreateDto input) {
        return User.builder()
                .userName(input.userName())
                .userPassword(input.password())
                .email(input.email())
                .build();
    }

    public static UserView entityToUserView(User user) {
        return new UserView(user.getUserName(), user.getEmail());
    }
}
