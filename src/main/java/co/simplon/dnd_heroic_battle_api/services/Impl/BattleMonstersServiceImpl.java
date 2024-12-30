package co.simplon.dnd_heroic_battle_api.services.Impl;

import co.simplon.dnd_heroic_battle_api.dtos.battle_monsters.BattleMonsterCreateDto;
import co.simplon.dnd_heroic_battle_api.entities.BattleMonster;
import co.simplon.dnd_heroic_battle_api.entities.Monster;
import co.simplon.dnd_heroic_battle_api.repositories.BattleMonstersRepository;
import co.simplon.dnd_heroic_battle_api.repositories.MonsterRepository;
import co.simplon.dnd_heroic_battle_api.services.BattleMonstersService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BattleMonstersServiceImpl implements BattleMonstersService {

    private final BattleMonstersRepository repo;
    private final MonsterRepository monsterRepo;

    @Transactional
    @Override
    public void create(BattleMonsterCreateDto input) {
        Monster monster = monsterRepo.findById(input.monsterId())
                .orElseThrow(() -> new IllegalArgumentException(String.format("Monster with id = %s don't exist", input.monsterId())));
        repo.create(input.name(), monster.getHitPoints(), input.monsterId(), input.battleId());
    }

    //TODO
    @Override
    public BattleMonster get(Long id) {
        return repo.findById(id).get();
    }
}
