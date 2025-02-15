package co.simplon.dnd_heroic_battle_api.services.Impl;

import co.simplon.dnd_heroic_battle_api.config.JwtProvider;
import co.simplon.dnd_heroic_battle_api.dtos.user.Tokens;
import co.simplon.dnd_heroic_battle_api.dtos.user.UserCreateDto;
import co.simplon.dnd_heroic_battle_api.dtos.user.UserLoginDto;
import co.simplon.dnd_heroic_battle_api.entities.User;
import co.simplon.dnd_heroic_battle_api.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl test;

    @Mock
    private UserRepository repo;

    @Mock
    private PasswordEncoder encoder;

    @Mock
    private JwtProvider jwt;

    @Test
    void create() {
        var input = new UserCreateDto("user name", "email@email.com", "password");
        when(encoder.encode(input.password())).thenReturn("hashed");
        assertDoesNotThrow(() -> test.create(input));
        verify(repo, times(1)).save(User.builder()
                .userName(input.userName()).userPassword("hashed").email(input.email()).build());
    }

    @Test
    void loginWithBadEmail() {
        var input = new UserLoginDto("bad email", "password");
        when(repo.findByEmail(input.email())).thenReturn(Optional.empty());
        assertThrows(BadCredentialsException.class, () -> test.login(input));
    }

    @Test
    void loginWithBadPassword() {
        var input = new UserLoginDto("email", "bad password");
        var user = User.builder().userPassword("good password").build();
        when(repo.findByEmail(input.email()))
                .thenReturn(Optional.of(user));
        when(encoder.matches(input.password(), user.getUserPassword())).thenReturn(false);
        assertThrows(BadCredentialsException.class, () -> test.login(input));
    }

    @Test
    void login() {
        var input = new UserLoginDto("email", "good password");
        var user = User.builder().userId(1L).userName("name").userPassword("good password").build();
        var token = new Tokens("token", "refresh token", 123L);
        when(repo.findByEmail(input.email()))
                .thenReturn(Optional.of(user));
        when(encoder.matches(input.password(), user.getUserPassword())).thenReturn(true);
        when(jwt.generate(user.getUserId().toString())).thenReturn(token);
        var actual = assertDoesNotThrow( () -> test.login(input));
        assertEquals(user.getUserName(), actual.userName());
        assertEquals(user.getEmail(), actual.email());
        assertEquals(token, actual.tokens());
    }

}