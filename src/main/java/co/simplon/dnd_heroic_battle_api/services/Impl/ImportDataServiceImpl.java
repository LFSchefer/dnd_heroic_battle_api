package co.simplon.dnd_heroic_battle_api.services.Impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestClient;

import co.simplon.dnd_heroic_battle_api.entities.Alignment;
import co.simplon.dnd_heroic_battle_api.entities.DamageType;
import co.simplon.dnd_heroic_battle_api.repositories.AlignmentRepository;
import co.simplon.dnd_heroic_battle_api.repositories.DamageTypeRepository;
import co.simplon.dnd_heroic_battle_api.services.ImportDataService;

@Service
public class ImportDataServiceImpl implements ImportDataService {

    private final DamageTypeRepository damageTypeRepository;
    private final AlignmentRepository alignmentRepository;

    public ImportDataServiceImpl(DamageTypeRepository damageTypeRepository, AlignmentRepository alignmentRepository) {
	this.damageTypeRepository = damageTypeRepository;
	this.alignmentRepository = alignmentRepository;
    }

    private final static String BASE_URL = "https://www.dnd5eapi.co";

    @Override
    @Transactional
    public void importData() {
	damageTypeRepository.deleteAll();
	alignmentRepository.deleteAll();
	RestClient restClient = RestClient.create();
	importDamageTypes(restClient, "/api/damage-types");
	importAlignments(restClient, "/api/alignments");
    }

    private void importAlignments(RestClient restClient, String url) {
	List<Alignment> alignments = new ArrayList<>();

	Map<String, Object> allAlignments = restClient.get().uri(BASE_URL + url).retrieve()
		.body(new ParameterizedTypeReference<>() {
		});
	List<Map<String, String>> results = (List<Map<String, String>>) allAlignments.get("results");

	for (Map<String, String> result : results) {

	    Map<String, Object> alignmentImport = restClient.get().uri("https://www.dnd5eapi.co" + result.get("url"))
		    .retrieve().body(new ParameterizedTypeReference<>() {
		    });
	    String name = (String) alignmentImport.get("name");
	    String desc = (String) alignmentImport.get("desc");
	    alignments.add(Alignment.builder().alignmentsName(name).description(desc).build());
	}

	alignmentRepository.saveAll(alignments);
    }

    private void importDamageTypes(RestClient restClient, String url) {

	List<DamageType> damageTypes = new ArrayList<>();

	Map<String, Object> allDamageType = restClient.get().uri(BASE_URL + url).retrieve()
		.body(new ParameterizedTypeReference<>() {
		});
	List<Map<String, String>> results = (List<Map<String, String>>) allDamageType.get("results");

	for (Map<String, String> result : results) {

	    Map<String, Object> damageTypeImport = restClient.get().uri("https://www.dnd5eapi.co" + result.get("url"))
		    .retrieve().body(new ParameterizedTypeReference<>() {
		    });
	    String name = (String) damageTypeImport.get("name");
	    List<String> desc = (List<String>) damageTypeImport.get("desc");
	    damageTypes.add(DamageType.builder().damageTypeName(name).description(desc.get(0)).build());

	}

	damageTypeRepository.saveAll(damageTypes);
    }

}
