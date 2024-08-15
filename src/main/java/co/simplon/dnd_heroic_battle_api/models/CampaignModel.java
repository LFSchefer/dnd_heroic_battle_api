package co.simplon.dnd_heroic_battle_api.models;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;

@Builder
@AllArgsConstructor
public class CampaignModel {

	private Long campaignId;

	private String campaignName;

	private Timestamp creationDate;

	public Long getCampaignId() {
		return campaignId;
	}

	public void setCampaignId(Long campaignId) {
		this.campaignId = campaignId;
	}

	public String getCampaignName() {
		return campaignName;
	}

	public void setCampaignName(String campaignName) {
		this.campaignName = campaignName;
	}

	public Timestamp getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Timestamp creationDate) {
		this.creationDate = creationDate;
	}

	@Override
	public String toString() {
		return "{campaignId=" + campaignId + ", campaignName=" + campaignName + ", creationDate=" + creationDate + "}";
	}

}
