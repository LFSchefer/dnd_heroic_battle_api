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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import co.simplon.dnd_heroic_battle_api.dtos.campaign.CampaignCreate;
import co.simplon.dnd_heroic_battle_api.dtos.campaign.CampaignUpdate;
import co.simplon.dnd_heroic_battle_api.models.CampaignModel;
import co.simplon.dnd_heroic_battle_api.services.CampaignService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/campaigns")
public class CampaignController {

	@Autowired
	private CampaignService service;

	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public void create(@Valid @RequestBody CampaignCreate input) {
		service.create(input);
	}

	@GetMapping
	@ResponseStatus(code = HttpStatus.OK)
	public List<CampaignModel> getAllByUserId() {
		return service.getAllByUserId();
	}

	@GetMapping("/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public CampaignModel getOne(@PathVariable("id") long id) {
		return service.getOne(id);
	}

	@DeleteMapping("/{id}")
	public void deleteOne(@PathVariable("id") long id) {
		service.deleteOne(id);
	}

	@PatchMapping
	public void update(@Valid @RequestBody CampaignUpdate input) {
		service.update(input);
	}

}
