package co.simplon.dnd_heroic_battle_api.dtos.user.validators;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Constraint(validatedBy = UserExistValidator.class)
public @interface UserExist {

    String message() default "Can't find User";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
