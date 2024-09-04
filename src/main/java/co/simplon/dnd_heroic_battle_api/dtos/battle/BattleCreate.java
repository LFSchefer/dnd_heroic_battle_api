package co.simplon.dnd_heroic_battle_api.dtos.battle;

import co.simplon.dnd_heroic_battle_api.dtos.battle.validators.UniqueBattleNameCreate;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

@UniqueBattleNameCreate
public record BattleCreate(@NotBlank @Size(min = 5, max = 50) String battleName, @Positive Long campaignId) {

}
