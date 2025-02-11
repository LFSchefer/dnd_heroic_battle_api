package co.simplon.dnd_heroic_battle_api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import co.simplon.dnd_heroic_battle_api.services.ImportDataService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/import-data")
public class ImportDataController {

	@Autowired
	private ImportDataService service;

	@GetMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public void importData() {
		service.importData();
	}
}
