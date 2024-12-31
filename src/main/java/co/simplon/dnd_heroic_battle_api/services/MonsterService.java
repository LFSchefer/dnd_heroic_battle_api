package co.simplon.dnd_heroic_battle_api.services;


import co.simplon.dnd_heroic_battle_api.dtos.monster.MonsterSearchDto;
import co.simplon.dnd_heroic_battle_api.entities.Monster;

public interface MonsterService {

	MonsterSearchDto get(String name, Integer limit, Integer page);

	Monster test(Long id);
}
