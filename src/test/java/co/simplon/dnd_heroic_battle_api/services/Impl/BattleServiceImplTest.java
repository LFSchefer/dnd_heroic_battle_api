package co.simplon.dnd_heroic_battle_api.services.Impl;

import co.simplon.dnd_heroic_battle_api.dtos.battle.BattleCreate;
import co.simplon.dnd_heroic_battle_api.dtos.battle.BattleDto;
import co.simplon.dnd_heroic_battle_api.dtos.battle.BattleUpdate;
import co.simplon.dnd_heroic_battle_api.dtos.monsters.MonsterPreviewDto;
import co.simplon.dnd_heroic_battle_api.entities.Battle;
import co.simplon.dnd_heroic_battle_api.entities.Campaign;
import co.simplon.dnd_heroic_battle_api.entities.Monster;
import co.simplon.dnd_heroic_battle_api.entities.MonsterModel;
import co.simplon.dnd_heroic_battle_api.repositories.BattleRepository;
import org.hibernate.ResourceClosedException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class BattleServiceImplTest {

    @InjectMocks
    private BattleServiceImpl test;

    @Mock
    private BattleRepository repo;

    @Test
    void getOneWithBadId() {
        when(repo.findById(1L)).thenReturn(Optional.empty());
        assertThrows(ResourceClosedException.class, () ->
                test.getOne(1L));
    }

    @Test
    void getOne() {
        var campaign = Campaign.builder()
                .campaignId(1L)
                .campaignName("campaign name")
                .build();
        var monsterModel = MonsterModel.builder().build();
        var monster = Monster.builder()
                .monster(monsterModel)
                .name("monster name")
                .monsterId(1L)
                .currentHitPoints(42)
                .maxHitPoints(42)
                .build();
        var battle = Battle.builder()
                .battleId(1L)
                .battleName("battle name")
                .turn(0)
                .campaign(campaign)
                .monsters(Set.of(monster))
                .build();
        when(repo.findById(1L)).thenReturn(Optional.of(battle));
        var actual = assertDoesNotThrow(() ->
                test.getOne(1L));
        assertEquals(actual.getBattleName(), battle.getBattleName());
        assertEquals(actual.getBattleId(), battle.getBattleId());
        assertEquals(actual.getTurn(), battle.getTurn());
        assertEquals(actual.getCampaignId(), campaign.getCampaignId());
        assertEquals(actual.getBattleMonsters().stream().map(MonsterPreviewDto::name).collect(Collectors.toSet()),
                Set.of(monster.getName()));
        assertEquals(actual.getBattleMonsters().stream().map(MonsterPreviewDto::id).collect(Collectors.toSet()),
                Set.of(monster.getMonsterId()));
        assertEquals(actual.getBattleMonsters().stream().map(MonsterPreviewDto::currentHitPoints).collect(Collectors.toSet()),
                Set.of(monster.getCurrentHitPoints()));
        assertEquals(actual.getBattleMonsters().stream().map(MonsterPreviewDto::maxHitPoints).collect(Collectors.toSet()),
                Set.of(monster.getMaxHitPoints()));
    }

    @Test
    void deleteOne() {
        assertDoesNotThrow(() ->
                test.deleteOne(1L));
        verify(repo, times(1)).deleteById(1L);
    }

    @Test
    void create() {
        var input = new BattleCreate("battle name", 1L);
        assertDoesNotThrow(() -> test.create(input));
        verify(repo, times(1)).save(any());
    }

    @Test
    void update() {
        var input = new BattleUpdate(1L, "battle name", 0);
        assertDoesNotThrow(() -> test.update(input));
        verify(repo).save(any());
    }

    @Test
    void getAllFromCampaignWithBadId() {
        when(repo.findAllByCampaignId(1L)).thenReturn(List.of());
        var actual = assertDoesNotThrow(() -> test.getAllFromCampaign(1L));
        assertEquals(actual, List.of());
    }

    @Test
    void getAllFromCampaign() {
        var battle = Battle.builder()
                .battleId(1L)
                .battleName("name")
                .turn(42)
                .build();
        when(repo.findAllByCampaignId(1L)).thenReturn(List.of(battle));
        var actual = assertDoesNotThrow(() -> test.getAllFromCampaign(1L));
        assertEquals(1, actual.size());
        assertEquals(1, actual.stream().map(BattleDto::battleId).toList().getFirst());
        assertEquals("name", actual.stream().map(BattleDto::battleName).toList().getFirst());
        assertEquals(42, actual.stream().map(BattleDto::turn).toList().getFirst());
    }

    @Test
    void getFightWithTurn0() {
        var battle = Battle.builder()
                .battleName("name")
                .battleId(1L)
                .turn(0)
                .build();
        when(repo.findById(1L)).thenReturn(Optional.of(battle));
        when(repo.saveAndFlush(battle)).thenReturn(battle);
        var actual = assertDoesNotThrow(() -> test.getFight(1L));
        assertEquals(1L, actual.battleId());
        assertEquals("name", battle.getBattleName());
        assertEquals(1, actual.turn());
    }

    @Test
    void getFight() {
        var battle = Battle.builder()
                .battleName("name")
                .battleId(1L)
                .turn(12)
                .build();
        when(repo.findById(1L)).thenReturn(Optional.of(battle));
        var actual = assertDoesNotThrow(() -> test.getFight(1L));
        assertEquals(1L, actual.battleId());
        assertEquals("name", battle.getBattleName());
        assertEquals(12, actual.turn());
    }
}