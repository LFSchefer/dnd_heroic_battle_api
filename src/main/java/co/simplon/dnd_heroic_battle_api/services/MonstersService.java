package co.simplon.dnd_heroic_battle_api.services;

import co.simplon.dnd_heroic_battle_api.dtos.monsters.*;
import co.simplon.dnd_heroic_battle_api.entities.Monster;
import jakarta.validation.Valid;

import java.util.List;
import java.util.Set;

public interface MonstersService {

    void create(MonsterCreateDto input);

    Set<MonsterInitiativeDto> getAllInitiative(Long battleId);

    void updateInitiative( MonsterInitiativeUpdateDto monsterInitiativeUpdateDto);

    void calculateInitiative( MonsterInitiativeDto monster);

    void calculateAllInitiative( List<MonsterInitiativeDto> monsters);

    MonsterFightDto actionsUpdate(MonsterActionsUpdateDtos input);
}
