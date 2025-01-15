package co.simplon.dnd_heroic_battle_api.entities;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Entity
@Table(name = "monster_models")
@Builder
@AllArgsConstructor
public class MonsterModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "model_id")
	private Long monsterId;

	@Column(name = "monster_name")
	private String monsterName;

	@Column(name = "hit_points")
	private Integer hitPoints;

	@Column(name = "hit_points_roll")
	private String hitPointsRoll;

	@Column(name = "strength")
	private Integer strength;

	@Column(name = "dexterity")
	private Integer dexterity;

	@Column(name = "constitution")
	private Integer constitution;

	@Column(name = "intelligence")
	private Integer intelligence;

	@Column(name = "wisdom")
	private Integer wisdom;

	@Column(name = "charisma")
	private Integer charisma;

	@Column(name = "challenge_rating")
	private Double challengeRating;

	@Column(name = "xp")
	private Integer xp;

	@Column(name = "image_url")
	private String imageUrl;

	@Column(name = "dnd5_native")
	private boolean dnd5Native;
	
	@Column(name = "passive_perception")
	private Integer passivePerception;
	
	@Column(name = "darkvision")
	private Integer darkvision;
	
	@Column(name = "walk")
	private Integer walk;
	
	@Column(name = "swim")
	private Integer swim;
	
	@Column(name = "fly")
	private Integer fly;

	@ManyToOne(targetEntity = Alignment.class)
	@JoinColumn(name = "alignment_id")
	private Alignment alignment;

	@ManyToOne(targetEntity = MonsterType.class)
	@JoinColumn(name = "monster_type_id")
	private MonsterType monsterType;

	@ManyToOne(targetEntity = Size.class)
	@JoinColumn(name = "size_id")
	private Size size;

	@ManyToOne(targetEntity = ArmorClass.class)
	@JoinColumn(name = "armor_id")
	private ArmorClass armorClass;

	@ManyToMany
	@JoinTable(name = "monster_languages", 
	joinColumns = @JoinColumn(name = "monster_id"),
	inverseJoinColumns = @JoinColumn( name = "language_id"))
	private Set<Language> languages = new HashSet<Language>();
	
	@ManyToMany
	@JoinTable(name = "monster_condition_immunities",
	joinColumns = @JoinColumn(name = "monster_id"),
	inverseJoinColumns = @JoinColumn(name = "condition_id"))
	private Set<Condition> conditionsImmunities = new HashSet<Condition>();
	
	@ManyToMany
	@JoinTable( name = "monster_vulnerabilities",
	joinColumns = @JoinColumn(name = "monster_id"),
	inverseJoinColumns = @JoinColumn(name = "vulnerability_id"))
	private Set<DamageType> monsterVulnerabilities = new HashSet<DamageType>();
	
	@ManyToMany
	@JoinTable( name = "monster_resistances",
	joinColumns = @JoinColumn(name = "monster_id"),
	inverseJoinColumns = @JoinColumn(name = "resistance_id"))
	private Set<DamageType> monsterResistances = new HashSet<DamageType>();
	
	@ManyToMany
	@JoinTable( name = "monster_imunities",
	joinColumns = @JoinColumn(name = "monster_id"),
	inverseJoinColumns = @JoinColumn(name = "imunity_id"))
	private Set<DamageType> monsterImunities = new HashSet<DamageType>();

	public MonsterModel() {
		// ORM
	}

	public Long getMonsterId() {
		return monsterId;
	}

	@SuppressWarnings("unused")
	private void setMonsterId(Long monsterId) {
		this.monsterId = monsterId;
	}

	public String getMonsterName() {
		return monsterName;
	}

	public void setMonsterName(String monsterName) {
		this.monsterName = monsterName;
	}

	public Integer getHitPoints() {
		return hitPoints;
	}

	public void setHitPoints(Integer hitPoints) {
		this.hitPoints = hitPoints;
	}

	public String getHitPointsRoll() {
		return hitPointsRoll;
	}

	public void setHitPointsRoll(String hitPointsRoll) {
		this.hitPointsRoll = hitPointsRoll;
	}

	public Integer getStrength() {
		return strength;
	}

	public void setStrength(Integer strength) {
		this.strength = strength;
	}

	public Integer getDexterity() {
		return dexterity;
	}

	public void setDexterity(Integer dexterity) {
		this.dexterity = dexterity;
	}

	public Integer getConstitution() {
		return constitution;
	}

	public void setConstitution(Integer constitution) {
		this.constitution = constitution;
	}

	public Integer getIntelligence() {
		return intelligence;
	}

	public void setIntelligence(Integer intelligence) {
		this.intelligence = intelligence;
	}

	public Integer getWisdom() {
		return wisdom;
	}

	public void setWisdom(Integer wisdom) {
		this.wisdom = wisdom;
	}

	public Integer getCharisma() {
		return charisma;
	}

	public void setCharisma(Integer charisma) {
		this.charisma = charisma;
	}

	public Double getChallengeRating() {
		return challengeRating;
	}

	public void setChallengeRating(Double challengeRating) {
		this.challengeRating = challengeRating;
	}

	public Integer getXp() {
		return xp;
	}

	public void setXp(Integer xp) {
		this.xp = xp;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public boolean isDnd5Native() {
		return dnd5Native;
	}

	public void setDnd5Native(boolean dnd5Native) {
		this.dnd5Native = dnd5Native;
	}

	public Alignment getAlignment() {
		return alignment;
	}

	public void setAlignment(Alignment alignment) {
		this.alignment = alignment;
	}

	public MonsterType getMonsterType() {
		return monsterType;
	}

	public void setMonsterType(MonsterType monsterType) {
		this.monsterType = monsterType;
	}

	public Size getSize() {
		return size;
	}

	public void setSize(Size size) {
		this.size = size;
	}

	public ArmorClass getArmorClass() {
		return armorClass;
	}

	public void setArmorClass(ArmorClass armorClass) {
		this.armorClass = armorClass;
	}

	public Set<Language> getLanguages() {
		return languages;
	}

	public void setLanguages(Set<Language> languages) {
		this.languages = languages;
	}

	public Set<Condition> getConditionsImmunities() {
		return conditionsImmunities;
	}

	public void setConditionsImmunities(Set<Condition> conditionsImmunities) {
		this.conditionsImmunities = conditionsImmunities;
	}

	public Set<DamageType> getMonsterVulnerabilities() {
		return monsterVulnerabilities;
	}

	public void setMonsterVulnerabilities(Set<DamageType> monsterVulnerabilities) {
		this.monsterVulnerabilities = monsterVulnerabilities;
	}

	public Set<DamageType> getMonsterResistances() {
		return monsterResistances;
	}

	public void setMonsterResistances(Set<DamageType> monsterResistances) {
		this.monsterResistances = monsterResistances;
	}

	public Set<DamageType> getMonsterImunities() {
		return monsterImunities;
	}

	public void setMonsterImunities(Set<DamageType> monsterImunities) {
		this.monsterImunities = monsterImunities;
	}

	public Integer getPassivePerception() {
		return passivePerception;
	}

	public void setPassivePerception(Integer passivePerception) {
		this.passivePerception = passivePerception;
	}

	public Integer getDarkvision() {
		return darkvision;
	}

	public void setDarkvision(Integer darkvision) {
		this.darkvision = darkvision;
	}

	public Integer getWalk() {
		return walk;
	}

	public void setWalk(Integer walk) {
		this.walk = walk;
	}

	public Integer getSwim() {
		return swim;
	}

	public void setSwim(Integer swim) {
		this.swim = swim;
	}

	public Integer getFly() {
		return fly;
	}

	public void setFly(Integer fly) {
		this.fly = fly;
	}

	@Override
	public String toString() {
		return "MonsterModel [monsterId=" + monsterId + ", monsterName=" + monsterName + ", hitPoints=" + hitPoints
				+ ", hitPointsRoll=" + hitPointsRoll + ", strength=" + strength
				+ ", dexterity=" + dexterity + ", constitution=" + constitution + ", intelligence=" + intelligence
				+ ", wisdom=" + wisdom + ", charisma=" + charisma + ", challengeRating=" + challengeRating + ", xp="
				+ xp + ", imageUrl=" + imageUrl + ", dnd5Native=" + dnd5Native + ", passivePerception="
				+ passivePerception + ", darkvision=" + darkvision + ", walk=" + walk + ", swim=" + swim + ", fly="
				+ fly + ", alignment=" + alignment + ", monsterType=" + monsterType + ", size=" + size + ", armorClass="
				+ armorClass + ", languages=" + languages + ", conditionsImmunities=" + conditionsImmunities
				+ ", monsterVulnerabilities=" + monsterVulnerabilities + ", monsterResistances=" + monsterResistances
				+ ", monsterImunities=" + monsterImunities + "]";
	}

}
