package co.simplon.dnd_heroic_battle_api.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.simplon.dnd_heroic_battle_api.services.ImportDataService;

@RestController
@RequestMapping("/import-data")
@CrossOrigin("*")
public class ImportDataController {

	private final ImportDataService service;

	public ImportDataController(ImportDataService service) {
		this.service = service;
	}

	@GetMapping
	public ResponseEntity<Void> importData() {
		service.importData();
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
}
