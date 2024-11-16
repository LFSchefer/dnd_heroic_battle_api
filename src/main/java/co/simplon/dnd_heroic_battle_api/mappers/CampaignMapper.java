package co.simplon.dnd_heroic_battle_api.mappers;

import java.util.List;

import co.simplon.dnd_heroic_battle_api.dtos.campaign.CampaignCreate;
import co.simplon.dnd_heroic_battle_api.dtos.campaign.CampaignUpdate;
import co.simplon.dnd_heroic_battle_api.dtos.campaign.CampaignView;
import co.simplon.dnd_heroic_battle_api.entities.Campaign;
import co.simplon.dnd_heroic_battle_api.models.CampaignModel;

public final class CampaignMapper {

	public static List<CampaignView> entitiesToCampaignViews(List<Campaign> campaigns) {
		return campaigns.stream().map(c -> new CampaignView(c.getCampaignName(), c.getCreationDate())).toList();
	}

	public static Campaign campaignCreateToEntity(CampaignCreate campaign) {
		return Campaign.builder().campaignName(campaign.campaignName()).build();
	}

	public static CampaignView entityToCampaignView(Campaign campaign) {
		return new CampaignView(campaign.getCampaignName(), campaign.getCreationDate());
	}

	public static Campaign campaignUpdateToEntity(CampaignUpdate input) {
		return Campaign.builder().campaignId(input.campaignId()).campaignName(input.campaignName()).build();
	}

	public static List<CampaignModel> entitiesToCampaignModel(List<Campaign> campaigns) {
		return campaigns.stream().map(c -> new CampaignModel(c.getId(), c.getCampaignName(), c.getCreationDate())).toList();
	}

	public static CampaignModel entityToCampaignModel(Campaign campaign) {
		return new CampaignModel(campaign.getId(), campaign.getCampaignName(), campaign.getCreationDate());
	}
}
