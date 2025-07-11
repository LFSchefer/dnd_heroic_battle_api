package co.simplon.dnd_heroic_battle_api.controllers;

import co.simplon.dnd_heroic_battle_api.dtos.user.Tokens;
import co.simplon.dnd_heroic_battle_api.dtos.user.UserCreateDto;
import co.simplon.dnd_heroic_battle_api.dtos.user.UserLoginDto;
import co.simplon.dnd_heroic_battle_api.services.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.security.auth.message.AuthException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.nio.file.AccessDeniedException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @InjectMocks
    private UserController test;

    @Mock
    private UserService service;

    @Test
    void create() {
        var dto = new UserCreateDto("good name","good@email.com","good password");
        assertDoesNotThrow(() -> test.create(dto));
        verify(service, times(1)).create(dto);
    }

    @Test
    void get() {
        var dto = new UserLoginDto("email@mail.com", "password");
        assertDoesNotThrow(() -> test.get(dto));
        verify(service, times(1)).login(dto);
    }

    @Test
    void tokenRenewal() throws AccessDeniedException, AuthException, JsonProcessingException {
        var token = new Tokens("token", "refresh token", 123L);
        assertDoesNotThrow(() -> test.tokenRenewal(token));
        verify(service, times(1)).renewalToken(token);
    }


}