package co.simplon.dnd_heroic_battle_api.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import co.simplon.dnd_heroic_battle_api.dtos.monster.MonsterSearchDto;
import co.simplon.dnd_heroic_battle_api.entities.Monster;
import co.simplon.dnd_heroic_battle_api.services.MonsterService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/monsters")
@RequiredArgsConstructor
public class MonsterController {

	private final MonsterService service;

	@GetMapping
	@ResponseStatus(code = HttpStatus.OK)
	public MonsterSearchDto get(@RequestParam(required = false) String name,
								@RequestParam Integer limit,
								@RequestParam Integer page) {
		return service.get(name, limit, page);
	}
	
	@GetMapping("/test")
	public Monster test(@RequestParam("id") Long id) {
		return service.test(id);
	}
}
