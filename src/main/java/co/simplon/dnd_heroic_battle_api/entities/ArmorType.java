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
@Table(name = "armor_types")
@Builder
@AllArgsConstructor
public class ArmorType {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "armor_types_id")
	private Long armorTypeId;

	@Column(name = "armor_type")
	private String armorType;

	public ArmorType() {
		// ORM
	}

	public Long getId() {
		return armorTypeId;
	}

	@SuppressWarnings("unused")
	private void setId(Long id) {
		this.armorTypeId = id;
	}

	public String getArmorType() {
		return armorType;
	}

	public void setArmorType(String armorType) {
		this.armorType = armorType;
	}

	@Override
	public String toString() {
		return "ArmorType{" +
				"armorTypeId=" + armorTypeId +
				", armorType='" + armorType + '\'' +
				'}';
	}
}
