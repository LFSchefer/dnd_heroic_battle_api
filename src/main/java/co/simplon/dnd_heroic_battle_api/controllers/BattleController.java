package co.simplon.dnd_heroic_battle_api.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.simplon.dnd_heroic_battle_api.dtos.battle.BattleCreate;
import co.simplon.dnd_heroic_battle_api.dtos.battle.BattleUpdate;
import co.simplon.dnd_heroic_battle_api.models.BattleModel;
import co.simplon.dnd_heroic_battle_api.services.BattleService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/battles")
public class BattleController {

	private final BattleService service;

	public BattleController(BattleService service) {
		this.service = service;
	}

	@GetMapping
	public ResponseEntity<List<BattleModel>> getAll() {
		return ResponseEntity.ok(service.getAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<BattleModel> getOne(@PathVariable("id") Long id) {
		return ResponseEntity.ok(service.getOne(id));
	}

	@GetMapping("/campaign")
	public ResponseEntity<List<BattleModel>> getAllFromCampaign(@RequestParam("id") Long id) {
		return ResponseEntity.ok(service.getAllFromCampaign(id));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteOne(@PathVariable("id") Long id) {
		service.deleteOne(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Void> create(@Valid @RequestBody BattleCreate input) {
		service.create(input);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@PatchMapping
	public ResponseEntity<Void> update(@Valid @RequestBody BattleUpdate input) {
		service.update(input);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
