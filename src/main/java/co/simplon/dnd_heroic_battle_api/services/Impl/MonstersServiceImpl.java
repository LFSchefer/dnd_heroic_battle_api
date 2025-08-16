package co.simplon.dnd_heroic_battle_api.services.Impl;

import co.simplon.dnd_heroic_battle_api.components.DiceRoller;
import co.simplon.dnd_heroic_battle_api.dtos.monsters.*;
import co.simplon.dnd_heroic_battle_api.entities.DamageType;
import co.simplon.dnd_heroic_battle_api.entities.Monster;
import co.simplon.dnd_heroic_battle_api.enums.DamageHeal;
import co.simplon.dnd_heroic_battle_api.mappers.MonstersMapper;
import co.simplon.dnd_heroic_battle_api.repositories.DamageTypeRepository;
import co.simplon.dnd_heroic_battle_api.repositories.MonsterRepository;
import co.simplon.dnd_heroic_battle_api.services.MonstersService;
import jakarta.transaction.Transactional;
import org.hibernate.ResourceClosedException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@Transactional
public class MonstersServiceImpl implements MonstersService {


    private final MonsterRepository repo;
    private final DiceRoller diceRoller;
    private final DamageTypeRepository damageTypeRepository;

    public MonstersServiceImpl(MonsterRepository repo, DiceRoller diceRoller, DamageTypeRepository damageTypeRepository) {
        this.repo = repo;
        this.diceRoller = diceRoller;
        this.damageTypeRepository = damageTypeRepository;
    }

    @Override
    public void create(MonsterCreateDto input) {
        repo.create(input.name(), input.currentHitPoints(), input.maxHitPoints(), input.modelId(), input.battleId());
    }

    @Override
    public Set<MonsterInitiativeDto> getAllInitiative(Long battleId) {
        Set<MonsterInitiativePro> monsterInitiativePros = repo.getAllInitiativeByBattle(battleId);
        return MonstersMapper.InitiativeProsToInitiativeDtos(monsterInitiativePros);
    }

    @Override
    public void updateInitiative(MonsterInitiativeUpdateDto monsterInitiativeUpdateDto) {
        Monster monster = repo.findById(monsterInitiativeUpdateDto.monsterId())
                .orElseThrow(() -> new ResourceClosedException("Monster with id: " + monsterInitiativeUpdateDto.monsterId() + " not found"));
        monster.setInitiative(monsterInitiativeUpdateDto.initiative());
        repo.save(monster);
    }

    @Override
    public void calculateInitiative(MonsterInitiativeDto monster) {
        Monster monsterEntity = repo.findById(monster.id())
                .orElseThrow(() -> new ResourceClosedException("Monster with id: " + monster.id() + " not found"));
        monsterEntity.setInitiative(diceRoller.d20(monster.bonus()));
        repo.save(monsterEntity);
    }

    @Override
    public void calculateAllInitiative(List<MonsterInitiativeDto> monstersDto) {
        List<Monster> monstersEntity = repo.findAllById(monstersDto.stream().map(MonsterInitiativeDto::id).toList());
        monstersEntity.forEach(monster ->
        {
            if (monster.getInitiative() == null) {
                monster.setInitiative(diceRoller.d20(
                        monstersDto.stream()
                                .filter(m -> m.id().equals(monster.getMonsterId())).findFirst()
                                .orElseThrow(() -> new ResourceClosedException("Monster not found"))
                                .bonus()
                ));
            }
        });
        repo.saveAll(monstersEntity);
    }

    @Override
    public MonsterFightDto actionsUpdate(MonsterActionsUpdateDtos input) {
        Monster monster = repo.findById(input.monsterId())
                .orElseThrow(() -> new ResourceClosedException("Monster not found"));
        monster.setAction(input.action());
        monster.setMove(input.move());
        monster.setBonusAction(input.bonusAction());
        return MonstersMapper.entityToFightDto(repo.saveAndFlush(monster));
    }

    @Override
    public MonsterFightDto updateHp(MonsterHpUpdateDto input) {
        Monster monster = repo.findById(input.monsterId())
                .orElseThrow(() -> new ResourceClosedException("Monster not found"));
        int updatedHp;
        if (DamageHeal.isDamage(input.type())) {
            updatedHp = calculateDamage(monster, input);
        } else if (DamageHeal.isHeal(input.type())) {
            updatedHp = calculateHeal(monster, input);
        } else {
            throw new IllegalArgumentException("Type " + input.type() + "not found");
        }
        monster.setCurrentHitPoints(updatedHp);
        return MonstersMapper.entityToFightDto(repo.saveAndFlush(monster));
    }

    int calculateDamage(Monster monster, MonsterHpUpdateDto input) {
        int updatedHp;
        if (input.damageTypeId() == null) {
            updatedHp = monster.getCurrentHitPoints() - input.amount();
        } else {
            DamageType damageType = damageTypeRepository.findById(input.damageTypeId())
                    .orElseThrow(() -> new ResourceClosedException("damage type with id " + input.damageTypeId() + " not found"));
            if (monster.getMonster().getMonsterImunities().contains(damageType)) {
                updatedHp = monster.getCurrentHitPoints();
            } else if (monster.getMonster().getMonsterVulnerabilities().contains(damageType)) {
                updatedHp = monster.getCurrentHitPoints() - (input.amount() * 2);
            } else if (monster.getMonster().getMonsterResistances().contains(damageType)) {
                updatedHp = monster.getCurrentHitPoints() - (input.amount() / 2);
            } else {
                updatedHp = monster.getCurrentHitPoints() - input.amount();
            }
        }
        if (updatedHp < 0) {
            updatedHp = 0;
        }
        return updatedHp;
    }

    int calculateHeal(Monster monster, MonsterHpUpdateDto input) {
        int updatedHp;
        updatedHp = monster.getCurrentHitPoints() + input.amount();
        if (updatedHp > monster.getMaxHitPoints()) {
            updatedHp = monster.getMaxHitPoints();
        }
        return updatedHp;
    }
}
