package co.simplon.dnd_heroic_battle_api.dtos.battle_monsters;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public record BattleMonsterCreateDto(@NotBlank String name,
                                     @Positive int currentHitPoints,
                                     @Positive Long battleId,
                                     @Positive Long monsterId) {
}
