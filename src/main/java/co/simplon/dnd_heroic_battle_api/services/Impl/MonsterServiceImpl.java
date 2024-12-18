package co.simplon.dnd_heroic_battle_api.services.Impl;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import co.simplon.dnd_heroic_battle_api.dtos.monster.MonsterPreviewDto;
import co.simplon.dnd_heroic_battle_api.repositories.MonsterRepository;
import co.simplon.dnd_heroic_battle_api.services.MonsterService;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MonsterServiceImpl implements MonsterService {

	MonsterRepository repo;

	@Override
	@Transactional
	public List<MonsterPreviewDto> getAll() {
		return repo.findAllPreviewDto();
	}
}
