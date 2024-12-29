package co.simplon.dnd_heroic_battle_api.services.Impl;

import co.simplon.dnd_heroic_battle_api.dtos.battle_monsters.BattleMonsterCreateDto;
import co.simplon.dnd_heroic_battle_api.entities.BattleMonster;
import co.simplon.dnd_heroic_battle_api.repositories.BattleMonstersRepository;
import co.simplon.dnd_heroic_battle_api.services.BattleMonstersService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BattleMonstersServiceImpl implements BattleMonstersService {

    private final BattleMonstersRepository repo;

    @Transactional
    @Override
    public void create(BattleMonsterCreateDto input) {
        repo.create(input.name(), input.currentHitPoints(), input.monsterId(), input.battleId());
    }

    @Override
    public BattleMonster get(Long id) {
        return repo.findById(id).get();
    }
}
