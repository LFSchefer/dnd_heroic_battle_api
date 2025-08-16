package co.simplon.dnd_heroic_battle_api.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.util.StringJoiner;

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

    @Column(name = "his_turn")
    private boolean hisTurn;

    @Column(name = "have_play_this_round")
    private boolean havePlayThisRound;

    @Column(name = "action")
    private boolean action;

    @Column(name = "move")
    private boolean move;

    @Column(name = "bonus_action")
    private boolean bonusAction;

    @ManyToOne
    @JoinColumn(name = "model_id", updatable = false)
    private MonsterModel monster;

    public Monster() {
        // ORM
    }

    public Long getMonsterId() {
        return monsterId;
    }

    @SuppressWarnings("unused")
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


    public MonsterModel getMonster() {
        return monster;
    }

    public void setMonster(MonsterModel monster) {
        this.monster = monster;
    }

    public boolean isAction() {
        return action;
    }

    public void setAction(boolean action) {
        this.action = action;
    }

    public boolean isMove() {
        return move;
    }

    public void setMove(boolean move) {
        this.move = move;
    }

    public boolean isBonusAction() {
        return bonusAction;
    }

    public void setBonusAction(boolean bonusAction) {
        this.bonusAction = bonusAction;
    }

    public boolean isHisTurn() {
        return hisTurn;
    }

    public void setHisTurn(boolean hisTurn) {
        this.hisTurn = hisTurn;
    }

    public boolean isHavePlayThisRound() {
        return havePlayThisRound;
    }

    public void setHavePlayThisRound(boolean havePlayThisRound) {
        this.havePlayThisRound = havePlayThisRound;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Monster.class.getSimpleName() + "[", "]")
                .add("monsterId=" + monsterId)
                .add("currentHitPoints=" + currentHitPoints)
                .add("maxHitPoints=" + maxHitPoints)
                .add("name='" + name + "'")
                .add("initiative=" + initiative)
                .add("hisTurn=" + hisTurn)
                .add("havePlayThisRound=" + havePlayThisRound)
                .add("action=" + action)
                .add("move=" + move)
                .add("bonusAction=" + bonusAction)
                .add("monster=" + monster)
                .toString();
    }
}
