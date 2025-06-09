package co.simplon.dnd_heroic_battle_api.dtos.battle;

import co.simplon.dnd_heroic_battle_api.entities.Monster;

import java.util.Set;

public record FightDto(Long battleId, String battleName, int turn, Set<Monster> monsters) {
}
