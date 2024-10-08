package co.simplon.dnd_heroic_battle_api.dtos.battle.validators;

import co.simplon.dnd_heroic_battle_api.dtos.battle.BattleCreate;
import co.simplon.dnd_heroic_battle_api.repositories.BattleRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UniqueBattleNameCreateValidator implements ConstraintValidator<UniqueBattleNameCreate, BattleCreate> {

    private final BattleRepository repo;

    public UniqueBattleNameCreateValidator(BattleRepository repo) {
	this.repo = repo;
    }

    @Override
    public boolean isValid(BattleCreate input, ConstraintValidatorContext context) {
	return !repo.existsByBattleNameAndCampaignId(input.battleName(), input.campaignId());
    }

}
