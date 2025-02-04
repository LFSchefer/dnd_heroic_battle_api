package co.simplon.dnd_heroic_battle_api.dtos.monsters.validators;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Constraint(validatedBy = ValidHitPointsValidator.class)
public @interface ValidHitPoints {

    String message() default "Current hit points must be <= max hit points";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
