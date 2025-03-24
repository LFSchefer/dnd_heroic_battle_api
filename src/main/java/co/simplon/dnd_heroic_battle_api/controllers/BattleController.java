package co.simplon.dnd_heroic_battle_api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import co.simplon.dnd_heroic_battle_api.dtos.battle.BattleCreate;
import co.simplon.dnd_heroic_battle_api.dtos.battle.BattleDto;
import co.simplon.dnd_heroic_battle_api.dtos.battle.BattleUpdate;
import co.simplon.dnd_heroic_battle_api.models.BattleModel;
import co.simplon.dnd_heroic_battle_api.services.BattleService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/battles")
public class BattleController {

	@Autowired
	private BattleService service;

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
}
