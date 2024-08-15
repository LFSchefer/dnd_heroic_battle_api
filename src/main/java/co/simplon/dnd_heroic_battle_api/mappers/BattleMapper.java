package co.simplon.dnd_heroic_battle_api.mappers;

import java.util.List;

import co.simplon.dnd_heroic_battle_api.dtos.battle.BattleCreate;
import co.simplon.dnd_heroic_battle_api.dtos.battle.BattleUpdate;
import co.simplon.dnd_heroic_battle_api.dtos.battle.BattleView;
import co.simplon.dnd_heroic_battle_api.entities.Battle;
import co.simplon.dnd_heroic_battle_api.entities.Campaign;
import co.simplon.dnd_heroic_battle_api.models.BattleModel;

public class BattleMapper {

	public static List<BattleView> entitiesToBattleViews(List<Battle> battles) {
		return battles.stream().map(b -> new BattleView(b.getBattleName(), CampaignMapper.entityToCampaignView(b.getCampaign()))).toList();
	}

	public static BattleView entityToBattleView(Battle battle) {
		return new BattleView(battle.getBattleName(), CampaignMapper.entityToCampaignView(battle.getCampaign()));
	}

	public static Battle battleCreateToEntity(BattleCreate input) {
		return Battle.builder().battleName(input.battleName()).campaign(Campaign.builder().campaignId(input.campaignId()).build()).build();
	}

	public static Battle battleUpdateToEntity(BattleUpdate input, long id) {
		return Battle.builder().battleId(input.id()).battleName(input.battleName()).campaign(Campaign.builder().campaignId(id).build()).build();
	}

	public static List<BattleModel> entitiesToBattleModel(List<Battle> battles) {
		return battles.stream().map(b -> new BattleModel(b.getId(), b.getBattleName(), CampaignMapper.entityToCampaignModel(b.getCampaign()))).toList();
	}

	public static BattleModel entityToBattleModel(Battle battle) {
		return new BattleModel(battle.getId(), battle.getBattleName(), CampaignMapper.entityToCampaignModel(battle.getCampaign()));
	}
}
