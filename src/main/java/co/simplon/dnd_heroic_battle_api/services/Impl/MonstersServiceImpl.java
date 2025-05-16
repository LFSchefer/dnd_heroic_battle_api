package co.simplon.dnd_heroic_battle_api.services.Impl;

import co.simplon.dnd_heroic_battle_api.dtos.monsters.MonsterCreateDto;
import co.simplon.dnd_heroic_battle_api.dtos.monsters.MonsterInitiativeDto;
import co.simplon.dnd_heroic_battle_api.dtos.monsters.MonsterInitiativePro;
import co.simplon.dnd_heroic_battle_api.entities.Monster;
import co.simplon.dnd_heroic_battle_api.entities.MonsterModel;
import co.simplon.dnd_heroic_battle_api.mappers.MonstersMapper;
import co.simplon.dnd_heroic_battle_api.repositories.MonsterRepository;
import co.simplon.dnd_heroic_battle_api.repositories.MonsterModelRepository;
import co.simplon.dnd_heroic_battle_api.services.MonstersService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;

@Service
@Transactional(readOnly = true)
public class MonstersServiceImpl implements MonstersService {

    @Autowired
    private MonsterRepository repo;

    @Transactional
    @Override
    public void create(MonsterCreateDto input) {
        repo.create(input.name(), input.currentHitPoints(), input.maxHitPoints(), input.modelId(), input.battleId());
    }

    @Override
    public Set<MonsterInitiativeDto> getAllInitiative(Long battleId) {
        Set<MonsterInitiativePro> monsterInitiativePros = repo.getAllInitiativeByBattle(battleId);
        return MonstersMapper.InitiativeProsToInitiativeDtos(monsterInitiativePros);
    }

}
