package co.simplon.dnd_heroic_battle_api.controllers;

import co.simplon.dnd_heroic_battle_api.dtos.monsters.MonsterInitiativeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import co.simplon.dnd_heroic_battle_api.dtos.monsters.MonsterCreateDto;
import co.simplon.dnd_heroic_battle_api.entities.Monster;
import co.simplon.dnd_heroic_battle_api.services.MonstersService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.Set;

@RestController
@RequestMapping("/api/monsters")
public class MonsterController {

    @Autowired
    private MonstersService service;

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public void create(@RequestBody @Valid MonsterCreateDto input) {
        service.create(input);
    }

    @GetMapping("/get-initiatives")
    @ResponseStatus(code = HttpStatus.OK)
    public Set<MonsterInitiativeDto> getInitiative(@RequestParam("battle") Long battleId) {
        return service.getAllInitiative(battleId);
    }

}
