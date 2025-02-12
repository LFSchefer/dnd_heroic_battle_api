package co.simplon.dnd_heroic_battle_api.entities;

import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;

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
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	public Campaign() {
		// for ORM
	}

	public Long getId() {
		return campaignId;
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

	public Long getCampaignId() {
		return campaignId;
	}

	@SuppressWarnings("unused")
	private void setCampaignId(Long campaignId) {
		this.campaignId = campaignId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Campaign [campaignId=" + campaignId + ", campaignName=" + campaignName + ", creationDate="
				+ creationDate + ", user=" + user + "]";
	}
	
	



}
