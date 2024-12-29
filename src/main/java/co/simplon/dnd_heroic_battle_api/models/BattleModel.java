package co.simplon.dnd_heroic_battle_api.models;

import co.simplon.dnd_heroic_battle_api.dtos.battle_monsters.BattleMonsterPreviewDto;
import co.simplon.dnd_heroic_battle_api.entities.BattleMonster;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.util.Set;

@Builder
@AllArgsConstructor
public class BattleModel {

	private Long battleId;

	private String battleName;

	private int turn;

	private Long campaignId;

	private Set<BattleMonsterPreviewDto> battleMonsters;

	public Set<BattleMonsterPreviewDto> getBattleMonsters() {
		return battleMonsters;
	}

	public void setBattleMonsters(Set<BattleMonsterPreviewDto> battleMonsters) {
		this.battleMonsters = battleMonsters;
	}

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
