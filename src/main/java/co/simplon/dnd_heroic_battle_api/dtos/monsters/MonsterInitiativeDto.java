package co.simplon.dnd_heroic_battle_api.dtos.monsters;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public record MonsterInitiativeDto(@Positive Long id, @NotBlank String name, @Positive int dexterity, int bonus, Short initiative) {
}
