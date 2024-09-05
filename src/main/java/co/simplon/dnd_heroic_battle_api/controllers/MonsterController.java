package co.simplon.dnd_heroic_battle_api.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.simplon.dnd_heroic_battle_api.dtos.monster.MonsterPreviewDto;
import co.simplon.dnd_heroic_battle_api.services.MonsterService;

@RestController
@RequestMapping("/monsters")
@CrossOrigin("*")
public class MonsterController {

	MonsterService service;

	public MonsterController(MonsterService service) {
		this.service = service;
	}

	@GetMapping
	public ResponseEntity<List<MonsterPreviewDto>> getAll() {
		return ResponseEntity.ok(service.getAll());
	}
}
