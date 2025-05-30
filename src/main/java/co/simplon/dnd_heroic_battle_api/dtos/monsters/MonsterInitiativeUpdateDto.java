package co.simplon.dnd_heroic_battle_api.dtos.monsters;

import jakarta.validation.constraints.Positive;

public record MonsterInitiativeUpdateDto(@Positive Long monsterId, int initiative) {
}
