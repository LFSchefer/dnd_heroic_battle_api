package co.simplon.dnd_heroic_battle_api.dtos.battle;

import co.simplon.dnd_heroic_battle_api.dtos.campaign.CampaignView;

public record BattleView(String battleName, CampaignView campaign) {

}
