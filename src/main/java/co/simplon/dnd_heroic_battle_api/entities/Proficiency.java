package co.simplon.dnd_heroic_battle_api.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.util.Objects;

@Entity
@Table(name = "proficiencies")
@Builder
@AllArgsConstructor
public class Proficiency {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "proficiency_id")
	private Long proficiencyId;

	@Column(name = "proficiency_name")
	private String proficiencyName;

	@Column(name = "proficiency_type")
	private String proficiencyType;

	@Column(name = "proficiency_attribute")
	private String proficiencyAttribute;

	public Proficiency() {
		// ORM
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

	public Long getProficiencyId() {
		return proficiencyId;
	}

	@SuppressWarnings("unused")
	private void setProficiencyId(Long proficiencyId) {
		this.proficiencyId = proficiencyId;
	}

	@Override
	public String toString() {
		return "{proficiencyId=" + proficiencyId + ", proficiencyName=" + proficiencyName + ", proficiencyType=" + proficiencyType + ", proficiencyAttribute="
				+ proficiencyAttribute + "}";
	}

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof Proficiency that)) return false;
        return Objects.equals(proficiencyName, that.proficiencyName);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(proficiencyName);
	}
}
