package co.simplon.dnd_heroic_battle_api.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "monster_models")
@Builder
@AllArgsConstructor
public class MonsterModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "model_id")
    private Long modelId;

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

    @Column(name = "armor_class")
    private int armorClass;

    @ManyToOne(targetEntity = Alignment.class)
    @JoinColumn(name = "alignment_id")
    private Alignment alignment;

    @ManyToOne(targetEntity = MonsterType.class)
    @JoinColumn(name = "monster_type_id")
    private MonsterType monsterType;

    @ManyToOne(targetEntity = Size.class)
    @JoinColumn(name = "size_id")
    private Size size;

    @ManyToOne(targetEntity = ArmorType.class)
    @JoinColumn(name = "armor_id")
    private ArmorType armorType;

    @Builder.Default
    @ManyToMany
    @JoinTable(name = "monster_languages",
            joinColumns = @JoinColumn(name = "monster_id"),
            inverseJoinColumns = @JoinColumn(name = "language_id"))
    private Set<Language> languages = new HashSet<>();

    @Builder.Default
    @ManyToMany
    @JoinTable(name = "monster_condition_immunities",
            joinColumns = @JoinColumn(name = "monster_id"),
            inverseJoinColumns = @JoinColumn(name = "condition_id"))
    private Set<Condition> conditionsImmunities = new HashSet<>();

    @Builder.Default
    @ManyToMany
    @JoinTable(name = "monster_vulnerabilities",
            joinColumns = @JoinColumn(name = "monster_id"),
            inverseJoinColumns = @JoinColumn(name = "vulnerability_id"))
    private Set<DamageType> monsterVulnerabilities = new HashSet<>();

    @Builder.Default
    @ManyToMany
    @JoinTable(name = "monster_resistances",
            joinColumns = @JoinColumn(name = "monster_id"),
            inverseJoinColumns = @JoinColumn(name = "resistance_id"))
    private Set<DamageType> monsterResistances = new HashSet<>();

    @Builder.Default
    @ManyToMany
    @JoinTable(name = "monster_imunities",
            joinColumns = @JoinColumn(name = "monster_id"),
            inverseJoinColumns = @JoinColumn(name = "imunity_id"))
    private Set<DamageType> monsterImunities = new HashSet<>();

    @Builder.Default
    @ManyToMany
    @JoinTable(name = "monster_special_abilities",
            joinColumns = @JoinColumn(name = "monster_id"),
            inverseJoinColumns = @JoinColumn(name = "special_ability_id"))
    private Set<SpecialAbility> specialAbilities = new HashSet<>();

    public MonsterModel() {
        // ORM
    }

    public Long getModelId() {
        return modelId;
    }

    @SuppressWarnings("unused")
    private void setModelId(Long modelId) {
        this.modelId = modelId;
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

    public ArmorType getArmorType() {
        return armorType;
    }

    public void setArmorType(ArmorType armorType) {
        this.armorType = armorType;
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

    public Set<SpecialAbility> getSpecialAbilities() {
        return specialAbilities;
    }

    public void setSpecialAbilities(Set<SpecialAbility> specialAbilities) {
        this.specialAbilities = specialAbilities;
    }

    public int getArmorClass() {
        return armorClass;
    }

    public void setArmorClass(int armorClass) {
        this.armorClass = armorClass;
    }

    @Override
    public int hashCode() {
        return Objects.hash(monsterName);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return obj instanceof MonsterModel other
                && this.monsterName.equals(other.monsterName);
    }

    @Override
    public String toString() {
        return "MonsterModel [monsterId=" + modelId + ", monsterName=" + monsterName + ", hitPoints=" + hitPoints
                + ", hitPointsRoll=" + hitPointsRoll + ", strength=" + strength + ", dexterity=" + dexterity
                + ", constitution=" + constitution + ", intelligence=" + intelligence + ", wisdom=" + wisdom
                + ", charisma=" + charisma + ", challengeRating=" + challengeRating + ", xp=" + xp + ", imageUrl="
                + imageUrl + ", dnd5Native=" + dnd5Native + ", passivePerception=" + passivePerception + ", darkvision="
                + darkvision + ", walk=" + walk + ", swim=" + swim + ", fly=" + fly + ", alignment=" + alignment
                + ", monsterType=" + monsterType + ", size=" + size + ", armorClass=" + armorType + ", languages="
                + languages + ", conditionsImmunities=" + conditionsImmunities + ", monsterVulnerabilities="
                + monsterVulnerabilities + ", monsterResistances=" + monsterResistances + ", monsterImunities="
                + monsterImunities + ", specialAbilities=" + specialAbilities + "]";
    }


}
