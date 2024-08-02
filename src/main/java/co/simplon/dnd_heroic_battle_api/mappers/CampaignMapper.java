package co.simplon.dnd_heroic_battle_api.mappers;

import java.sql.Timestamp;
import java.util.List;

import co.simplon.dnd_heroic_battle_api.dtos.campaign.CampaignCreate;
import co.simplon.dnd_heroic_battle_api.dtos.campaign.CampaignUpdate;
import co.simplon.dnd_heroic_battle_api.dtos.campaign.CampaignView;
import co.simplon.dnd_heroic_battle_api.entities.Campaign;

public class CampaignMapper {

    public static List<CampaignView> entitiesToCampaignViews(List<Campaign> campaigns) {
	return campaigns.stream().map(c -> new CampaignView(c.getCampaignName(), c.getCreationDate())).toList();
    }

    public static Campaign campaignCreateToEntity(CampaignCreate campaign) {
	return Campaign.builder().campaignName(campaign.campaignName())
		.creationDate(new Timestamp(System.currentTimeMillis())).build();
    }

    public static CampaignView entityToCampaignView(Campaign campaign) {
	return new CampaignView(campaign.getCampaignName(), campaign.getCreationDate());
    }

    public static Campaign campaignUpdateToEntity(CampaignUpdate input) {
	return Campaign.builder().id(input.id()).campaignName(input.campaignName()).build();
    }
}
