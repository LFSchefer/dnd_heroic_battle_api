package co.simplon.dnd_heroic_battle_api.dtos.campaign;

import co.simplon.dnd_heroic_battle_api.dtos.campaign.validators.UniqueCampaignUpdate;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

@UniqueCampaignUpdate
public record CampaignUpdate(@Positive long campaignId, @NotBlank @Size(min = 5, max = 200) String campaignName) {

}
