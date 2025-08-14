package co.simplon.dnd_heroic_battle_api.dtos.monster_model;

public record MonsterModelFightDto(Integer strength,
                                   Integer dexterity,
                                   Integer constitution,
                                   Integer intelligence,
                                   Integer wisdom,
                                   Integer charisma,
                                   int armorClass,
                                   String imageUrl) {
}
