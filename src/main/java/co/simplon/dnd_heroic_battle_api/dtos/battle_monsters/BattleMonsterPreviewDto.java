package co.simplon.dnd_heroic_battle_api.dtos.battle_monsters;

public record BattleMonsterPreviewDto(Long id, String name, Integer currentHitPoints, Integer initiative, Long monsterId) {
}
