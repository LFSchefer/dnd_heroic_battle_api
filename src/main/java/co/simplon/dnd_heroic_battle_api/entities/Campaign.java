package co.simplon.dnd_heroic_battle_api.entities;

import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Entity
@Table(name = "campaigns")
@Builder
@AllArgsConstructor
public class Campaign {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "campaign_id")
	private Long campaignId;

	@Column(name = "campaign_name")
	private String campaignName;

	@CreationTimestamp
	@Column(name = "creation_date")
	private Timestamp creationDate;

	public Campaign() {
		// for ORM
	}

	public Long getId() {
		return campaignId;
	}

	@SuppressWarnings("unused")
	private void setId(Long id) {
		this.campaignId = id;
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
		return "{id=" + campaignId + ", campaignName=" + campaignName + ", creationDate=" + creationDate + "}";
	}

}
