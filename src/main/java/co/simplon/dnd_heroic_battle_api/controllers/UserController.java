package co.simplon.dnd_heroic_battle_api.controllers;

import co.simplon.dnd_heroic_battle_api.dtos.user.Tokens;
import jakarta.security.auth.message.AuthException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import co.simplon.dnd_heroic_battle_api.dtos.user.UserCreateDto;
import co.simplon.dnd_heroic_battle_api.dtos.user.UserLoginDto;
import co.simplon.dnd_heroic_battle_api.dtos.user.UserView;
import co.simplon.dnd_heroic_battle_api.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.nio.file.AccessDeniedException;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public void create(@Valid @RequestBody UserCreateDto input) {
        userService.create(input);
    }

    @PostMapping("/sign-in")
    @ResponseStatus(code = HttpStatus.OK)
    public UserView get(@Valid @RequestBody UserLoginDto input) {
        return userService.login(input);
    }
    
    @PostMapping("/token-renewal")
    @ResponseStatus(code = HttpStatus.CREATED)
    public Tokens tokenRenewal(@RequestBody String token) throws AccessDeniedException, AuthException {
    	return userService.renewalToken(token);
    }
}
