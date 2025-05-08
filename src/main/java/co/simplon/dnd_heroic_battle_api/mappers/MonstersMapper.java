package co.simplon.dnd_heroic_battle_api.mappers;

import java.util.Set;
import java.util.stream.Collectors;

import co.simplon.dnd_heroic_battle_api.dtos.monsters.MonsterInitiativeDto;
import co.simplon.dnd_heroic_battle_api.dtos.monsters.MonsterPreviewDto;
import co.simplon.dnd_heroic_battle_api.entities.Monster;

public final class MonstersMapper {

    private MonstersMapper() {
        throw new IllegalStateException("Utility class");
    }

    public static Set<MonsterPreviewDto> setEntitiesToSetPreviewDto(Set<Monster> monsters) {
        return monsters.stream().map(m ->
                        new MonsterPreviewDto(m.getMonsterId(), m.getName(), m.getCurrentHitPoints(), m.getMaxHitPoints(), m.getInitiative(), m.getMonster().getModelId()))
                .collect(Collectors.toSet());
    }

    public static Set<MonsterInitiativeDto> entitiesToInitiativeDtos(Set<Monster> monsters) {
        return monsters.stream().map( m ->
                        new MonsterInitiativeDto(m.getMonsterId(), m.getName(), m.getMonster().getDexterity(), (m.getMonster().getDexterity() - 10 )/2, m.getInitiative()))
                .collect(Collectors.toSet());
    }
}
