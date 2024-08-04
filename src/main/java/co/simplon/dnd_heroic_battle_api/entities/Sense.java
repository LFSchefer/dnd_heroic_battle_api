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
@Table(name = "senses")
@Builder
@AllArgsConstructor
public class Sense {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "darkvision")
	private Integer darkvision;

	@Column(name = "passive_perception")
	private Integer passivePerception;

	public Sense() {
		// ORM
	}

	public Long getId() {
		return id;
	}

	@SuppressWarnings("unused")
	private void setId(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "{id=" + id + ", darkvision=" + darkvision + ", passivePerception=" + passivePerception + "}";
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

}
