package co.simplon.dnd_heroic_battle_api.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Entity
@Table(name = "monsters")
@Builder
@AllArgsConstructor
public class Monster {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "monster_id")
	private Long monsterId;

	@Column(name = "monster_name")
	private String monsterName;

	@Column(name = "hit_points")
	private Integer hitPoints;

	@Column(name = "hit_dices")
	private String hitDices;

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

	@Column(name = "dnd5_url")
	private String dnd5Url;

	@Column(name = "dnd5_native")
	private boolean dnd5Native;

	@ManyToOne(targetEntity = Alignment.class)
	@JoinColumn(name = "alignment_id")
	private Alignment alignment;

	@ManyToOne(targetEntity = MonsterType.class)
	@JoinColumn(name = "monster_type_id")
	private MonsterType monsterType;

	@ManyToOne(targetEntity = Size.class)
	@JoinColumn(name = "size_id")
	private Size size;

	@ManyToOne(targetEntity = Sense.class)
	@JoinColumn(name = "sense_id")
	private Sense sense;

	@ManyToOne(targetEntity = Speed.class)
	@JoinColumn(name = "speed_id")
	private Speed speed;

	@ManyToOne(targetEntity = ArmorClass.class)
	@JoinColumn(name = "armor_id")
	private ArmorClass armorClass;

	public Monster() {
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

	public String getHitDices() {
		return hitDices;
	}

	public void setHitDices(String hitDices) {
		this.hitDices = hitDices;
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

	public String getDnd5Url() {
		return dnd5Url;
	}

	public void setDnd5Url(String dnd5Url) {
		this.dnd5Url = dnd5Url;
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

	public Sense getSense() {
		return sense;
	}

	public void setSense(Sense sense) {
		this.sense = sense;
	}

	public Speed getSpeed() {
		return speed;
	}

	public void setSpeed(Speed speed) {
		this.speed = speed;
	}

	public ArmorClass getArmorClass() {
		return armorClass;
	}

	public void setArmorClass(ArmorClass armorClass) {
		this.armorClass = armorClass;
	}

	@Override
	public String toString() {
		return "{monsterId=" + monsterId + ", monsterName=" + monsterName + ", hitPoints=" + hitPoints + ", hitDices=" + hitDices + ", hitPointsRoll="
				+ hitPointsRoll + ", strength=" + strength + ", dexterity=" + dexterity + ", constitution=" + constitution + ", intelligence=" + intelligence
				+ ", wisdom=" + wisdom + ", charisma=" + charisma + ", challengeRating=" + challengeRating + ", xp=" + xp + ", imageUrl=" + imageUrl
				+ ", dnd5Url=" + dnd5Url + ", dnd5Native=" + dnd5Native + ", alignment=" + alignment + ", monsterType=" + monsterType + ", size=" + size
				+ ", sense=" + sense + ", speed=" + speed + ", armorClass=" + armorClass + "}";
	}

}
