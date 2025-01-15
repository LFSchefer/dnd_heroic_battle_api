package co.simplon.dnd_heroic_battle_api.services.Impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.simplon.dnd_heroic_battle_api.dtos.monster_model.MonsterPreviewDto;
import co.simplon.dnd_heroic_battle_api.dtos.monster_model.MonsterSearchDto;
import co.simplon.dnd_heroic_battle_api.repositories.MonsterModelRepository;
import co.simplon.dnd_heroic_battle_api.services.MonsterModelService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MonsterModelServiceImpl implements MonsterModelService {

    private final MonsterModelRepository repo;


    @Override
    public MonsterSearchDto get(String name, Integer limit, Integer page) {
        int trueOffset = page == 1 ? 0 : (page - 1) * limit;
        List<MonsterPreviewDto> monsterList = new ArrayList<MonsterPreviewDto>();
        Integer numberOfResult;
        if (name.isBlank()) {
            monsterList = repo.findAllPreviewDto(limit, trueOffset);
            numberOfResult = repo.countTotal();
        } else {
            monsterList = repo.findByNamePreviewDto(name, limit, trueOffset);
            numberOfResult = repo.countResults(name);
        }
        int numberOfPages = (int) Math.ceil((double) numberOfResult / limit);
        return new MonsterSearchDto(monsterList, page, numberOfPages);
    }

}
