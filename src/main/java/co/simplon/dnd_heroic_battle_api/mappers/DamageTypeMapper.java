package co.simplon.dnd_heroic_battle_api.mappers;


import co.simplon.dnd_heroic_battle_api.entities.DamageType;

import java.util.Set;
import java.util.stream.Collectors;

public final class DamageTypeMapper {

    private DamageTypeMapper() {
        throw new IllegalStateException("Utility class");
    }

    public static Set<String> entitiesToNames(Set<DamageType> damageTypes) {
        return damageTypes.stream().map(DamageType::getDamageTypeName).collect(Collectors.toSet());
    }
}
