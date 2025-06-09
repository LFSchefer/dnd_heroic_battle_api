package co.simplon.dnd_heroic_battle_api.services;

import java.util.List;

import co.simplon.dnd_heroic_battle_api.dtos.battle.BattleCreate;
import co.simplon.dnd_heroic_battle_api.dtos.battle.BattleUpdate;
import co.simplon.dnd_heroic_battle_api.dtos.battle.BattleDto;
import co.simplon.dnd_heroic_battle_api.dtos.battle.FightDto;
import co.simplon.dnd_heroic_battle_api.models.BattleModel;

public interface BattleService {

	BattleModel getOne(Long id);

	List<BattleDto> getAllFromCampaign(Long id);

	void deleteOne(Long id);

	void create(BattleCreate input);

	void update(BattleUpdate input);

	FightDto getFight(Long id);
}
