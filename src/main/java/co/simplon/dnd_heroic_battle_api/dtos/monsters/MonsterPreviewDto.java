package co.simplon.dnd_heroic_battle_api.dtos.monsters;

public record MonsterPreviewDto(Long id, String name, Integer currentHitPoints, Integer initiative, Long monsterId) {
}
