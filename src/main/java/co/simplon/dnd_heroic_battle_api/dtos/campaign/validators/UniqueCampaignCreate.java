package co.simplon.dnd_heroic_battle_api.dtos.campaign.validators;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Constraint(validatedBy = UniqueCampaignCreateValidator.class)
public @interface UniqueCampaignCreate {

    String message() default "Campaign name must be unique";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
