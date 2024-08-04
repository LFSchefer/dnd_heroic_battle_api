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
@Table(name = "speeds")
@Builder
@AllArgsConstructor
public class Speed {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "walk")
    private Short walk;

    @Column(name = "swim")
    private Short swim;

    @Column(name = "fly")
    private Short fly;

    public Speed() {
	// ORM
    }

    public Long getId() {
	return id;
    }

    @SuppressWarnings("unused")
    private void setId(Long id) {
	this.id = id;
    }

    public Short getWalk() {
	return walk;
    }

    public void setWalk(Short walk) {
	this.walk = walk;
    }

    public Short getSwim() {
	return swim;
    }

    public void setSwim(Short swim) {
	this.swim = swim;
    }

    public Short getFly() {
	return fly;
    }

    public void setFly(Short fly) {
	this.fly = fly;
    }

    @Override
    public String toString() {
	return "{id=" + id + ", walk=" + walk + ", swim=" + swim + ", fly=" + fly + "}";
    }

}
