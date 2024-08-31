package co.simplon.dnd_heroic_battle_api.dtos.campaign.validators;

import co.simplon.dnd_heroic_battle_api.dtos.campaign.CampaignUpdate;
import co.simplon.dnd_heroic_battle_api.repositories.CampaingRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UniqueCampaignUpdateValidator implements ConstraintValidator<UniqueCampaignUpdate, CampaignUpdate> {

	private final CampaingRepository repo;

	public UniqueCampaignUpdateValidator(CampaingRepository repo) {
		this.repo = repo;
	}

	@Override
	public boolean isValid(CampaignUpdate value, ConstraintValidatorContext context) {
		return !repo.existsByCampaignNameAndIdNot(value.campaignName(), value.campaignId());
	}
}
