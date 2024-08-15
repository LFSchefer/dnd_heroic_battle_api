package co.simplon.dnd_heroic_battle_api.dtos.battle.validators;

import co.simplon.dnd_heroic_battle_api.dtos.battle.BattleUpdate;
import co.simplon.dnd_heroic_battle_api.repositories.BattleRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UniqueBattleNameUpdateValidator implements ConstraintValidator<UniqueBattleNameUpdate, BattleUpdate> {

	private final BattleRepository repo;

	public UniqueBattleNameUpdateValidator(BattleRepository repo) {
		this.repo = repo;
	}

	@Override
	public boolean isValid(BattleUpdate value, ConstraintValidatorContext context) {
		return repo.battleNameNotExistForCampaign(value.id(), value.battleName());
	}

}
