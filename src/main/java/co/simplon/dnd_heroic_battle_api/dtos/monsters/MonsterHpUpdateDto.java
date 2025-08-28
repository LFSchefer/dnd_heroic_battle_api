package co.simplon.dnd_heroic_battle_api.dtos.monsters;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record MonsterHpUpdateDto(@NotNull @Positive Long monsterId, @NotNull @Positive int amount,
                                 @NotBlank String type,
                                 @Positive Long damageTypeId) {
}
