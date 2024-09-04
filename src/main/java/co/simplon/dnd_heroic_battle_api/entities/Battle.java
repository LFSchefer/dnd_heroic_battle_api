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

	@Override
	public String toString() {
		return "{battleId=" + battleId + ", battleName=" + battleName + ", turn=" + turn + ", campaign=" + campaign + "}";
	}

}
