package co.simplon.dnd_heroic_battle_api.components;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class DiceRollerTest {

    @InjectMocks
    private DiceRoller test;

    @Test
    void d20() {
        var actual = assertDoesNotThrow(() -> test.d20());
        assertTrue(1 <= actual && actual <= 20);
    }

    @Test
    void d20Bonus() {
        var bonus = 5;
        var actual = assertDoesNotThrow(() -> test.d20(bonus));
        assertTrue(1 + bonus <= actual && actual <= 20 + bonus);
    }

}