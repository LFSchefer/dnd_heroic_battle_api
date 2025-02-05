package co.simplon.dnd_heroic_battle_api.mappers;

import co.simplon.dnd_heroic_battle_api.entities.SpecialAbility;

import java.util.Set;
import java.util.stream.Collectors;

public final class SpecialAbilityMapper {

    private SpecialAbilityMapper() {
        throw new IllegalStateException("Utility class");
    }

    public static Set<String> entitiesToNames(Set<SpecialAbility> specialAbilities) {
        return specialAbilities.stream().map(SpecialAbility::getSpecialAbilityName).collect(Collectors.toSet());
    }
}
