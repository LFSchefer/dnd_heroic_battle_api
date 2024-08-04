package co.simplon.dnd_heroic_battle_api.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Entity
@Table(name = "proficiencies")
@Builder
@AllArgsConstructor
public class Proficiency {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "proficiency_name")
    private String proficiencyName;

    @Column(name = "proficiency_type")
    private String proficiencyType;

    @Column(name = "proficiency_attribute")
    private String proficiencyAttribute;

    public Proficiency() {
	// ORM
    }

    public Long getId() {
	return id;
    }

    @SuppressWarnings("unused")
    private void setId(Long id) {
	this.id = id;
    }

    public String getProficiencyName() {
	return proficiencyName;
    }

    public void setProficiencyName(String proficiencyName) {
	this.proficiencyName = proficiencyName;
    }

    public String getProficiencyType() {
	return proficiencyType;
    }

    public void setProficiencyType(String proficiencyType) {
	this.proficiencyType = proficiencyType;
    }

    public String getProficiencyAttribute() {
	return proficiencyAttribute;
    }

    public void setProficiencyAttribute(String proficiencyAttribute) {
	this.proficiencyAttribute = proficiencyAttribute;
    }

    @Override
    public String toString() {
	return "{id=" + id + ", proficiencyName=" + proficiencyName + ", proficiencyType=" + proficiencyType
		+ ", proficiencyAttribute=" + proficiencyAttribute + "}";
    }

}
