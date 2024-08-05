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
@Table(name = "monster_types")
@Builder
@AllArgsConstructor
public class MonsterType {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "monster_type_id")
	private Long monsterTypesId;

	@Column(name = "type_name")
	private String typeName;

	public MonsterType() {
		// ORM
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public Long getMonsterTypesId() {
		return monsterTypesId;
	}

	@SuppressWarnings("unused")
	private void setMonsterTypesId(Long monsterTypesId) {
		this.monsterTypesId = monsterTypesId;
	}

	@Override
	public String toString() {
		return "{monsterTypesId=" + monsterTypesId + ", typeName=" + typeName + "}";
	}

}
