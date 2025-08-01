package co.simplon.dnd_heroic_battle_api.mappers;

import co.simplon.dnd_heroic_battle_api.dtos.monsters.MonsterFightDto;
import co.simplon.dnd_heroic_battle_api.dtos.monsters.MonsterInitiativeDto;
import co.simplon.dnd_heroic_battle_api.dtos.monsters.MonsterInitiativePro;
import co.simplon.dnd_heroic_battle_api.dtos.monsters.MonsterPreviewDto;
import co.simplon.dnd_heroic_battle_api.entities.Monster;

import java.util.Set;
import java.util.stream.Collectors;

public final class MonstersMapper {

    private MonstersMapper() {
        throw new IllegalStateException("Utility class");
    }

    public static Set<MonsterPreviewDto> setEntitiesToSetPreviewDto(Set<Monster> monsters) {
        return monsters.stream().map(m ->
                        new MonsterPreviewDto(m.getMonsterId(), m.getName(), m.getCurrentHitPoints(), m.getMaxHitPoints(), m.getInitiative(), m.getMonster().getModelId()))
                .collect(Collectors.toSet());
    }

    public static Set<MonsterInitiativeDto> InitiativeProsToInitiativeDtos(Set<MonsterInitiativePro> monsterInitiativePros) {
        return monsterInitiativePros.stream().map(m ->
                new MonsterInitiativeDto(m.id(), m.name(), m.dexterity(), (m.dexterity() - 10) / 2, m.initiative())).collect(Collectors.toSet());
    }

    public static Set<MonsterFightDto> entitiesToMonsterFightDtos(Set<Monster> monsters) {
        return monsters.stream().map(monster ->
                        new MonsterFightDto(monster.getMonsterId(), monster.getCurrentHitPoints(),
                                monster.getMaxHitPoints(), monster.getName(), monster.getInitiative(),
                                monster.isAction(), monster.isMove(), monster.isBonusAction(), monster.isHisTurn(),
                                monster.isHavePlayThisRound(),
                                MonsterModelMapper.entityToDetailDto(monster.getMonster())
                        ))
                .collect(Collectors.toSet());
    }

    public static MonsterFightDto entityToFightDto(Monster monster) {
        return new MonsterFightDto(monster.getMonsterId(), monster.getCurrentHitPoints(),
                monster.getMaxHitPoints(), monster.getName(), monster.getInitiative(),
                monster.isAction(), monster.isMove(), monster.isBonusAction(), monster.isHisTurn(),
                monster.isHavePlayThisRound(),
                MonsterModelMapper.entityToDetailDto(monster.getMonster()));
    }
}
