package co.simplon.dnd_heroic_battle_api.mappers;

import co.simplon.dnd_heroic_battle_api.dtos.damage_types.DamageTypeDto;
import co.simplon.dnd_heroic_battle_api.entities.DamageType;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;


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

    @Test
    void entitiesToDtos() {
        var damageType1 = DamageType.builder().damageTypeId(1L).damageTypeName("fire").build();
        var damageType2 = DamageType.builder().damageTypeId(2L).damageTypeName("cold").build();
        var damageTypes = List.of(damageType1, damageType2);
        var actual = assertDoesNotThrow(() -> DamageTypeMapper.entitiesToDtos(damageTypes));
        assertTrue(actual.contains(new DamageTypeDto(1L, "fire")));
        assertTrue(actual.contains(new DamageTypeDto(2L, "cold")));
    }
}