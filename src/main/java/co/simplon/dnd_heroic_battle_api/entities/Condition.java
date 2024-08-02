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
@Table(name = "conditions")
@Builder
@AllArgsConstructor
public class Condition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "condition_name")
    private String conditionName;

    @Column(name = "description")
    private String description;

    public Condition() {
	// ORM
    }

    public long getId() {
	return id;
    }

    @SuppressWarnings("unused")
    private void setId(long id) {
	this.id = id;
    }

    public String getConditionName() {
	return conditionName;
    }

    public void setConditionName(String conditionName) {
	this.conditionName = conditionName;
    }

    public String getDescription() {
	return description;
    }

    public void setDescription(String description) {
	this.description = description;
    }

    @Override
    public String toString() {
	return "{id=" + id + ", conditionName=" + conditionName + ", description=" + description + "}";
    }

}
