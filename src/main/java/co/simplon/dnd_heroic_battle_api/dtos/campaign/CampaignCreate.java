package co.simplon.dnd_heroic_battle_api.dtos.campaign;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CampaignCreate(@NotBlank @Size(min = 5) String campaignName) {

}
