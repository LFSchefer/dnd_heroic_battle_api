package co.simplon.dnd_heroic_battle_api.services;

import co.simplon.dnd_heroic_battle_api.dtos.monsters.MonsterCreateDto;
import co.simplon.dnd_heroic_battle_api.dtos.monsters.MonsterInitiativeDto;
import co.simplon.dnd_heroic_battle_api.entities.Monster;
import jakarta.validation.Valid;

import java.util.Set;

public interface MonstersService {

    void create(MonsterCreateDto input);

    Set<MonsterInitiativeDto> getAllInitiative(Long battleId);

}
