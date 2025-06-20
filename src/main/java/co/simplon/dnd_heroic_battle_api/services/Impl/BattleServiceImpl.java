package co.simplon.dnd_heroic_battle_api.services.Impl;

import co.simplon.dnd_heroic_battle_api.dtos.battle.BattleCreate;
import co.simplon.dnd_heroic_battle_api.dtos.battle.BattleDto;
import co.simplon.dnd_heroic_battle_api.dtos.battle.BattleUpdate;
import co.simplon.dnd_heroic_battle_api.dtos.battle.FightDto;
import co.simplon.dnd_heroic_battle_api.entities.Battle;
import co.simplon.dnd_heroic_battle_api.entities.Monster;
import co.simplon.dnd_heroic_battle_api.mappers.BattleMapper;
import co.simplon.dnd_heroic_battle_api.models.BattleModel;
import co.simplon.dnd_heroic_battle_api.repositories.BattleRepository;
import co.simplon.dnd_heroic_battle_api.services.BattleService;
import jakarta.transaction.Transactional;
import org.hibernate.ResourceClosedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class BattleServiceImpl implements BattleService {

    @Autowired
    private BattleRepository repo;

    @Override
    public BattleModel getOne(Long id) {
        return BattleMapper
                .entityToBattleModel(repo.findById(id)
                        .orElseThrow(() -> new ResourceClosedException("Battle with id = " + id + " does not exist")));
    }

    @Override
    public List<BattleDto> getAllFromCampaign(Long id) {
        return BattleMapper.entitiesToBattleViews(repo.findAllByCampaignId(id));
    }

    @Override
    public void deleteOne(Long id) {
        repo.deleteById(id);
    }

    @Override
    public void create(BattleCreate input) {
        repo.save(BattleMapper.battleCreateToEntity(input));
    }

    @Override
    public void update(BattleUpdate input) {
        repo.save(BattleMapper.battleUpdateToEntity(input, repo.findcampaignIdByBattleId(input.id())));
    }

    @Override
    public FightDto getFight(Long id) {
        Battle battle = repo.findById(id)
                .orElseThrow(() -> new ResourceClosedException("Battle with id: " + id + " not found !"));
        if (battle.getTurn() == 0) {
            battle.setTurn(1);
            List<Monster> monsters = battle.getBattleMonsters().stream()
                    .sorted((a, b) -> b.getInitiative().compareTo(a.getInitiative()))
                    .collect(Collectors.toCollection(LinkedList::new));
            monsters.getFirst().setHisTurn(true);
            battle = repo.saveAndFlush(battle);
        }
        return BattleMapper.entityToFightDto(battle);
    }

    @Override
    public FightDto nextTurn(Long battleId) {
        Battle battle = repo.findById(battleId)
                .orElseThrow(() -> new ResourceClosedException("Battle with id: " + battleId + " not found !"));
        battle.setTurn(battle.getTurn() + 1);
        List<Monster> monsters = battle.getBattleMonsters().stream()
                .sorted((a, b) -> b.getInitiative().compareTo(a.getInitiative()))
                .collect(Collectors.toCollection(LinkedList::new));
        monsters.stream().filter(Monster::isHisTurn).findFirst().ifPresent(monster -> {
            monster.setHisTurn(false);
            monster.setHavePlayThisRound(true);
        });
        monsters.stream().filter(m -> !m.isHavePlayThisRound()).findFirst().ifPresent(m -> {
            m.setHisTurn(true);
            m.setAction(false);
            m.setMove(false);
            m.setBonusAction(false);
        });
        battle.setBattleMonsters(new HashSet<>(monsters));
        battle = repo.saveAndFlush(battle);
        return BattleMapper.entityToFightDto(battle);
    }
}
