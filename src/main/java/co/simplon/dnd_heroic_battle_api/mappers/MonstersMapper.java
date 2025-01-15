package co.simplon.dnd_heroic_battle_api.mappers;

import co.simplon.dnd_heroic_battle_api.dtos.monsters.MonsterPreviewDto;
import co.simplon.dnd_heroic_battle_api.entities.Monster;

import java.util.Set;
import java.util.stream.Collectors;

public final class MonstersMapper {

    private MonstersMapper() {
        throw new IllegalStateException("Utility class");
    }

    public static MonsterPreviewDto entityToPreviewDto(Monster monster) {
        return new MonsterPreviewDto(monster.getBattleMonsterId(), monster.getName(), monster.getCurrentHitPoints(), monster.getInitiative(), monster.getBattleMonsterId());
    }

    public static Set<MonsterPreviewDto> setEntitiesToSetPreviewDto(Set<Monster> monsters) {
        return monsters.stream().map(m ->
                        new MonsterPreviewDto(m.getBattleMonsterId(), m.getName(), m.getCurrentHitPoints(), m.getInitiative(), m.getMonster().getMonsterId()))
                .collect(Collectors.toSet());
    }
}
