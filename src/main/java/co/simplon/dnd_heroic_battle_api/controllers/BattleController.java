package co.simplon.dnd_heroic_battle_api.controllers;

import co.simplon.dnd_heroic_battle_api.dtos.battle.BattleCreate;
import co.simplon.dnd_heroic_battle_api.dtos.battle.BattleDto;
import co.simplon.dnd_heroic_battle_api.dtos.battle.BattleUpdate;
import co.simplon.dnd_heroic_battle_api.dtos.battle.FightDto;
import co.simplon.dnd_heroic_battle_api.models.BattleModel;
import co.simplon.dnd_heroic_battle_api.services.BattleService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/battles")
public class BattleController {

    private final BattleService service;

    public BattleController(BattleService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public BattleModel getOne(@PathVariable("id") Long id) {
        return service.getOne(id);
    }

    @GetMapping("/campaign")
    @ResponseStatus(code = HttpStatus.OK)
    public List<BattleDto> getAllFromCampaign(@RequestParam("id") Long id) {
        return service.getAllFromCampaign(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public void deleteOne(@PathVariable("id") Long id) {
        service.deleteOne(id);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public void create(@Valid @RequestBody BattleCreate input) {
        service.create(input);
    }

    @PatchMapping
    @ResponseStatus(code = HttpStatus.OK)
    public void update(@Valid @RequestBody BattleUpdate input) {
        service.update(input);
    }

    @GetMapping("/{id}/fight")
    @ResponseStatus(code = HttpStatus.OK)
    public FightDto getFight(@PathVariable("id") Long id) {
        return service.getFight(id);
    }

    @PatchMapping("/{id}/next-turn")
    @ResponseStatus(code = HttpStatus.OK)
    public FightDto nextTurn(@PathVariable("id") Long battleId) {
        return service.nextTurn(battleId);
    }
}
