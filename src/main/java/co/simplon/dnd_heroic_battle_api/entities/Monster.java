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

    @Column(name = "have_play_this_round")
    private boolean havePlayThisRound;

    @ManyToOne
    @JoinColumn(name = "model_id", updatable = false)
    private MonsterModel monster;

    public Monster() {
        // ORM
    }

    public Long getMonsterId() {
        return monsterId;
    }

    private void setMonsterId(Long monsterId) {
        this.monsterId = monsterId;
    }

    public int getCurrentHitPoints() {
        return currentHitPoints;
    }

    public void setCurrentHitPoints(int currentHitPoints) {
        this.currentHitPoints = currentHitPoints;
    }

    public int getMaxHitPoints() {
        return maxHitPoints;
    }

    public void setMaxHitPoints(int maxHitPoints) {
        this.maxHitPoints = maxHitPoints;
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

    public boolean isHavePlayThisRound() {
        return havePlayThisRound;
    }

    public void setHavePlayThisRound(boolean havePlayThisRound) {
        this.havePlayThisRound = havePlayThisRound;
    }

    public MonsterModel getMonster() {
        return monster;
    }

    public void setMonster(MonsterModel monster) {
        this.monster = monster;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Monster{");
        sb.append("monsterId=").append(monsterId);
        sb.append(", currentHitPoints=").append(currentHitPoints);
        sb.append(", maxHitPoints=").append(maxHitPoints);
        sb.append(", name='").append(name).append('\'');
        sb.append(", initiative=").append(initiative);
        sb.append(", havePlayThisRound=").append(havePlayThisRound);
        sb.append(", monster=").append(monster);
        sb.append('}');
        return sb.toString();
    }
}
