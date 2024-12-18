package co.simplon.dnd_heroic_battle_api.dtos.user.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;


public class ValidPasswordValidator implements ConstraintValidator<ValidPassword, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value.matches(".*[A-Z].*") && value.matches(".*[a-z].*[a-z].*") && value.matches(".*[0-9].*") && value.matches(".*[!%#$~?&:;^+-].*")) {
            return true;
        }
        return false;
    }
}
