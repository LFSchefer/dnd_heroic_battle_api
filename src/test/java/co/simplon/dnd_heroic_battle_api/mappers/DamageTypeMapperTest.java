package co.simplon.dnd_heroic_battle_api.mappers;

import co.simplon.dnd_heroic_battle_api.entities.DamageType;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class DamageTypeMapperTest {

    @Test
    void entitiesToNames() {
        var damageType1 = DamageType.builder().damageTypeName("fire").build();
        var damageType2 = DamageType.builder().damageTypeName("cold").build();
        var damageTypes = Set.of(damageType1, damageType2);
        var actual = assertDoesNotThrow(() -> DamageTypeMapper.entitiesToNames(damageTypes));
        assertTrue(actual.contains(damageType1.getDamageTypeName()));
        assertTrue(actual.contains(damageType2.getDamageTypeName()));
    }
}