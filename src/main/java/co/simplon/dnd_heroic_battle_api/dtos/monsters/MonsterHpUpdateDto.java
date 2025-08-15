package co.simplon.dnd_heroic_battle_api.dtos.monsters;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record MonsterHpUpdateDto(@Positive Long monsterId, @Positive int amount, @NotNull String type,
                                 Integer damageTypeId) {
}
