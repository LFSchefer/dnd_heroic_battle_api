package co.simplon.dnd_heroic_battle_api.models;

import java.util.Set;


import co.simplon.dnd_heroic_battle_api.dtos.monsters.MonsterPreviewDto;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Builder
@AllArgsConstructor
public class BattleModel {

	private Long battleId;

	private String battleName;

	private int turn;

	private Long campaignId;

	private Set<MonsterPreviewDto> battleMonsters;

	public Long getBattleId() {
		return battleId;
	}

	public String getBattleName() {
		return battleName;
	}

	public int getTurn() {
		return turn;
	}

	public Long getCampaignId() {
		return campaignId;
	}

	public void setBattleId(Long battleId) {
		this.battleId = battleId;
	}

	public void setBattleName(String battleName) {
		this.battleName = battleName;
	}

	public void setTurn(int turn) {
		this.turn = turn;
	}

	public void setCampaignId(Long campaignId) {
		this.campaignId = campaignId;
	}

	public Set<MonsterPreviewDto> getBattleMonsters() {
		return battleMonsters;
	}

	public void setBattleMonsters(Set<MonsterPreviewDto> battleMonsters) {
		this.battleMonsters = battleMonsters;
	}

	@Override
	public String toString() {
		return "BattleModel{" +
				"battleId=" + battleId +
				", battleName='" + battleName + '\'' +
				", turn=" + turn +
				", campaignId=" + campaignId +
				'}';
	}
}
