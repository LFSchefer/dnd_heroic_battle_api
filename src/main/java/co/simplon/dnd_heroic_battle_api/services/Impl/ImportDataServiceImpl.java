package co.simplon.dnd_heroic_battle_api.services.Impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import co.simplon.dnd_heroic_battle_api.entities.Alignment;
import co.simplon.dnd_heroic_battle_api.entities.ArmorClass;
import co.simplon.dnd_heroic_battle_api.entities.Condition;
import co.simplon.dnd_heroic_battle_api.entities.Damage;
import co.simplon.dnd_heroic_battle_api.entities.DamageType;
import co.simplon.dnd_heroic_battle_api.entities.Dc;
import co.simplon.dnd_heroic_battle_api.entities.Language;
import co.simplon.dnd_heroic_battle_api.entities.Monster;
import co.simplon.dnd_heroic_battle_api.entities.MonsterType;
import co.simplon.dnd_heroic_battle_api.entities.Proficiency;
import co.simplon.dnd_heroic_battle_api.entities.Sense;
import co.simplon.dnd_heroic_battle_api.entities.Size;
import co.simplon.dnd_heroic_battle_api.entities.SpecialAbility;
import co.simplon.dnd_heroic_battle_api.entities.Speed;
import co.simplon.dnd_heroic_battle_api.entities.Usage;
import co.simplon.dnd_heroic_battle_api.repositories.AlignmentRepository;
import co.simplon.dnd_heroic_battle_api.repositories.ArmorClassRepository;
import co.simplon.dnd_heroic_battle_api.repositories.ConditionRepository;
import co.simplon.dnd_heroic_battle_api.repositories.DamageRepository;
import co.simplon.dnd_heroic_battle_api.repositories.DamageTypeRepository;
import co.simplon.dnd_heroic_battle_api.repositories.DcRepository;
import co.simplon.dnd_heroic_battle_api.repositories.LanguageRepository;
import co.simplon.dnd_heroic_battle_api.repositories.MonsterRepository;
import co.simplon.dnd_heroic_battle_api.repositories.MonsterTypeRepository;
import co.simplon.dnd_heroic_battle_api.repositories.ProficiencyRepository;
import co.simplon.dnd_heroic_battle_api.repositories.SenseRepository;
import co.simplon.dnd_heroic_battle_api.repositories.SizeRepository;
import co.simplon.dnd_heroic_battle_api.repositories.SpecialAbilityRepository;
import co.simplon.dnd_heroic_battle_api.repositories.SpeedRepository;
import co.simplon.dnd_heroic_battle_api.repositories.UsageRepository;
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
	private final SenseRepository senseRepository;
	private final SpeedRepository speedRepository;
	private final ArmorClassRepository armorClassRepository;
	private final MonsterRepository monsterRepository;
	private final UsageRepository usageRepository;
	private final DcRepository dcRepository;
	private final SpecialAbilityRepository specialAbilityRepository;
	private final DamageRepository damageRepository;
	private final static String BASE_URL = "https://www.dnd5eapi.co";

	public ImportDataServiceImpl(DamageTypeRepository damageTypeRepository, AlignmentRepository alignmentRepository, ConditionRepository conditionRepository,
			LanguageRepository languageRepository, ProficiencyRepository proficiencyRepository, SizeRepository sizeRepository,
			MonsterTypeRepository monsterTypeRepository, SenseRepository senseRepository, SpeedRepository speedRepository,
			ArmorClassRepository armorClassRepository, MonsterRepository monsterRepository, UsageRepository usageRepository, DcRepository dcRepository,
			SpecialAbilityRepository specialAbilityRepository, DamageRepository damageRepository) {
		this.damageTypeRepository = damageTypeRepository;
		this.alignmentRepository = alignmentRepository;
		this.conditionRepository = conditionRepository;
		this.languageRepository = languageRepository;
		this.proficiencyRepository = proficiencyRepository;
		this.sizeRepository = sizeRepository;
		this.monsterTypeRepository = monsterTypeRepository;
		this.senseRepository = senseRepository;
		this.speedRepository = speedRepository;
		this.armorClassRepository = armorClassRepository;
		this.monsterRepository = monsterRepository;
		this.usageRepository = usageRepository;
		this.dcRepository = dcRepository;
		this.specialAbilityRepository = specialAbilityRepository;
		this.damageRepository = damageRepository;
	}

	@Override
	public void importData() {
		RestClient restClient = RestClient.create();
		deleteExisting();
		importDamageTypes(restClient, "/api/damage-types");
		importConditions(restClient, "/api/conditions");
		importLanguages(restClient, "/api/languages");
		importProficiencies(restClient, "/api/proficiencies");
		importAlignments(restClient, "/api/alignments");
		List<String> monsterUrls = getUrlList(restClient, "/api/monsters");
		importFromMonster(restClient, monsterUrls);
		importMonsters(restClient, monsterUrls);
	}

	private void deleteExisting() {
		monsterRepository.deleteAll();
		alignmentRepository.deleteAll();
		damageRepository.deleteAll();
		damageTypeRepository.deleteAll();
		conditionRepository.deleteAll();
		languageRepository.deleteAll();
		proficiencyRepository.deleteAll();
		sizeRepository.deleteAll();
		monsterTypeRepository.deleteAll();
		senseRepository.deleteAll();
		speedRepository.deleteAll();
		armorClassRepository.deleteAll();
		usageRepository.deleteAll();
		dcRepository.deleteAll();
		specialAbilityRepository.deleteAll();
	}

	private void importAlignments(RestClient restClient, String urlType) {
		List<Alignment> alignments = new ArrayList<>();
		List<String> urls = getUrlList(restClient, urlType);
		for (String url : urls) {
			Map<String, Object> alignmentImport = restClient.get().uri(BASE_URL + url).retrieve().body(new ParameterizedTypeReference<>() {
			});
			String name = (String) alignmentImport.get("name");
			String desc = (String) alignmentImport.get("desc");
			alignments.add(Alignment.builder().alignmentsName(name).description(desc).build());
		}
		alignmentRepository.saveAll(alignments);
	}

	private void importDamageTypes(RestClient restClient, String urlType) {
		List<DamageType> damageTypes = new ArrayList<>();
		List<String> urls = getUrlList(restClient, urlType);
		for (String url : urls) {
			Map<String, Object> damageTypeImport = restClient.get().uri(BASE_URL + url).retrieve().body(new ParameterizedTypeReference<>() {
			});
			String name = (String) damageTypeImport.get("name");
			List<String> desc = (List<String>) damageTypeImport.get("desc");
			damageTypes.add(DamageType.builder().damageTypeName(name).description(desc.get(0)).build());
		}
		damageTypeRepository.saveAll(damageTypes);
	}

	private void importConditions(RestClient restClient, String urlType) {
		List<Condition> conditions = new ArrayList<>();
		List<String> urls = getUrlList(restClient, urlType);
		for (String url : urls) {
			Map<String, Object> conditionImport = restClient.get().uri(BASE_URL + url).retrieve().body(new ParameterizedTypeReference<>() {
			});
			String name = (String) conditionImport.get("name");
			List<String> desc = (List<String>) conditionImport.get("desc");
			String description = String.join(System.lineSeparator(), desc);
			conditions.add(Condition.builder().conditionName(name).description(description).build());
		}
		conditionRepository.saveAll(conditions);
	}

	private void importLanguages(RestClient restClient, String urlType) {
		List<Language> languages = new ArrayList<>();
		List<String> urls = getUrlList(restClient, urlType);
		for (String url : urls) {
			Map<String, Object> languageImport = restClient.get().uri(BASE_URL + url).retrieve().body(new ParameterizedTypeReference<>() {
			});
			String name = (String) languageImport.get("name");
			languages.add(Language.builder().languagesName(name).build());
		}
		languageRepository.saveAll(languages);
	}

	private void importProficiencies(RestClient restClient, String urlType) {
		List<Proficiency> proficiencies = new ArrayList<>();
		List<String> urls = getUrlList(restClient, urlType);
		for (String url : urls) {
			Map<String, Object> proficiencyImport = restClient.get().uri(BASE_URL + url).retrieve().body(new ParameterizedTypeReference<>() {
			});
			String name = (String) proficiencyImport.get("name");
			String type = (String) proficiencyImport.get("type");
			Map<String, String> references = (Map<String, String>) proficiencyImport.get("reference");
			String attribute = references.get("name");
			proficiencies.add(Proficiency.builder().proficiencyName(name).proficiencyType(type).proficiencyAttribute(attribute).build());
		}
		proficiencyRepository.saveAll(proficiencies);
	}

	private void importFromMonster(RestClient restClient, List<String> monsterUrls) {
		Set<String> sizes = new HashSet<>();
		Set<String> monsterTypes = new HashSet<>();
		Set<Pair<Integer, String>> senses = new HashSet<>();
		Set<Map<String, Short>> speeds = new HashSet<>();
		Set<Pair<String, Integer>> armorClasses = new HashSet<>();
		Set<Pair<String, Integer>> usages = new HashSet<>();
		Set<Map<String, Object>> dcs = new HashSet<>();
		Set<Map<String, Object>> specialAbilities = new HashSet<>();
		Set<Pair<String, String>> damages = new HashSet<>();
		for (String monsterUrl : monsterUrls) {
			Map<String, Object> monstersImport = restClient.get().uri(BASE_URL + monsterUrl).retrieve().body(new ParameterizedTypeReference<>() {
			});
			// Size
			String size = (String) monstersImport.get("size");
			sizes.add(size);
			// Monster Type
			String type = (String) monstersImport.get("type");
			monsterTypes.add(type);
			// Sense
			Map<String, Object> apiSense = (Map<String, Object>) monstersImport.get("senses");
			Integer passivePerception = (Integer) apiSense.get("passive_perception");
			String darkvision = (String) apiSense.get("darkvision");
			if (darkvision == null) {
				darkvision = "0";
			}
			Pair<Integer, String> sens = Pair.of(passivePerception, darkvision);
			senses.add(sens);
			// Speed
			Map<String, String> speedApi = (Map<String, String>) monstersImport.get("speed");
			Map<String, Short> speed = new HashMap<>();
			Short walk = speedApi.get("walk") == null ? null : Short.valueOf(speedApi.get("walk").replace("ft.", "").trim());
			if (walk != null && walk == 0) {
				walk = null;
			}
			speed.put("walk", walk);
			speed.put("swim", speedApi.get("swim") == null ? null : Short.valueOf(speedApi.get("swim").replace("ft.", "").trim()));
			speed.put("fly", speedApi.get("fly") == null ? null : Short.valueOf(speedApi.get("fly").replace("ft.", "").trim()));
			speeds.add(speed);
			// Armor class
			List<Map<String, Object>> listArmorClasses = (List<Map<String, Object>>) monstersImport.get("armor_class");
			Map<String, Object> ArmorClasseApi = listArmorClasses.get(0);
			String armorType = (String) ArmorClasseApi.get("type");
			Integer armorValue = (Integer) ArmorClasseApi.get("value");
			Pair<String, Integer> armorclass = Pair.of(armorType, armorValue);
			armorClasses.add(armorclass);
			// Usages
			List<Map<String, Object>> actionsApi = (List<Map<String, Object>>) monstersImport.get("actions");
			actionsApi.forEach(action -> {
				Map<String, Object> usage = (Map<String, Object>) action.get("usage");
				if (usage != null) {
					String usageName = (String) usage.get("type");
					Integer usageTime = (Integer) usage.get("times") == null ? 0 : (Integer) usage.get("times");
					Pair<String, Integer> usageValues = Pair.of(usageName, usageTime);
					usages.add(usageValues);
				}
			});
			List<Map<String, Object>> specialAbilitiesApi = (List<Map<String, Object>>) monstersImport.get("special_abilities");
			specialAbilitiesApi.forEach(specialAbility -> {
				Map<String, Object> usage = (Map<String, Object>) specialAbility.get("usage");
				if (usage != null) {
					String usageName = (String) usage.get("type");
					Integer usageTime = (Integer) usage.get("times") == null ? 0 : (Integer) usage.get("times");
					Pair<String, Integer> usageValues = Pair.of(usageName, usageTime);
					usages.add(usageValues);
				}
			});
			// DCs
			actionsApi.forEach(action -> {
				Map<String, Object> dc = (Map<String, Object>) action.get("dc");
				if (dc != null) {
					Map<String, Object> dcCandidate = new HashMap<>();
					Map<String, String> dc_type = (Map<String, String>) dc.get("dc_type");
					String dcType = dc_type.get("index");
					switch (dcType) {
					case "cha":
						dcType = "charisma";
						break;
					case "con":
						dcType = "constitution";
						break;
					case "dex":
						dcType = "dexterity";
						break;
					case "str":
						dcType = "strength";
						break;
					case "wis":
						dcType = "wisdom";
						break;
					default:
						break;
					}
					dcCandidate.put("dcType", dcType);
					dcCandidate.put("dcValue", dc.get("dc_value"));
					dcCandidate.put("successType", dc.get("success_type"));

					dcs.add(dcCandidate);
				}
			});
			// Special abilities
			if (specialAbilitiesApi.size() != 0) {
				specialAbilitiesApi.forEach(specialAbility -> {
					Map<String, Object> specialAbilityMap = new HashMap<String, Object>();
					specialAbilityMap.put("specialAbilityName", specialAbility.get("name"));
					specialAbilityMap.put("specialAbilityDescription", specialAbility.get("desc"));
					specialAbilities.add(specialAbilityMap);
				});

			}
			// Damages
			actionsApi.forEach(action -> {
				List<Map<String, Object>> damageApi = (List<Map<String, Object>>) action.get("damage");
				if (damageApi != null) {
					damageApi.forEach(damage -> {
						String damageDice = (String) damage.get("damage_dice") == null ? "0" : (String) damage.get("damage_dice");
						Map<String, String> damageType = (Map<String, String>) damage.get("damage_type");
						String damageName = damageType == null ? "null" : damageType.get("name");
						Pair<String, String> damageCandidate = Pair.of(damageName, damageDice);
						damages.add(damageCandidate);
					});
				}
			});
		}
		sizeRepository.saveAll(sizes.stream().map(s -> Size.builder().sizeName(s).build()).toList());
		monsterTypeRepository.saveAll(monsterTypes.stream().map(m -> MonsterType.builder().typeName(m).build()).toList());
		senseRepository.saveAll(senses.stream()
				.map(s -> Sense.builder().passivePerception(s.getFirst())
						.darkvision(s.getSecond().replace("ft.", "") == "0" ? null : Integer.valueOf(s.getSecond().replace("ft.", "").trim())).build())
				.toList());
		speedRepository.saveAll(speeds.stream().map(s -> Speed.builder().walk(s.get("walk")).fly(s.get("fly")).swim(s.get("swim")).build()).toList());
		armorClassRepository.saveAll(armorClasses.stream().map(a -> ArmorClass.builder().armorType(a.getFirst()).armorValue(a.getSecond()).build()).toList());
		usageRepository
				.saveAll(usages.stream().map(u -> Usage.builder().usageType(u.getFirst()).time(u.getSecond() == 0 ? null : u.getSecond()).build()).toList());
		dcRepository.saveAll(dcs.stream()
				.map(d -> Dc.builder().dcType((String) d.get("dcType")).dcValue((Integer) d.get("dcValue")).successType((String) d.get("successType")).build())
				.toList());
		specialAbilityRepository.saveAll(specialAbilities.stream().map(s -> SpecialAbility.builder().specialAbilityName((String) s.get("specialAbilityName"))
				.specialAbilityDescription((String) s.get("specialAbilityDescription")).build()).toList());
		damageRepository.saveAll(damages.stream()
				.map(d -> Damage.builder().damageDices(d.getSecond()).damageType(damageTypeRepository.findByDamageTypeName(d.getFirst())).build()).toList());
	}

	private void importMonsters(RestClient restClient, List<String> monsterUrls) {
		List<Monster> monsters = new ArrayList<>();
		for (String monsterUrl : monsterUrls) {
			Map<String, Object> monstersImport = restClient.get().uri(BASE_URL + monsterUrl).retrieve().body(new ParameterizedTypeReference<>() {
			});
			String name = (String) monstersImport.get("name");
			Integer hitPoints = (Integer) monstersImport.get("hit_points");
			String hitDices = (String) monstersImport.get("hit_dice");
			String hitPointsRoll = (String) monstersImport.get("hit_points_roll");
			Integer strength = (Integer) monstersImport.get("strength");
			Integer dexterity = (Integer) monstersImport.get("dexterity");
			Integer constitution = (Integer) monstersImport.get("constitution");
			Integer intelligence = (Integer) monstersImport.get("intelligence");
			Integer wisdom = (Integer) monstersImport.get("wisdom");
			Integer charisma = (Integer) monstersImport.get("charisma");
			Double challengeRating;
			try {
				challengeRating = (Double) monstersImport.get("challenge_rating");
			} catch (Exception e) {
				Integer challengeRatingAsInt = (Integer) monstersImport.get("challenge_rating");
				challengeRating = Double.valueOf(challengeRatingAsInt);
			}
			Integer xp = (Integer) monstersImport.get("xp");
			String imageUrl = (String) monstersImport.get("image");
			// Alignment fkey
			String alignmentsName = (String) monstersImport.get("alignment");
			Alignment alignment = alignmentRepository.findByAlignmentsNameIgnoreCase(alignmentsName);
			// MonsterType fkey
			String monsterTypeName = (String) monstersImport.get("type");
			MonsterType monsterType = monsterTypeRepository.findByTypeName(monsterTypeName);
			// Size fkey
			String sizeName = (String) monstersImport.get("size");
			Size size = sizeRepository.findBySizeName(sizeName);
			// Sense fkey
			Map<String, Object> apiSense = (Map<String, Object>) monstersImport.get("senses");
			Integer passivePerception = (Integer) apiSense.get("passive_perception");
			String darkvisionApi = (String) apiSense.get("darkvision");
			Integer darkvision = darkvisionApi == null ? null : Integer.valueOf(darkvisionApi.replace("ft.", "").trim());
			Sense sense = senseRepository.findByDarkvisionAndPassivePerception(darkvision, passivePerception);
			// Speed fkey
			Map<String, String> speedApi = (Map<String, String>) monstersImport.get("speed");
			Short walk = speedApi.get("walk") == null ? null : Short.valueOf(speedApi.get("walk").replace("ft.", "").trim());
			if (walk != null && walk == 0) {
				walk = null;
			}
			Short swim = speedApi.get("swim") == null ? null : Short.valueOf(speedApi.get("swim").replace("ft.", "").trim());
			Short fly = speedApi.get("fly") == null ? null : Short.valueOf(speedApi.get("fly").replace("ft.", "").trim());
			Speed speed = speedRepository.findByWalkAndSwimAndFly(walk, swim, fly);
			// Armor class fkey
			List<Map<String, Object>> listArmorClasses = (List<Map<String, Object>>) monstersImport.get("armor_class");
			Map<String, Object> ArmorClasseApi = listArmorClasses.get(0);
			String armorType = (String) ArmorClasseApi.get("type");
			Integer armorValue = (Integer) ArmorClasseApi.get("value");
			ArmorClass armorClass = armorClassRepository.findByArmorTypeAndArmorValue(armorType, armorValue);
			monsters.add(Monster.builder().monsterName(name).hitPoints(hitPoints).hitDices(hitDices).hitPointsRoll(hitPointsRoll).strength(strength)
					.dexterity(dexterity).constitution(constitution).intelligence(intelligence).wisdom(wisdom).charisma(charisma)
					.challengeRating(challengeRating).xp(xp).imageUrl(imageUrl == null ? null : BASE_URL + imageUrl).dnd5Native(true).alignment(alignment)
					.monsterType(monsterType).sense(sense).size(size).speed(speed).armorClass(armorClass).build());
		}
		monsterRepository.saveAll(monsters);

	}

	private List<String> getUrlList(RestClient restClient, String urlType) {
		Map<String, Object> object = restClient.get().uri(BASE_URL + urlType).retrieve().body(new ParameterizedTypeReference<>() {
		});
		List<Map<String, String>> results = (List<Map<String, String>>) object.get("results");
		return results.stream().map(r -> r.get("url")).toList();
	}

}
