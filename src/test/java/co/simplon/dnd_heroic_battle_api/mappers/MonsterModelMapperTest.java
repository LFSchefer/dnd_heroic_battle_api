package co.simplon.dnd_heroic_battle_api.mappers;

import co.simplon.dnd_heroic_battle_api.entities.*;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;


class MonsterModelMapperTest {

    @Test
    void entityToDetailDto() {
        var monsterModel = MonsterModel.builder()
                .monsterName("name")
                .hitPoints(42)
                .hitPointsRoll("12d20")
                .strength(20)
                .dexterity(19)
                .constitution(18)
                .intelligence(17)
                .wisdom(16)
                .charisma(15)
                .challengeRating(1.2)
                .xp(654)
                .imageUrl("url")
                .dnd5Native(true)
                .passivePerception(12)
                .darkvision(null)
                .walk(35)
                .fly(null)
                .swim(null)
                .size(Size.builder().sizeName("size").build())
                .alignment(Alignment.builder().alignmentsName(" alignment name").build())
                .monsterType(MonsterType.builder().typeName("type name").build())
                .armorType(ArmorType.builder().armorType("armor type").build())
                .languages(Set.of())
                .conditionsImmunities(new HashSet<>())
                .monsterVulnerabilities(new HashSet<>())
                .monsterResistances(new HashSet<>())
                .monsterImunities(new HashSet<>())
                .specialAbilities(new HashSet<>())
                .build();
        var actual = assertDoesNotThrow(() -> MonsterModelMapper.entityToDetailDto(monsterModel));
        assertEquals(monsterModel.getMonsterName(), actual.monsterName());
        assertEquals(monsterModel.getHitPoints(), actual.hitPoints());
        assertEquals(monsterModel.getHitPointsRoll(), actual.hitPointsRoll());
        assertEquals(monsterModel.getStrength(), actual.strength());
        assertEquals(monsterModel.getDexterity(), actual.dexterity());
        assertEquals(monsterModel.getConstitution(), actual.constitution());
        assertEquals(monsterModel.getIntelligence(), actual.intelligence());
        assertEquals(monsterModel.getWisdom(), actual.wisdom());
        assertEquals(monsterModel.getCharisma(), actual.charisma());
        assertEquals(monsterModel.getChallengeRating(), actual.challengeRating());
        assertEquals(monsterModel.getXp(), actual.xp());
        assertEquals(monsterModel.getImageUrl(), actual.imageUrl());
        assertEquals(monsterModel.isDnd5Native(), actual.dnd5Native());
        assertEquals(monsterModel.getPassivePerception(), actual.passivePerception());
        assertEquals(monsterModel.getDarkvision(), actual.darkvision());
        assertEquals(monsterModel.getWalk(), actual.walk());
        assertEquals(monsterModel.getFly(), actual.fly());
        assertEquals(monsterModel.getSwim(), actual.swim());
        assertEquals(monsterModel.getAlignment().getAlignmentsName(), actual.alignment());
        assertEquals(monsterModel.getMonsterType().getTypeName(), actual.monsterType());
        assertEquals(monsterModel.getArmorType().getArmorType(), actual.armorType());
        assertEquals(monsterModel.getSize().getSizeName(), actual.size());
    }
}