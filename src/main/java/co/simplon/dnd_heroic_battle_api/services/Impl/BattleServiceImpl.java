package co.simplon.dnd_heroic_battle_api.services.Impl;

import java.util.List;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.simplon.dnd_heroic_battle_api.dtos.battle.BattleCreate;
import co.simplon.dnd_heroic_battle_api.dtos.battle.BattleDto;
import co.simplon.dnd_heroic_battle_api.dtos.battle.BattleUpdate;
import co.simplon.dnd_heroic_battle_api.mappers.BattleMapper;
import co.simplon.dnd_heroic_battle_api.models.BattleModel;
import co.simplon.dnd_heroic_battle_api.repositories.BattleRepository;
import co.simplon.dnd_heroic_battle_api.services.BattleService;

@Service
public class BattleServiceImpl implements BattleService {

	private final BattleRepository repo;

	public BattleServiceImpl(BattleRepository repo) {
		this.repo = repo;
	}

	@Transactional(readOnly = true)
	@Override
	public List<BattleModel> getAll() {
		return BattleMapper.entitiesToBattleModel(repo.findAll());
	}

	@Transactional(readOnly = true)
	@Override
	public BattleModel getOne(Long id) {
		return BattleMapper
				.entityToBattleModel(repo.findById(id)
						.orElseThrow(() -> new BadCredentialsException("Battle with id = " + id + " does not exist")));
	}

	@Transactional(readOnly = true)
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

}
