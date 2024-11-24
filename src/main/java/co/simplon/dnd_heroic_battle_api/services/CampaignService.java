package co.simplon.dnd_heroic_battle_api.services;

import java.util.List;

import co.simplon.dnd_heroic_battle_api.dtos.campaign.CampaignCreate;
import co.simplon.dnd_heroic_battle_api.dtos.campaign.CampaignUpdate;
import co.simplon.dnd_heroic_battle_api.models.CampaignModel;

public interface CampaignService {

	void create(CampaignCreate input, String token);

	List<CampaignModel> getAllByUserId(String token);

	void deleteOne(long id);

	CampaignModel getOne(long id);

	void update(CampaignUpdate input, String token);

}
