package co.simplon.dnd_heroic_battle_api.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.simplon.dnd_heroic_battle_api.dtos.campaign.CampaignCreate;
import co.simplon.dnd_heroic_battle_api.services.CampaignService;

@RestController
@RequestMapping("/campaign")
public class CampaignController {

    private final CampaignService service;

    public CampaignController(CampaignService service) {
	this.service = service;
    }

    @PostMapping
    public ResponseEntity<Void> create(CampaignCreate input) {
	service.create(input);
	return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
