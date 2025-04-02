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

@Builder
@AllArgsConstructor
@Entity
@Table(name = "special_abilities")
public class SpecialAbility {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "special_ability_id")
	private Long specialAbilityId;

	@Column(name = "special_ability_name")
	private String specialAbilityName;

	@Column(name = "special_ability_description")
	private String specialAbilityDescription;

	public SpecialAbility() {
		// ORM
	}

	public Long getSpecialAbilityId() {
		return specialAbilityId;
	}

	public void setSpecialAbilityId(Long specialAbilityId) {
		this.specialAbilityId = specialAbilityId;
	}

	public String getSpecialAbilityName() {
		return specialAbilityName;
	}

	public void setSpecialAbilityName(String specialAbilityName) {
		this.specialAbilityName = specialAbilityName;
	}

	public String getSpecialAbilityDescription() {
		return specialAbilityDescription;
	}

	public void setSpecialAbilityDescription(String specialAbilityDescription) {
		this.specialAbilityDescription = specialAbilityDescription;
	}

	@Override
	public String toString() {
		return "{specialAbilityId=" + specialAbilityId + ", specialAbilityName=" + specialAbilityName + ", specialAbilityDescription="
				+ specialAbilityDescription + "}";
	}

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof SpecialAbility that)) return false;
        return Objects.equals(specialAbilityName, that.specialAbilityName) && Objects.equals(specialAbilityDescription, that.specialAbilityDescription);
	}

	@Override
	public int hashCode() {
		return Objects.hash(specialAbilityName, specialAbilityDescription);
	}
}
