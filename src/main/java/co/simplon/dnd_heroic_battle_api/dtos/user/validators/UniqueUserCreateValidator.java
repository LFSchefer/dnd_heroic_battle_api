package co.simplon.dnd_heroic_battle_api.dtos.user.validators;

import co.simplon.dnd_heroic_battle_api.dtos.user.UserCreateDto;
import co.simplon.dnd_heroic_battle_api.repositories.UserRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UniqueUserCreateValidator implements ConstraintValidator<UniqueUserCreate, UserCreateDto> {

    private final UserRepository repo;

    public UniqueUserCreateValidator(UserRepository repo) {
        this.repo = repo;
    }

    @Override
    public boolean isValid(UserCreateDto value, ConstraintValidatorContext context) {
        return !repo.existsByUserNameOrEmail(value.userName(),value.email());
    }
}
