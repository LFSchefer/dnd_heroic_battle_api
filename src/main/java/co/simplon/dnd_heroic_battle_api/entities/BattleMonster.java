package co.simplon.dnd_heroic_battle_api.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Entity
@Table(name = "battle_monsters")
@Builder
@AllArgsConstructor
public class BattleMonster {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "battle_monster_id")
    private Long battleMonsterId;

    @Column(name = "current_hit_points")
    private int currentHitPoints;

    @Column(name = "battle_monster_name")
    private String name;

    @Column(name = "initiative")
    private Integer initiative;

    @ManyToOne
    @JoinColumn(name = "monster_id")
    private Monster monster;

    public BattleMonster() {
        // ORM
    }

    public Long getBattleMonsterId() {
        return battleMonsterId;
    }

    private void setBattleMonsterId(Long battleMonsterId) {
        this.battleMonsterId = battleMonsterId;
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

    public Monster getMonster() {
        return monster;
    }

    public void setMonster(Monster monster) {
        this.monster = monster;
    }

    @Override
    public String toString() {
        return "BattleMonster{" +
                "battleMonsterId=" + battleMonsterId +
                ", currentHitPoints=" + currentHitPoints +
                ", name='" + name + '\'' +
                ", initiative=" + initiative +
                ", monster=" + monster +
                '}';
    }
}
