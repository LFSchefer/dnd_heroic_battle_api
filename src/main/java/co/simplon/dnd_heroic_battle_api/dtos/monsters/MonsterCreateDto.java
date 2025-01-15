package co.simplon.dnd_heroic_battle_api.dtos.monsters;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public record MonsterCreateDto(@NotBlank String name,
                                     @Positive Long battleId,
                                     @Positive Long monsterId) {
}
