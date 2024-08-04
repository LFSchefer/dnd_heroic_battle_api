package co.simplon.dnd_heroic_battle_api.services.Impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import co.simplon.dnd_heroic_battle_api.entities.Alignment;
import co.simplon.dnd_heroic_battle_api.entities.Condition;
import co.simplon.dnd_heroic_battle_api.entities.DamageType;
import co.simplon.dnd_heroic_battle_api.entities.Language;
import co.simplon.dnd_heroic_battle_api.entities.MonsterType;
import co.simplon.dnd_heroic_battle_api.entities.Proficiency;
import co.simplon.dnd_heroic_battle_api.entities.Size;
import co.simplon.dnd_heroic_battle_api.repositories.AlignmentRepository;
import co.simplon.dnd_heroic_battle_api.repositories.ConditionRepository;
import co.simplon.dnd_heroic_battle_api.repositories.DamageTypeRepository;
import co.simplon.dnd_heroic_battle_api.repositories.LanguageRepository;
import co.simplon.dnd_heroic_battle_api.repositories.MonsterTypeRepository;
import co.simplon.dnd_heroic_battle_api.repositories.ProficiencyRepository;
import co.simplon.dnd_heroic_battle_api.repositories.SizeRepository;
import co.simplon.dnd_heroic_battle_api.services.ImportDataService;

@Service
public class ImportDataServiceImpl implements ImportDataService {

    private final DamageTypeRepository damageTypeRepository;
    private final AlignmentRepository alignmentRepository;
    private final ConditionRepository conditionRepository;
    private final LanguageRepository languageRepository;
    private final ProficiencyRepository proficiencyRepository;
    private final SizeRepository sizeRepository;
    private final MonsterTypeRepository monsterTypeRepository;
    private final static String BASE_URL = "https://www.dnd5eapi.co";

    public ImportDataServiceImpl(DamageTypeRepository damageTypeRepository, AlignmentRepository alignmentRepository,
	    ConditionRepository conditionRepository, LanguageRepository languageRepository,
	    ProficiencyRepository proficiencyRepository, SizeRepository sizeRepository,
	    MonsterTypeRepository monsterTypeRepository) {
	this.damageTypeRepository = damageTypeRepository;
	this.alignmentRepository = alignmentRepository;
	this.conditionRepository = conditionRepository;
	this.languageRepository = languageRepository;
	this.proficiencyRepository = proficiencyRepository;
	this.sizeRepository = sizeRepository;
	this.monsterTypeRepository = monsterTypeRepository;
    }

    @Override
    public void importData() {
	RestClient restClient = RestClient.create();
	importDamageTypes(restClient, "/api/damage-types");
	importAlignments(restClient, "/api/alignments");
	importConditions(restClient, "/api/conditions");
	importLanguages(restClient, "/api/languages");
	importProficiencies(restClient, "/api/proficiencies");
	importFromMonster(restClient, "/api/monsters");
    }

    private void importAlignments(RestClient restClient, String urlType) {
	alignmentRepository.deleteAll();
	List<Alignment> alignments = new ArrayList<>();
	List<String> urls = getUrlList(restClient, urlType);
	for (String url : urls) {
	    Map<String, Object> alignmentImport = restClient.get().uri(BASE_URL + url).retrieve()
		    .body(new ParameterizedTypeReference<>() {
		    });
	    String name = (String) alignmentImport.get("name");
	    String desc = (String) alignmentImport.get("desc");
	    alignments.add(Alignment.builder().alignmentsName(name).description(desc).build());
	}
	alignmentRepository.saveAll(alignments);
    }

    private void importDamageTypes(RestClient restClient, String urlType) {
	damageTypeRepository.deleteAll();
	List<DamageType> damageTypes = new ArrayList<>();
	List<String> urls = getUrlList(restClient, urlType);
	for (String url : urls) {
	    Map<String, Object> damageTypeImport = restClient.get().uri(BASE_URL + url).retrieve()
		    .body(new ParameterizedTypeReference<>() {
		    });
	    String name = (String) damageTypeImport.get("name");
	    List<String> desc = (List<String>) damageTypeImport.get("desc");
	    damageTypes.add(DamageType.builder().damageTypeName(name).description(desc.get(0)).build());
	}
	damageTypeRepository.saveAll(damageTypes);
    }

    private void importConditions(RestClient restClient, String urlType) {
	conditionRepository.deleteAll();
	List<Condition> conditions = new ArrayList<>();
	List<String> urls = getUrlList(restClient, urlType);
	for (String url : urls) {
	    Map<String, Object> conditionImport = restClient.get().uri(BASE_URL + url).retrieve()
		    .body(new ParameterizedTypeReference<>() {
		    });
	    String name = (String) conditionImport.get("name");
	    List<String> desc = (List<String>) conditionImport.get("desc");
	    String description = String.join(System.lineSeparator(), desc);
	    conditions.add(Condition.builder().conditionName(name).description(description).build());
	}
	conditionRepository.saveAll(conditions);
    }

    private void importLanguages(RestClient restClient, String urlType) {
	languageRepository.deleteAll();
	List<Language> languages = new ArrayList<>();
	List<String> urls = getUrlList(restClient, urlType);
	for (String url : urls) {
	    Map<String, Object> languageImport = restClient.get().uri(BASE_URL + url).retrieve()
		    .body(new ParameterizedTypeReference<>() {
		    });
	    String name = (String) languageImport.get("name");
	    languages.add(Language.builder().languagesName(name).build());
	}
	languageRepository.saveAll(languages);
    }

    private void importProficiencies(RestClient restClient, String urlType) {
	proficiencyRepository.deleteAll();
	List<Proficiency> proficiencies = new ArrayList<>();
	List<String> urls = getUrlList(restClient, urlType);
	for (String url : urls) {
	    Map<String, Object> proficiencyImport = restClient.get().uri(BASE_URL + url).retrieve()
		    .body(new ParameterizedTypeReference<>() {
		    });
	    String name = (String) proficiencyImport.get("name");
	    String type = (String) proficiencyImport.get("type");
	    Map<String, String> references = (Map<String, String>) proficiencyImport.get("reference");
	    String attribute = references.get("name");
	    proficiencies.add(Proficiency.builder().proficiencyName(name).proficiencyType(type)
		    .proficiencyAttribute(attribute).build());
	}
	proficiencyRepository.saveAll(proficiencies);
    }

    private void importFromMonster(RestClient restClient, String urlType) {
	sizeRepository.deleteAll();
	monsterTypeRepository.deleteAll();
	Set<String> sizes = new HashSet<>();
	Set<String> monsterTypes = new HashSet<>();
	List<String> urls = getUrlList(restClient, urlType);
	for (String url : urls) {
	    Map<String, Object> monstersImport = restClient.get().uri(BASE_URL + url).retrieve()
		    .body(new ParameterizedTypeReference<>() {
		    });
	    String size = (String) monstersImport.get("size");
	    String type = (String) monstersImport.get("type");
	    sizes.add(size);
	    monsterTypes.add(type);
	}
	sizeRepository.saveAll(sizes.stream().map(s -> Size.builder().sizeName(s).build()).toList());
	monsterTypeRepository
		.saveAll(monsterTypes.stream().map(m -> MonsterType.builder().typeName(m).build()).toList());
    }

    private List<String> getUrlList(RestClient restClient, String urlType) {
	Map<String, Object> object = restClient.get().uri(BASE_URL + urlType).retrieve()
		.body(new ParameterizedTypeReference<>() {
		});
	List<Map<String, String>> results = (List<Map<String, String>>) object.get("results");
	return results.stream().map(r -> r.get("url")).toList();
    }

}
