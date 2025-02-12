package co.simplon.dnd_heroic_battle_api.mappers;

import co.simplon.dnd_heroic_battle_api.dtos.monster_model.MonsterModelDetail;
import co.simplon.dnd_heroic_battle_api.entities.MonsterModel;

public final class MonsterModelMapper {

    private MonsterModelMapper() {
        throw new IllegalStateException("Utility class");
    }

    public static MonsterModelDetail entityToDetailDto(MonsterModel monster) {
        return new MonsterModelDetail(monster.getMonsterName(), monster.getHitPoints(), monster.getHitPointsRoll(),
                monster.getStrength(), monster.getDexterity(), monster.getConstitution(), monster.getIntelligence(),
                monster.getWisdom(), monster.getCharisma(), monster.getChallengeRating(), monster.getXp(),
                monster.getImageUrl(), monster.isDnd5Native(), monster.getPassivePerception(), monster.getDarkvision(),
                monster.getWalk(), monster.getSwim(), monster.getFly(),
                monster.getAlignment() == null ? null : monster.getAlignment().getAlignmentsName(),
                monster.getMonsterType().getTypeName(),monster.getSize().getSizeName(),
                monster.getArmorClass().getArmorValue(),monster.getArmorClass().getArmorType(),
                LanguageMapper.entitiesToNames(monster.getLanguages()),
                ConditionMapper.entitiesToNames(monster.getConditionsImmunities()),
                DamageTypeMapper.entitiesToNames(monster.getMonsterVulnerabilities()),
                DamageTypeMapper.entitiesToNames(monster.getMonsterResistances()),
                DamageTypeMapper.entitiesToNames(monster.getMonsterImunities()),
                SpecialAbilityMapper.entitiesToNames(monster.getSpecialAbilities()));
    }
}
