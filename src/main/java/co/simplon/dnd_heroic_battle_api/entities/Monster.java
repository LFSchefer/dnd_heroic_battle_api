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
@Table(name = "monsters")
@Builder
@AllArgsConstructor
public class Monster {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "monster_id")
    private Long monsterId;

    @Column(name = "current_hit_points")
    private int currentHitPoints;

    @Column(name = "max_hit_points")
    private int maxHitPoints;

    @Column(name = "monster_name")
    private String name;

    @Column(name = "initiative")
    private Integer initiative;

    @ManyToOne
    @JoinColumn(name = "model_id")
    private MonsterModel monster;

    public Monster() {
        // ORM
    }

    public Long getMonsterId() {
        return monsterId;
    }

    @SuppressWarnings("unused")
	private void setMonsterId(Long battleMonsterId) {
        this.monsterId = battleMonsterId;
    }

    public int getCurrentHitPoints() {
        return currentHitPoints;
    }

    public void setCurrentHitPoints(int currentHitPoints) {
        this.currentHitPoints = currentHitPoints;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getInitiative() {
        return initiative;
    }

    public void setInitiative(Integer initiative) {
        this.initiative = initiative;
    }

    public MonsterModel getMonster() {
        return monster;
    }

    @SuppressWarnings("unused")
	private void setMonster(MonsterModel monster) {
        this.monster = monster;
    }

    public void setMaxHitPoints(int maxHitPoints) {
        this.maxHitPoints = maxHitPoints;
    }

    public int getMaxHitPoints() {
        return maxHitPoints;
    }

    @Override
    public String toString() {
        return "Monster{" +
                "monsterId=" + monsterId +
                ", currentHitPoints=" + currentHitPoints +
                ", maxHitPoints=" + maxHitPoints +
                ", name='" + name + '\'' +
                ", initiative=" + initiative +
                ", monster=" + monster +
                '}';
    }
}
