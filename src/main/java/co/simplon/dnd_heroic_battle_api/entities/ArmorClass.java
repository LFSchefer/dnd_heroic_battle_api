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
@Table(name = "armor_classes")
@Builder
@AllArgsConstructor
public class ArmorClass {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "armor_classe_id")
	private Long armorClasseId;

	@Column(name = "armor_type")
	private String armorType;

	@Column(name = "armor_value")
	private Integer armorValue;

	public ArmorClass() {
		// ORM
	}

	public Long getId() {
		return armorClasseId;
	}

	@SuppressWarnings("unused")
	private void setId(Long id) {
		this.armorClasseId = id;
	}

	public String getArmorType() {
		return armorType;
	}

	public void setArmorType(String armorType) {
		this.armorType = armorType;
	}

	public Integer getArmorValue() {
		return armorValue;
	}

	public void setArmorValue(Integer armorValue) {
		this.armorValue = armorValue;
	}

	@Override
	public String toString() {
		return "{id=" + armorClasseId + ", armorType=" + armorType + ", armorValue=" + armorValue + "}";
	}

}
