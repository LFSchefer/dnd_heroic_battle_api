package co.simplon.dnd_heroic_battle_api.services.Impl;

import java.util.List;

import co.simplon.dnd_heroic_battle_api.dtos.battle.FightDto;
import co.simplon.dnd_heroic_battle_api.entities.Battle;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import co.simplon.dnd_heroic_battle_api.dtos.battle.BattleCreate;
import co.simplon.dnd_heroic_battle_api.dtos.battle.BattleDto;
import co.simplon.dnd_heroic_battle_api.dtos.battle.BattleUpdate;
import co.simplon.dnd_heroic_battle_api.mappers.BattleMapper;
import co.simplon.dnd_heroic_battle_api.models.BattleModel;
import co.simplon.dnd_heroic_battle_api.repositories.BattleRepository;
import co.simplon.dnd_heroic_battle_api.services.BattleService;

@Service
@Transactional
public class BattleServiceImpl implements BattleService {

	@Autowired
	private BattleRepository repo;

	@Override
	public BattleModel getOne(Long id) {
		return BattleMapper
				.entityToBattleModel(repo.findById(id)
						.orElseThrow(() -> new BadCredentialsException("Battle with id = " + id + " does not exist")));
	}

	@Override
	public List<BattleDto> getAllFromCampaign(Long id) {
		return BattleMapper.entitiesToBattleViews(repo.findAllByCampaignId(id));
	}

	@Transactional
	@Override
	public void deleteOne(Long id) {
		repo.deleteById(id);
	}

	@Transactional
	@Override
	public void create(BattleCreate input) {
		repo.save(BattleMapper.battleCreateToEntity(input));
	}

	@Transactional
	@Override
	public void update(BattleUpdate input) {
		repo.save(BattleMapper.battleUpdateToEntity(input, repo.findcampaignIdByBattleId(input.id())));
	}

	@Override
	@Transactional
	public FightDto getFight(Long id) {
		Battle battle = repo.findById(id)
				.orElseThrow(() ->  new BadCredentialsException("Battle with id: " + id + " not found !"));
		if (battle.getTurn() == 0) {
			battle.setTurn(1);
			battle = repo.saveAndFlush(battle);
		}
		return BattleMapper.entityToFightDto(battle);
	}

}
