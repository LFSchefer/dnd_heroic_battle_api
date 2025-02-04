package co.simplon.dnd_heroic_battle_api.services.Impl;

import java.util.ArrayList;
import java.util.List;

import co.simplon.dnd_heroic_battle_api.dtos.monster_model.MonsterModelCreationDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.simplon.dnd_heroic_battle_api.dtos.monster_model.MonsterModelPreviewDto;
import co.simplon.dnd_heroic_battle_api.dtos.monster_model.MonsterModelSearchDto;
import co.simplon.dnd_heroic_battle_api.repositories.MonsterModelRepository;
import co.simplon.dnd_heroic_battle_api.services.MonsterModelService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MonsterModelServiceImpl implements MonsterModelService {

    private final MonsterModelRepository repo;

    @Override
    public MonsterModelSearchDto get(String name, Integer limit, Integer page) {
        int trueOffset = page == 1 ? 0 : (page - 1) * limit;
        List<MonsterModelPreviewDto> monsterList = new ArrayList<MonsterModelPreviewDto>();
        Integer numberOfResult;
        if (name.isBlank()) {
            monsterList = repo.findAllPreviewDto(limit, trueOffset);
            numberOfResult = repo.countTotal();
        } else {
            monsterList = repo.findByNamePreviewDto(name, limit, trueOffset);
            numberOfResult = repo.countResults(name);
        }
        int numberOfPages = (int) Math.ceil((double) numberOfResult / limit);
        return new MonsterModelSearchDto(monsterList, page, numberOfPages);
    }

    @Override
    public MonsterModelCreationDto getOne(long id) {
        return repo.findByMonsterModelCreationDto(id).orElseThrow( () ->
                new IllegalArgumentException(String.format("MonsterModel with id: %s does not exist", id)));
    }

}
