package co.simplon.dnd_heroic_battle_api.dtos.user;

import co.simplon.dnd_heroic_battle_api.dtos.user.validators.UserExist;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

@UserExist
public record UserLoginDto(@Email String email, @Size(min = 5, max = 255) String password) {
}
