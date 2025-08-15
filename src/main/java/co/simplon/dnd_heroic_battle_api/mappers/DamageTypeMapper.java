package co.simplon.dnd_heroic_battle_api.mappers;


import co.simplon.dnd_heroic_battle_api.dtos.damage_types.DamageTypeDto;
import co.simplon.dnd_heroic_battle_api.entities.DamageType;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public final class DamageTypeMapper {

    private DamageTypeMapper() {
        throw new IllegalStateException("Utility class");
    }

    public static Set<String> entitiesToNames(Set<DamageType> damageTypes) {
        return damageTypes.stream().map(DamageType::getDamageTypeName).collect(Collectors.toSet());
    }

    public static List<DamageTypeDto> entitiesToDtos(List<DamageType> damageTypes) {
        return damageTypes.stream().map(damageType -> new DamageTypeDto(damageType.getId(), damageType.getDamageTypeName())).toList();
    }
}
