package co.simplon.dnd_heroic_battle_api.services.Impl;

import co.simplon.dnd_heroic_battle_api.dtos.monsters.MonsterCreateDto;
import co.simplon.dnd_heroic_battle_api.entities.Monster;
import co.simplon.dnd_heroic_battle_api.entities.MonsterModel;
import co.simplon.dnd_heroic_battle_api.repositories.MonsterRepository;
import co.simplon.dnd_heroic_battle_api.repositories.MonsterModelRepository;
import co.simplon.dnd_heroic_battle_api.services.MonstersService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MonstersServiceImpl implements MonstersService {

    private final MonsterRepository repo;
    private final MonsterModelRepository monsterRepo;

    @Transactional
    @Override
    public void create(MonsterCreateDto input) {
        MonsterModel monster = monsterRepo.findById(input.monsterId())
                .orElseThrow(() -> new IllegalArgumentException(String.format("Monster with id = %s don't exist", input.monsterId())));
        repo.create(input.name(), monster.getHitPoints(), input.monsterId(), input.battleId());
    }

    //TODO
    @Override
    public Monster get(Long id) {
        return repo.findById(id).get();
    }
}
