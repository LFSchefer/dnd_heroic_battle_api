package co.simplon.dnd_heroic_battle_api.services;

import co.simplon.dnd_heroic_battle_api.dtos.battle_monsters.BattleMonsterCreateDto;
import co.simplon.dnd_heroic_battle_api.entities.BattleMonster;
import jakarta.validation.Valid;

public interface BattleMonstersService {

    void create(BattleMonsterCreateDto input);

    BattleMonster get(Long id);
}
