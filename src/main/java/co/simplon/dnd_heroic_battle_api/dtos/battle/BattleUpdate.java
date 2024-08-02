package co.simplon.dnd_heroic_battle_api.dtos.battle;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record BattleUpdate(@Positive Long id, @NotBlank @Size(min = 5) String battleName) {

}
