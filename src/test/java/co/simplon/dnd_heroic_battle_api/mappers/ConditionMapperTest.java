package co.simplon.dnd_heroic_battle_api.mappers;

import co.simplon.dnd_heroic_battle_api.entities.Condition;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;


class ConditionMapperTest {

    @Test
    void entitiesToNames() {
        var condition1 = Condition.builder().conditionName("stun").build();
        var condition2 = Condition.builder().conditionName("bleed").build();
        var conditions = Set.of(condition1, condition2);
        var actual = assertDoesNotThrow(() -> ConditionMapper.entitiesToNames(conditions));
        assertTrue(actual.contains(condition1.getConditionName()));
        assertTrue(actual.contains(condition2.getConditionName()));
    }
}