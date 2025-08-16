package co.simplon.dnd_heroic_battle_api.controllers;

import co.simplon.dnd_heroic_battle_api.dtos.damage_types.DamageTypeDto;
import co.simplon.dnd_heroic_battle_api.services.DamageTypeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/damage-types")
public class DamageTypeController {

    private final DamageTypeService damageTypeService;

    public DamageTypeController(DamageTypeService damageTypeService) {
        this.damageTypeService = damageTypeService;
    }

    @GetMapping
    public List<DamageTypeDto> getAll() {
        return damageTypeService.getAll();
    }
}
