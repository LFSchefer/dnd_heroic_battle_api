package co.simplon.dnd_heroic_battle_api.services;


import co.simplon.dnd_heroic_battle_api.dtos.monster.MonsterSearchDto;

public interface MonsterService {

	MonsterSearchDto get(String name, Integer limit, Integer page);
}
