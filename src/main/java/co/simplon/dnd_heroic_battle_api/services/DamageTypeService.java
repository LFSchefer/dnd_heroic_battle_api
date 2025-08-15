package co.simplon.dnd_heroic_battle_api.services;

import co.simplon.dnd_heroic_battle_api.dtos.damage_types.DamageTypeDto;

import java.util.List;

public interface DamageTypeService {
    List<DamageTypeDto> getAll();
}
