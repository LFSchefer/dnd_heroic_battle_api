package co.simplon.dnd_heroic_battle_api.mappers;

import java.util.List;

import co.simplon.dnd_heroic_battle_api.dtos.battle.BattleCreate;
import co.simplon.dnd_heroic_battle_api.dtos.battle.BattleUpdate;
import co.simplon.dnd_heroic_battle_api.dtos.battle.BattleView;
import co.simplon.dnd_heroic_battle_api.dtos.campaign.CampaignView;
import co.simplon.dnd_heroic_battle_api.entities.Battle;
import co.simplon.dnd_heroic_battle_api.entities.Campaign;

public class BattleMapper {

    public static List<BattleView> entitiesToBattleViews(List<Battle> battles) {
	return battles.stream()
		.map(b -> new BattleView(b.getBattleName(),
			new CampaignView(b.getCampaign().getCampaignName(), b.getCampaign().getCreationDate())))
		.toList();
    }

    public static BattleView entityToBattleView(Battle battle) {
	return new BattleView(battle.getBattleName(),
		new CampaignView(battle.getCampaign().getCampaignName(), battle.getCampaign().getCreationDate()));
    }

    public static Battle battleCreateToEntity(BattleCreate input) {
	return Battle.builder().battleName(input.battleName())
		.campaign(Campaign.builder().id(input.campaignId()).build()).build();
    }

    public static Battle battleUpdateToEntity(BattleUpdate input, long id) {
	return Battle.builder().id(input.id()).battleName(input.battleName())
		.campaign(Campaign.builder().id(id).build()).build();
    }
}
