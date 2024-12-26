package co.simplon.dnd_heroic_battle_api.services.Impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.simplon.dnd_heroic_battle_api.dtos.monster.MonsterPreviewDto;
import co.simplon.dnd_heroic_battle_api.repositories.MonsterRepository;
import co.simplon.dnd_heroic_battle_api.services.MonsterService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MonsterServiceImpl implements MonsterService {

	private final MonsterRepository repo;

	@Override
	public List<MonsterPreviewDto> get(String name, Integer limit, Integer offset) {
		return name.isBlank() ? repo.findAllPreviewDto(limit, offset) : repo.findByNamePreviewDto(name,limit,offset);
	}
}
