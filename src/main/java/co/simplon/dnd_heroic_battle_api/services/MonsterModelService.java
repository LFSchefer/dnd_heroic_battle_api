package co.simplon.dnd_heroic_battle_api.services;


import co.simplon.dnd_heroic_battle_api.dtos.monster_model.MonsterModelCreationDto;
import co.simplon.dnd_heroic_battle_api.dtos.monster_model.MonsterModelDetail;
import co.simplon.dnd_heroic_battle_api.dtos.monster_model.MonsterModelSearchDto;
import co.simplon.dnd_heroic_battle_api.entities.MonsterModel;

public interface MonsterModelService {

	MonsterModelSearchDto get(String name, Integer limit, Integer page);

    MonsterModelCreationDto getOne(long id);

    MonsterModelDetail getDetail(long id);
}
