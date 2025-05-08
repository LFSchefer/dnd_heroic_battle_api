package co.simplon.dnd_heroic_battle_api.dtos.monsters;

public record MonsterInitiativeDto(Long id, String name, int dexterity, int bonus, Integer initiative) {
}
