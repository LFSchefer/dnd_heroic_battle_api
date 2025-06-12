package co.simplon.dnd_heroic_battle_api.dtos.monsters;

import co.simplon.dnd_heroic_battle_api.dtos.monster_model.MonsterModelDetail;

public record MonsterFightDto(Long monsterId, int currentHitPoints, int maxHitPoints, String name,
                              Integer initiative, boolean havePlayThisRound, MonsterModelDetail monster      ) {
}
