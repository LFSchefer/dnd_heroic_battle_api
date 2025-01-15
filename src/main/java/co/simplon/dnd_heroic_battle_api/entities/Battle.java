package co.simplon.dnd_heroic_battle_api.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "battles")
@Builder
@AllArgsConstructor
public class Battle {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "battle_id")
	private Long battleId;

	@Column(name = "battle_name")
	private String battleName;

	@Column(name = "turn")
	private int turn;

	@ManyToOne
	@JoinColumn(name = "campaign_id")
	private Campaign campaign;

	@OneToMany
	@JoinColumn(name = "battle_id")
	private Set<Monster> monsters = new HashSet<Monster>();

	public Battle() {
		// for ORM
	}

	public Long getBattleId() {
		return battleId;
	}

	@SuppressWarnings("unused")
	private void setBattleId(Long battleId) {
		this.battleId = battleId;
	}

	public String getBattleName() {
		return battleName;
	}

	public void setBattleName(String battleName) {
		this.battleName = battleName;
	}

	public int getTurn() {
		return turn;
	}

	public void setTurn(int turn) {
		this.turn = turn;
	}

	public Campaign getCampaign() {
		return campaign;
	}

	public void setCampaign(Campaign campaign) {
		this.campaign = campaign;
	}

	public Set<Monster> getBattleMonsters() {
		return monsters;
	}

	public void setBattleMonsters(Set<Monster> battleMonsters) {
		this.monsters = battleMonsters;
	}

	@Override
	public String toString() {
		return "{battleId=" + battleId + ", battleName=" + battleName + ", turn=" + turn + ", campaign=" + campaign + "}";
	}

}
