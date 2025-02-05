package co.simplon.dnd_heroic_battle_api.dtos.monster_model;

import co.simplon.dnd_heroic_battle_api.entities.*;
import java.util.Set;

public record MonsterModelDetail(
        String monsterName,
        Integer hitPoints,
        String hitPointsRoll,
        Integer strength,
        Integer dexterity,
        Integer constitution,
        Integer intelligence,
        Integer wisdom,
        Integer charisma,
        Double challengeRating,
        Integer xp,
        String imageUrl,
        boolean dnd5Native,
        Integer passivePerception,
        Integer darkvision,
        Integer walk,
        Integer swim,
        Integer fly,
        String alignment,
        String monsterType,
        String size,
        int armorClass,
        String armorType,
        Set<String> languages,
        Set<String> conditionsImmunities,
        Set<String> monsterVulnerabilities,
        Set<String> monsterResistances,
        Set<String> monsterImunities,
        Set<String> specialAbilities
) {
}
