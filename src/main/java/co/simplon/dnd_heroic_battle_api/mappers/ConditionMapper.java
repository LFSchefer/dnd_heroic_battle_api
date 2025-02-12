package co.simplon.dnd_heroic_battle_api.mappers;

import co.simplon.dnd_heroic_battle_api.entities.Condition;

import java.util.Set;
import java.util.stream.Collectors;

public final class ConditionMapper {

    private ConditionMapper() {
        throw new IllegalStateException("Utility class");
    }

    public static Set<String> entitiesToNames(Set<Condition> conditions) {
        return conditions.stream().map(Condition::getConditionName).collect(Collectors.toSet());
    }
}
