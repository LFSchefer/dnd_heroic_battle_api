package co.simplon.dnd_heroic_battle_api.controllers;

import co.simplon.dnd_heroic_battle_api.dtos.monsters.MonsterInitiativeDto;
import co.simplon.dnd_heroic_battle_api.dtos.monsters.MonsterInitiativeUpdateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import co.simplon.dnd_heroic_battle_api.dtos.monsters.MonsterCreateDto;
import co.simplon.dnd_heroic_battle_api.services.MonstersService;
import jakarta.validation.Valid;

import java.util.List;
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

    @PutMapping("/update-initiative")
    @ResponseStatus(code = HttpStatus.OK)
    public void updateInitiative(@RequestBody @Valid MonsterInitiativeUpdateDto monsterInitiativeUpdateDto) {
        service.updateInitiative(monsterInitiativeUpdateDto);
    }

    @PutMapping("/calculate-initiative")
    @ResponseStatus(code = HttpStatus.OK)
    public void calculateInitiative(@RequestBody @Valid MonsterInitiativeDto monster) {
        service.calculateInitiative(monster);
    }

    @PutMapping("/calculate-all-initiative")
    @ResponseStatus(code = HttpStatus.OK)
    public void calculateAllInitiative(@RequestBody @Valid List<MonsterInitiativeDto> monsters) {
        service.calculateAllInitiative(monsters);
    }

}
