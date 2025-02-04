package co.simplon.dnd_heroic_battle_api.mappers;

import java.util.Set;
import java.util.stream.Collectors;

import co.simplon.dnd_heroic_battle_api.dtos.monsters.MonsterPreviewDto;
import co.simplon.dnd_heroic_battle_api.entities.Monster;

public final class MonstersMapper {

    private MonstersMapper() {
        throw new IllegalStateException("Utility class");
    }

    public static MonsterPreviewDto entityToPreviewDto(Monster monster) {
        return new MonsterPreviewDto(monster.getMonsterId(), monster.getName(), monster.getCurrentHitPoints(), monster.getMaxHitPoints(), monster.getInitiative(), monster.getMonsterId());
    }

    public static Set<MonsterPreviewDto> setEntitiesToSetPreviewDto(Set<Monster> monsters) {
        return monsters.stream().map(m ->
                        new MonsterPreviewDto(m.getMonsterId(), m.getName(), m.getCurrentHitPoints(), m.getMaxHitPoints(), m.getInitiative(), m.getMonster().getModelId()))
                .collect(Collectors.toSet());
    }
}
