package co.simplon.dnd_heroic_battle_api.dtos.campaign.validators;

import co.simplon.dnd_heroic_battle_api.dtos.campaign.CampaignCreate;
import co.simplon.dnd_heroic_battle_api.repositories.CampaingRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UniqueCampaignCreateValidator implements ConstraintValidator<UniqueCampaignCreate, CampaignCreate> {

    private final CampaingRepository repo;

    public UniqueCampaignCreateValidator(CampaingRepository repo) {
	this.repo = repo;
    }

    @Override
    public boolean isValid(CampaignCreate value, ConstraintValidatorContext context) {
	return !repo.existsByCampaignName(value.campaignName());
    }
}
