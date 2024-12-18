package co.simplon.dnd_heroic_battle_api.dtos.user.validators;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = ValidPasswordValidator.class)
public @interface ValidPassword {

    String message() default "Password is not valid";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
