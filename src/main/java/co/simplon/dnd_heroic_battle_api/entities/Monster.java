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
    @Column(name = "id")
    private Long monsterId;

    @Column(name = "current_hit_points")
    private int currentHitPoints;

    @Column(name = "monster_name")
    private String name;

    @Column(name = "initiative")
    private Integer initiative;

    @ManyToOne
    @JoinColumn(name = "monster_id")
    private MonsterModel monster;

    public Monster() {
        // ORM
    }

    public Long getBattleMonsterId() {
        return monsterId;
    }

    private void setBattleMonsterId(Long battleMonsterId) {
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

    public void setMonster(MonsterModel monster) {
        this.monster = monster;
    }

    @Override
    public String toString() {
        return "BattleMonster{" +
                "battleMonsterId=" + monsterId +
                ", currentHitPoints=" + currentHitPoints +
                ", name='" + name + '\'' +
                ", initiative=" + initiative +
                ", monster=" + monster +
                '}';
    }
}
