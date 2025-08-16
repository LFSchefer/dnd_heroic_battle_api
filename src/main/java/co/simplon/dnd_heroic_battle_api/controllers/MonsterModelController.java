package co.simplon.dnd_heroic_battle_api.controllers;

import co.simplon.dnd_heroic_battle_api.dtos.monster_model.MonsterModelCreationPro;
import co.simplon.dnd_heroic_battle_api.dtos.monster_model.MonsterModelDetail;
import co.simplon.dnd_heroic_battle_api.dtos.monster_model.MonsterModelSearchDto;
import co.simplon.dnd_heroic_battle_api.services.MonsterModelService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/monster-models")
public class MonsterModelController {

    private final MonsterModelService service;

    public MonsterModelController(MonsterModelService service) {
        this.service = service;
    }

    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    public MonsterModelSearchDto get(@RequestParam(required = false) String name,
                                     @RequestParam Integer limit,
                                     @RequestParam Integer page) {
        return service.get(name, limit, page);
    }

    @GetMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public MonsterModelCreationPro getOne(@PathVariable("id") long id) {
        return service.getOne(id);
    }

    @GetMapping("/details/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public MonsterModelDetail getDetail(@PathVariable("id") long id) {
        return service.getDetail(id);
    }
}
