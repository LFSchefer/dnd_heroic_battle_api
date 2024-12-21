package co.simplon.dnd_heroic_battle_api.services;

import java.util.List;

import co.simplon.dnd_heroic_battle_api.dtos.campaign.CampaignCreate;
import co.simplon.dnd_heroic_battle_api.dtos.campaign.CampaignUpdate;
import co.simplon.dnd_heroic_battle_api.models.CampaignModel;

public interface CampaignService {

	void create(CampaignCreate input);

	List<CampaignModel> getAllByUserId();

	void deleteOne(long id);

	CampaignModel getOne(long id);

	void update(CampaignUpdate input);

}
