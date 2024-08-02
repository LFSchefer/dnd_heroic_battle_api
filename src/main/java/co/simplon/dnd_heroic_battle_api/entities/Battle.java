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
    @Column(name = "id")
    private Long id;

    @Column(name = "battle_name")
    private String battleName;

    @ManyToOne
    @JoinColumn(name = "campaign_id")
    private Campaign campaign;

    public Battle() {
	// for ORM
    }

    public long getId() {
	return id;
    }

    @SuppressWarnings("unused")
    private void setId(long id) {
	this.id = id;
    }

    public String getBattleName() {
	return battleName;
    }

    public void setBattleName(String battleName) {
	this.battleName = battleName;
    }

    public Campaign getCampaign() {
	return campaign;
    }

    public void setCampaign(Campaign campaign) {
	this.campaign = campaign;
    }

    @Override
    public String toString() {
	return "{id=" + id + ", battleName=" + battleName + ", campaign=" + campaign + "}";
    }

}
