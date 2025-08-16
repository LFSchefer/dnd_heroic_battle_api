package co.simplon.dnd_heroic_battle_api.services.Impl;

import co.simplon.dnd_heroic_battle_api.dtos.damage_types.DamageTypeDto;
import co.simplon.dnd_heroic_battle_api.mappers.DamageTypeMapper;
import co.simplon.dnd_heroic_battle_api.repositories.DamageTypeRepository;
import co.simplon.dnd_heroic_battle_api.services.DamageTypeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DamageTypeServiceImpl implements DamageTypeService {

    private final DamageTypeRepository repo;

    public DamageTypeServiceImpl(DamageTypeRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<DamageTypeDto> getAll() {
        return DamageTypeMapper.entitiesToDtos(repo.findAll());
    }
}
