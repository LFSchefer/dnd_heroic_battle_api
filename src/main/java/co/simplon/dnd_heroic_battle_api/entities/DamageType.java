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
@Table(name = "damage_types")
@Builder
@AllArgsConstructor
public class DamageType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "damage_type_name")
    private String damageTypeName;

    @Column(name = "description")
    private String description;

    public DamageType() {
	// ORM
    }

    public Long getId() {
	return id;
    }

    @SuppressWarnings("unused")
    private void setId(Long id) {
	this.id = id;
    }

    public String getDamageTypeName() {
	return damageTypeName;
    }

    public void setDamageTypeName(String damageTypeName) {
	this.damageTypeName = damageTypeName;
    }

    public String getDescription() {
	return description;
    }

    public void setDescription(String description) {
	this.description = description;
    }

    @Override
    public String toString() {
	return "{id=" + id + ", damageTypeName=" + damageTypeName + ", description=" + description + "}";
    }

}
