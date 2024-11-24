package co.simplon.dnd_heroic_battle_api.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import co.simplon.dnd_heroic_battle_api.dtos.monster.MonsterPreviewDto;
import co.simplon.dnd_heroic_battle_api.services.MonsterService;

@RestController
@RequestMapping("/monsters")
public class MonsterController {

	MonsterService service;

	public MonsterController(MonsterService service) {
		this.service = service;
	}

	@GetMapping
	@ResponseStatus(code = HttpStatus.OK)
	public List<MonsterPreviewDto> getAll() {
		return service.getAll();
	}
}
