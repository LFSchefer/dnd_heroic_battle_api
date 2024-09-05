package co.simplon.dnd_heroic_battle_api.services.Impl;

import java.util.List;

import org.springframework.stereotype.Service;

import co.simplon.dnd_heroic_battle_api.dtos.monster.MonsterPreviewDto;
import co.simplon.dnd_heroic_battle_api.repositories.MonsterRepository;
import co.simplon.dnd_heroic_battle_api.services.MonsterService;

@Service
public class MonsterServiceImpl implements MonsterService {

	MonsterRepository repo;

	public MonsterServiceImpl(MonsterRepository repo) {
		this.repo = repo;
	}

	@Override
	public List<MonsterPreviewDto> getAll() {
		return repo.findAllPreviewDtos();
	}
}
