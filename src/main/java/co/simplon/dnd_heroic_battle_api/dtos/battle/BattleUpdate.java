package co.simplon.dnd_heroic_battle_api.dtos.battle;

import co.simplon.dnd_heroic_battle_api.dtos.battle.validators.UniqueBattleNameUpdate;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

@UniqueBattleNameUpdate
public record BattleUpdate(@Positive Long id, @NotBlank @Size(min = 5, max = 50) String battleName, @PositiveOrZero int turn) {

}
