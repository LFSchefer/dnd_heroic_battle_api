package co.simplon.dnd_heroic_battle_api.components;

import org.springframework.stereotype.Component;

import java.util.concurrent.ThreadLocalRandom;

@Component
public class DiceRoller {

    public int d20(int bonus) {
        return d20() + bonus;
    }

    public int d20() {
        return ThreadLocalRandom.current().nextInt(1,21);
    }
}
