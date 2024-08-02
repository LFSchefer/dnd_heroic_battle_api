package co.simplon.dnd_heroic_battle_api.dtos.campaign;

import co.simplon.dnd_heroic_battle_api.dtos.campaign.validators.UniqueCampaignCreate;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@UniqueCampaignCreate
public record CampaignCreate(@NotBlank @Size(min = 5) String campaignName) {

}
