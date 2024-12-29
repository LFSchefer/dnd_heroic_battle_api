package co.simplon.dnd_heroic_battle_api.controllers;

import co.simplon.dnd_heroic_battle_api.dtos.battle_monsters.BattleMonsterCreateDto;
import co.simplon.dnd_heroic_battle_api.entities.BattleMonster;
import co.simplon.dnd_heroic_battle_api.services.BattleMonstersService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/battle-monsters")
@RequiredArgsConstructor
public class BattleMonsters {

    private final BattleMonstersService service;

    @PostMapping
    public void create(@RequestBody @Valid BattleMonsterCreateDto input) {
        service.create(input);
    }

    @GetMapping("/{id}")
    public BattleMonster get( @PathVariable("id") Long id) {
        return service.get(id);
    }
}
