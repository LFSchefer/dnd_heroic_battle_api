package co.simplon.dnd_heroic_battle_api.mappers;

import co.simplon.dnd_heroic_battle_api.dtos.battle.BattleCreate;
import co.simplon.dnd_heroic_battle_api.dtos.battle.BattleUpdate;
import co.simplon.dnd_heroic_battle_api.entities.Battle;
import co.simplon.dnd_heroic_battle_api.entities.Campaign;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;


class BattleMapperTest {

    @Test
    void entitiesToBattleViews() {
        var battle = Battle.builder()
                .battleId(1L)
                .battleName("name")
                .turn(0)
                .build();
        var battles = List.of(battle);
        var actual = assertDoesNotThrow(() -> BattleMapper.entitiesToBattleViews(battles));
        assertEquals(battles.getFirst().getBattleId(), actual.getFirst().battleId());
        assertEquals(battles.getFirst().getBattleName(), actual.getFirst().battleName());
        assertEquals(battles.getFirst().getTurn(), actual.getFirst().turn());
    }

    @Test
    void battleCreateToEntity() {
        var battleCreate = new BattleCreate("battle name", 45L);
        var actual = assertDoesNotThrow(() -> BattleMapper.battleCreateToEntity(battleCreate));
        assertEquals(battleCreate.battleName(), actual.getBattleName());
        assertEquals(battleCreate.campaignId(), actual.getCampaign().getCampaignId());
    }

    @Test
    void battleUpdateToEntity() {
        var battleUpdate = new BattleUpdate(1L, "battle name", 3);
        long id = 24L;
        var actual = assertDoesNotThrow(() -> BattleMapper.battleUpdateToEntity(battleUpdate, id));
        assertEquals(battleUpdate.id(), actual.getBattleId());
        assertEquals(battleUpdate.battleName(), actual.getBattleName());
        assertEquals(battleUpdate.turn(), actual.getTurn());
        assertEquals(id, actual.getCampaign().getCampaignId());
    }

    @Test
    void entityToBattleModel() {
        var battle = Battle.builder()
                .battleId(1L)
                .battleName("name")
                .turn(0)
                .campaign(Campaign.builder().campaignId(2L).build())
                .monsters(new HashSet<>())
                .build();
        var actual = assertDoesNotThrow(() -> BattleMapper.entityToBattleModel(battle));
        assertEquals(battle.getBattleId(), actual.getBattleId());
        assertEquals(battle.getBattleName(), actual.getBattleName());
        assertEquals(battle.getTurn(), actual.getTurn());
        assertEquals(battle.getCampaign().getCampaignId(), actual.getCampaignId());
    }
}