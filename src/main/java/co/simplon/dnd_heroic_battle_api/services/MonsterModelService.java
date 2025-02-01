package co.simplon.dnd_heroic_battle_api.services;


import co.simplon.dnd_heroic_battle_api.dtos.monster_model.MonsterModelSearchDto;

public interface MonsterModelService {

	MonsterModelSearchDto get(String name, Integer limit, Integer page);

}
