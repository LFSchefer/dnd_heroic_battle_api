package co.simplon.dnd_heroic_battle_api.dtos.monsters;

public record MonsterActionsUpdateDtos(Long monsterId, boolean action, boolean move, boolean bonusAction) {
}
