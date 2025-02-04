package co.simplon.dnd_heroic_battle_api.dtos.monsters.validators;

import co.simplon.dnd_heroic_battle_api.dtos.monsters.MonsterCreateDto;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValidHitPointsValidator implements ConstraintValidator<ValidHitPoints, MonsterCreateDto> {

    @Override
    public boolean isValid(MonsterCreateDto value, ConstraintValidatorContext context) {
        return value.currentHitPoints() <= value.maxHitPoints();
    }
}
