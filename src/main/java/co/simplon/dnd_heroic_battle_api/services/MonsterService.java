package co.simplon.dnd_heroic_battle_api.services;

import java.util.List;

import co.simplon.dnd_heroic_battle_api.dtos.monster.MonsterPreviewDto;

public interface MonsterService {

	List<MonsterPreviewDto> getAll();
}
