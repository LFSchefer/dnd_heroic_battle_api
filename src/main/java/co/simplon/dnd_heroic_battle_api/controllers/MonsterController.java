package co.simplon.dnd_heroic_battle_api.controllers;

import co.simplon.dnd_heroic_battle_api.dtos.monsters.*;
import co.simplon.dnd_heroic_battle_api.services.MonstersService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/monsters")
public class MonsterController {

    private final MonstersService service;

    public MonsterController(MonstersService service) {
        this.service = service;
    }

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

    @PutMapping("/update-actions")
    @ResponseStatus(code = HttpStatus.OK)
    public MonsterFightDto updateActions(@RequestBody @Valid MonsterActionsUpdateDtos input) {
        return service.actionsUpdate(input);
    }

    @PatchMapping("/update-hp")
    @ResponseStatus(code = HttpStatus.OK)
    public MonsterFightDto updateHp(@RequestBody @Valid MonsterHpUpdateDto input) {
        return service.updateHp(input);
    }

}
