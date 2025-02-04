package co.simplon.dnd_heroic_battle_api.dtos.monsters;

import co.simplon.dnd_heroic_battle_api.dtos.monsters.validators.ValidHitPoints;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

@ValidHitPoints
public record MonsterCreateDto(@NotBlank String name,
                               @Positive int currentHitPoints,
                               @Positive int maxHitPoints,
                               @Positive long battleId,
                               @Positive long monsterId) {
}
