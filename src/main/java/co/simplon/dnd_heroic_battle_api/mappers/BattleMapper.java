package co.simplon.dnd_heroic_battle_api.mappers;

import co.simplon.dnd_heroic_battle_api.dtos.battle.BattleCreate;
import co.simplon.dnd_heroic_battle_api.dtos.battle.BattleDto;
import co.simplon.dnd_heroic_battle_api.dtos.battle.BattleUpdate;
import co.simplon.dnd_heroic_battle_api.entities.Battle;
import co.simplon.dnd_heroic_battle_api.entities.Campaign;
import co.simplon.dnd_heroic_battle_api.models.BattleModel;

import java.util.List;

public final class BattleMapper {

    private BattleMapper() {
        throw new IllegalStateException("Utility class");
    }

    public static List<BattleDto> entitiesToBattleViews(List<Battle> battles) {
        return battles.stream().map(b ->
                        new BattleDto(b.getBattleId(), b.getBattleName(), b.getTurn()))
                .toList();
    }

    public static Battle battleCreateToEntity(BattleCreate input) {
        return Battle.builder()
                .battleName(input.battleName())
                .campaign(Campaign.builder()
                        .campaignId(input.campaignId())
                        .build())
                .build();
    }

    public static Battle battleUpdateToEntity(BattleUpdate input, long id) {
        return Battle.builder()
                .battleId(input.id())
                .battleName(input.battleName())
                .turn(input.turn())
                .campaign(Campaign.builder()
                        .campaignId(id)
                        .build())
                .build();
    }

    public static BattleModel entityToBattleModel(Battle battle) {
        return new BattleModel(battle.getBattleId(), battle.getBattleName(), battle.getTurn(), battle.getCampaign().getId(),
                MonstersMapper.setEntitiesToSetPreviewDto(battle.getBattleMonsters()));
    }
}
