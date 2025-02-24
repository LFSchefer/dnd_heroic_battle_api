package co.simplon.dnd_heroic_battle_api.mappers;

import co.simplon.dnd_heroic_battle_api.entities.SpecialAbility;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class SpecialAbilityMapperTest {

    @Test
    void entitiesToNames() {
        var specialAbility = SpecialAbility.builder()
                .specialAbilityId(1L)
                .specialAbilityDescription("description")
                .specialAbilityName("ability name")
                .build();
        var specialAbilities = Set.of(specialAbility);
        var actual = assertDoesNotThrow(() -> SpecialAbilityMapper.entitiesToNames(specialAbilities));
        assertEquals(specialAbilities.stream().findFirst().get().getSpecialAbilityName(),
                actual.stream().findFirst().get());
    }
}