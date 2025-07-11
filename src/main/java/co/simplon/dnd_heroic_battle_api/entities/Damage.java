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

import java.util.Objects;

@Builder
@AllArgsConstructor
@Entity
@Table(name = "damages")
public class Damage {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "damage_id")
	private Long damageId;

	@Column(name = "damage_dices")
	private String damageDices;

	@ManyToOne
	@JoinColumn(name = "damage_type_id")
	private DamageType damageType;

	public Damage() {
		// ORM
	}

	public Long getDamageId() {
		return damageId;
	}

	@SuppressWarnings("unused")
	private void setDamageId(Long damageId) {
		this.damageId = damageId;
	}

	public String getDamageDices() {
		return damageDices;
	}

	public void setDamageDices(String damageDices) {
		this.damageDices = damageDices;
	}

	public DamageType getDamageType() {
		return damageType;
	}

	public void setDamageType(DamageType damageType) {
		this.damageType = damageType;
	}

	@Override
	public String toString() {
		return "{damageId=" + damageId + ", damageDices=" + damageDices + ", damageType=" + damageType + "}";
	}

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof Damage damage)) return false;
        return Objects.equals(damageDices, damage.damageDices) && Objects.equals(damageType, damage.damageType);
	}

	@Override
	public int hashCode() {
		return Objects.hash(damageDices, damageType);
	}
}
