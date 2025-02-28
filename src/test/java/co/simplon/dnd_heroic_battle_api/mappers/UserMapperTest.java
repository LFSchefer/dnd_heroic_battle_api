package co.simplon.dnd_heroic_battle_api.mappers;

import co.simplon.dnd_heroic_battle_api.dtos.user.Tokens;
import co.simplon.dnd_heroic_battle_api.dtos.user.UserCreateDto;
import co.simplon.dnd_heroic_battle_api.entities.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;


class UserMapperTest {

    @Test
    void createDtoToEntity() {
        var userCreateDto = new UserCreateDto("name", "email", "password");
        var hashPassword = "hashPassword";
        var actual = assertDoesNotThrow(() -> UserMapper.createDtoToEntity(userCreateDto, hashPassword));
        assertEquals(userCreateDto.userName(), actual.getUserName());
        assertEquals(userCreateDto.email(), actual.getEmail());
        assertEquals(hashPassword, actual.getUserPassword());
    }

    @Test
    void entityToUserView() {
        var user = User.builder().userName("name").email("email").build();
        var token = new Tokens("token", "refresh token", 1L);
        var actual = assertDoesNotThrow( () -> UserMapper.entityToUserView(user, token));
        assertEquals(user.getUserName(), actual.userName());
        assertEquals(user.getEmail(), actual.email());
        assertEquals(token, actual.tokens());
    }
}