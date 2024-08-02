package co.simplon.dnd_heroic_battle_api.services;

import java.util.List;

import co.simplon.dnd_heroic_battle_api.dtos.battle.BattleCreate;
import co.simplon.dnd_heroic_battle_api.dtos.battle.BattleUpdate;
import co.simplon.dnd_heroic_battle_api.dtos.battle.BattleView;

public interface BattleService {

    List<BattleView> getAll();

    BattleView getOne(Long id);

    List<BattleView> getAllFromCampaign(Long id);

    void deleteOne(Long id);

    void create(BattleCreate input);

    void update(BattleUpdate input);

}
