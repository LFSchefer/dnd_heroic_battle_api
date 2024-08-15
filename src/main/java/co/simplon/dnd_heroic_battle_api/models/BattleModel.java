package co.simplon.dnd_heroic_battle_api.models;

import lombok.AllArgsConstructor;
import lombok.Builder;

@Builder
@AllArgsConstructor
public class BattleModel {

	private Long battleId;

	private String battleName;

	private CampaignModel campaign;

	public Long getBattleId() {
		return battleId;
	}

	public void setBattleId(Long battleId) {
		this.battleId = battleId;
	}

	public String getBattleName() {
		return battleName;
	}

	public void setBattleName(String battleName) {
		this.battleName = battleName;
	}

	public CampaignModel getCampaign() {
		return campaign;
	}

	public void setCampaign(CampaignModel campaign) {
		this.campaign = campaign;
	}

	@Override
	public String toString() {
		return "{battleId=" + battleId + ", battleName=" + battleName + ", campaign=" + campaign + "}";
	}

}
