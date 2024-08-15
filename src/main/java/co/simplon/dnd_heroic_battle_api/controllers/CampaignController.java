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
import org.springframework.web.bind.annotation.RestController;

import co.simplon.dnd_heroic_battle_api.dtos.campaign.CampaignCreate;
import co.simplon.dnd_heroic_battle_api.dtos.campaign.CampaignUpdate;
import co.simplon.dnd_heroic_battle_api.models.CampaignModel;
import co.simplon.dnd_heroic_battle_api.services.CampaignService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/campaigns")
public class CampaignController {

	private final CampaignService service;

	public CampaignController(CampaignService service) {
		this.service = service;
	}

	@PostMapping
	public ResponseEntity<Void> create(@Valid @RequestBody CampaignCreate input) {
		service.create(input);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@GetMapping
	public ResponseEntity<List<CampaignModel>> getAll() {
		return ResponseEntity.ok(service.getAll());
	}

	@GetMapping("{id}")
	public ResponseEntity<CampaignModel> getone(@PathVariable("id") long id) {
		return ResponseEntity.ok(service.getOne(id));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteOne(@PathVariable("id") long id) {
		service.deleteOne(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PatchMapping
	public ResponseEntity<Void> update(@Valid @RequestBody CampaignUpdate input) {
		service.update(input);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
