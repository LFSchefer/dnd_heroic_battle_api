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
@Table(name = "alignments")
@Builder
@AllArgsConstructor
public class Alignment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "alignments_name")
    private String alignmentsName;

    @Column(name = "description")
    private String description;

    public Alignment() {
	// ORM
    }

    public long getId() {
	return id;
    }

    @SuppressWarnings("unused")
    private void setId(long id) {
	this.id = id;
    }

    public String getAlignmentsName() {
	return alignmentsName;
    }

    public void setAlignmentsName(String alignmentsName) {
	this.alignmentsName = alignmentsName;
    }

    public String getDescription() {
	return description;
    }

    public void setDescription(String description) {
	this.description = description;
    }

    @Override
    public String toString() {
	return "{id=" + id + ", alignmentsName=" + alignmentsName + ", description=" + description + "}";
    }

}
