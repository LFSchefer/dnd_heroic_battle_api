package co.simplon.dnd_heroic_battle_api.dtos.monsters;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record MonsterActionsUpdateDtos(@Positive Long monsterId, @NotNull Boolean action,
                                       @NotNull Boolean move, @NotNull Boolean bonusAction) {
}
