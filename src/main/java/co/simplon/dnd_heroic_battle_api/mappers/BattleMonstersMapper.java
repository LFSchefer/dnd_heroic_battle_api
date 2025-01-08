package co.simplon.dnd_heroic_battle_api.mappers;

import co.simplon.dnd_heroic_battle_api.dtos.battle_monsters.BattleMonsterPreviewDto;
import co.simplon.dnd_heroic_battle_api.entities.BattleMonster;

import java.util.Set;
import java.util.stream.Collectors;

public final class BattleMonstersMapper {

    private BattleMonstersMapper() {
        throw new IllegalStateException("Utility class");
    }

    public static BattleMonsterPreviewDto entityToPreviewDto(BattleMonster battleMonster) {
        return new BattleMonsterPreviewDto(battleMonster.getBattleMonsterId(), battleMonster.getName(), battleMonster.getCurrentHitPoints(), battleMonster.getInitiative(), battleMonster.getBattleMonsterId());
    }

    public static Set<BattleMonsterPreviewDto> setEntitiesToSetPreviewDto(Set<BattleMonster> battleMonsters) {
        return battleMonsters.stream().map(m ->
                        new BattleMonsterPreviewDto(m.getBattleMonsterId(), m.getName(), m.getCurrentHitPoints(), m.getInitiative(), m.getMonster().getMonsterId()))
                .collect(Collectors.toSet());
    }
}
