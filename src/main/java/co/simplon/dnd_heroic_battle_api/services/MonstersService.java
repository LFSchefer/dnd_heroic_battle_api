package co.simplon.dnd_heroic_battle_api.services;

import co.simplon.dnd_heroic_battle_api.dtos.monsters.MonsterCreateDto;
import co.simplon.dnd_heroic_battle_api.entities.Monster;
import jakarta.validation.Valid;

public interface MonstersService {

    void create(MonsterCreateDto input);

    Monster get(Long id);
}
