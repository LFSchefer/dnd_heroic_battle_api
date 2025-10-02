package co.simplon.dnd_heroic_battle_api.mappers;

import co.simplon.dnd_heroic_battle_api.dtos.monsters.MonsterInitiativePro;
import co.simplon.dnd_heroic_battle_api.entities.Monster;
import co.simplon.dnd_heroic_battle_api.entities.MonsterModel;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;


class MonstersMapperTest {


    @Test
    void setEntitiesToSetPreviewDto() {
        var monster = Monster.builder()
                .monsterId(1L)
                .name("name")
                .currentHitPoints(42)
                .maxHitPoints(42)
                .initiative(15)
                .monster(MonsterModel.builder().modelId(24L).build())
                .build();
        var monsters = Set.of(monster);
        var actual = assertDoesNotThrow(() -> MonstersMapper.setEntitiesToSetPreviewDto(monsters));
        assertEquals(monsters.stream().findFirst().get().getMonsterId(),
                actual.stream().findFirst().get().id());
        assertEquals(monsters.stream().findFirst().get().getName(),
                actual.stream().findFirst().get().name());
        assertEquals(monsters.stream().findFirst().get().getMaxHitPoints(),
                actual.stream().findFirst().get().maxHitPoints());
        assertEquals(monsters.stream().findFirst().get().getCurrentHitPoints(),
                actual.stream().findFirst().get().currentHitPoints());
        assertEquals(monsters.stream().findFirst().get().getInitiative(),
                actual.stream().findFirst().get().initiative());
        assertEquals(monsters.stream().findFirst().get().getMonster().getModelId(),
                actual.stream().findFirst().get().modelId());
    }

    @Test
    void InitiativeProsToInitiativeDtos() {
        MonsterInitiativePro initiativePro1 = new MonsterInitiativePro(1L, "Dragon", (short) 15, 18);
        MonsterInitiativePro initiativePro2 = new MonsterInitiativePro(24L, "Giant", (short) 11, 8);
        Set<MonsterInitiativePro> monsterInitiativePros = Set.of(initiativePro1, initiativePro2);
        var actual = assertDoesNotThrow(() -> MonstersMapper.InitiativeProsToInitiativeDtos(monsterInitiativePros));
        assertEquals(actual.stream().filter(m -> m.id().equals(1L)).findFirst().get().initiative(), (short) 15);
        assertEquals(actual.stream().filter(m -> m.id().equals(24L)).findFirst().get().initiative(), (short) 11);
    }

    @Test
    void entitiesToMonsterFightDtos() {
        var monsterModel = MonsterModel.builder()
                .strength(18)
                .dexterity(11)
                .constitution(15)
                .intelligence(7)
                .wisdom(5)
                .charisma(9)
                .armorClass(21)
                .imageUrl("url")
                .build();
        var monster = Monster.builder()
                .monsterId(12L)
                .currentHitPoints(45)
                .maxHitPoints(78)
                .name("bob")
                .initiative(17)
                .action(true)
                .move(true)
                .bonusAction(false)
                .hisTurn(false)
                .havePlayThisRound(true)
                .monster(monsterModel)
                .build();
        var actual = assertDoesNotThrow(() -> MonstersMapper.entitiesToMonsterFightDtos(Set.of(monster)));
        assertEquals(actual.stream().findFirst().get().monsterId(), monster.getMonsterId());
        assertEquals(actual.stream().findFirst().get().currentHitPoints(), monster.getCurrentHitPoints());
        assertEquals(actual.stream().findFirst().get().maxHitPoints(), monster.getMaxHitPoints());
        assertEquals(actual.stream().findFirst().get().name(), monster.getName());
        assertEquals(actual.stream().findFirst().get().initiative(), monster.getInitiative());
    }

    @Test
    void entityToFightDto() {
        var monsterModel = MonsterModel.builder()
                .strength(18)
                .dexterity(11)
                .constitution(15)
                .intelligence(7)
                .wisdom(5)
                .charisma(9)
                .armorClass(21)
                .imageUrl("url")
                .build();
        var monster = Monster.builder()
                .monsterId(12L)
                .currentHitPoints(45)
                .maxHitPoints(78)
                .name("bob")
                .initiative(17)
                .action(true)
                .move(true)
                .bonusAction(false)
                .hisTurn(false)
                .havePlayThisRound(true)
                .monster(monsterModel)
                .build();
        var actual = assertDoesNotThrow(() -> MonstersMapper.entityToFightDto(monster));
        assertEquals(actual.monsterId(), monster.getMonsterId());
        assertEquals(actual.currentHitPoints(), monster.getCurrentHitPoints());
        assertEquals(actual.maxHitPoints(), monster.getMaxHitPoints());
        assertEquals(actual.name(), monster.getName());
        assertEquals(actual.initiative(), monster.getInitiative());
    }
}