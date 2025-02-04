package co.simplon.dnd_heroic_battle_api.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import co.simplon.dnd_heroic_battle_api.dtos.monster_model.MonsterModelSearchDto;
import co.simplon.dnd_heroic_battle_api.services.MonsterModelService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/monster-models")
@RequiredArgsConstructor
public class MonsterModelController {

	private final MonsterModelService service;

	@GetMapping
	@ResponseStatus(code = HttpStatus.OK)
	public MonsterModelSearchDto get(@RequestParam(required = false) String name,
                                     @RequestParam Integer limit,
                                     @RequestParam Integer page) {
		return service.get(name, limit, page);
	}

	@GetMapping("/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public Object getOne(@PathVariable("id") long id) {
		return service.getOne(id);
	}
}
