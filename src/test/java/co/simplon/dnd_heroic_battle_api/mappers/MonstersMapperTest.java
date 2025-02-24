package co.simplon.dnd_heroic_battle_api.mappers;

import co.simplon.dnd_heroic_battle_api.entities.Monster;
import co.simplon.dnd_heroic_battle_api.entities.MonsterModel;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

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
}