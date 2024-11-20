package co.simplon.dnd_heroic_battle_api.dtos.user.validators;

import co.simplon.dnd_heroic_battle_api.dtos.user.UserLoginDto;
import co.simplon.dnd_heroic_battle_api.repositories.UserRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UserExistValidator implements ConstraintValidator<UserExist, UserLoginDto> {

    private final UserRepository repo;

    public UserExistValidator(UserRepository repo) {
        this.repo = repo;
    }

    @Override
    public boolean isValid(UserLoginDto value, ConstraintValidatorContext context) {
        return repo.existsByEmail(value.email());
    }
}
