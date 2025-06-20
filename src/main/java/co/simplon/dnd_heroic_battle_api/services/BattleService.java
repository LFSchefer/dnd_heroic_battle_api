package co.simplon.dnd_heroic_battle_api.services;

import co.simplon.dnd_heroic_battle_api.dtos.battle.BattleCreate;
import co.simplon.dnd_heroic_battle_api.dtos.battle.BattleDto;
import co.simplon.dnd_heroic_battle_api.dtos.battle.BattleUpdate;
import co.simplon.dnd_heroic_battle_api.dtos.battle.FightDto;
import co.simplon.dnd_heroic_battle_api.models.BattleModel;

import java.util.List;

public interface BattleService {

    BattleModel getOne(Long id);

    List<BattleDto> getAllFromCampaign(Long id);

    void deleteOne(Long id);

    void create(BattleCreate input);

    void update(BattleUpdate input);

    FightDto getFight(Long id);

    FightDto nextTurn(Long battleId);
}
