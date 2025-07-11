package co.simplon.dnd_heroic_battle_api.services.Impl;

import co.simplon.dnd_heroic_battle_api.components.DiceRoller;
import co.simplon.dnd_heroic_battle_api.dtos.monsters.*;
import co.simplon.dnd_heroic_battle_api.entities.*;
import co.simplon.dnd_heroic_battle_api.repositories.MonsterRepository;
import org.hibernate.ResourceClosedException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MonstersServiceImplTest {

    @InjectMocks
    private MonstersServiceImpl test;

    @Mock
    private MonsterRepository repo;

    @Mock
    private DiceRoller diceRoller;

    @Test
    void create() {
        var input = new MonsterCreateDto("name", 42, 42, 1L, 1L);
        assertDoesNotThrow(() -> test.create(input));
        verify(repo, times(1)).create("name", 42, 42, 1L, 1L);
    }

    @Test
    void getAllInitiative() {
        var monster = new MonsterInitiativePro(1L, "name", (short) 7, (short) 15);
        when(repo.getAllInitiativeByBattle(7L)).thenReturn(Set.of(monster));
        Set<MonsterInitiativeDto> actual = assertDoesNotThrow(() -> test.getAllInitiative(7L));
        verify(repo, times(1)).getAllInitiativeByBattle(7L);
        assertEquals(actual.stream().findFirst().get().id(), monster.id());
        assertEquals(actual.stream().findFirst().get().name(), monster.name());
        assertEquals(actual.stream().findFirst().get().initiative(), monster.initiative());
        assertEquals(actual.stream().findFirst().get().dexterity(), monster.dexterity());
    }

    @Test
    void updateInitiative() {
        var dto = new MonsterInitiativeUpdateDto(1L, 13);
        var monster = Monster.builder().monsterId(1L).initiative(null).build();
        when(repo.findById(dto.monsterId())).thenReturn(Optional.ofNullable(monster));
        assertDoesNotThrow(() -> test.updateInitiative(dto));
        verify(repo, times(1)).findById(dto.monsterId());
        Assertions.assertNotNull(monster);
        monster.setInitiative(dto.initiative());
        verify(repo, times(1)).save(monster);
    }

    @Test
    void updateInitiativeWithBadId() {
        var dto = new MonsterInitiativeUpdateDto(99L, 13);
        when(repo.findById(dto.monsterId())).thenReturn(Optional.empty());
        assertThrows(ResourceClosedException.class, () -> test.updateInitiative(dto));
    }

    @Test
    void calculateInitiative() {
        var dto = new MonsterInitiativeDto(1L, "name", 13, 1, (short) 5);
        var monster = Monster.builder().monsterId(1L).initiative(5).build();
        when(repo.findById(dto.id())).thenReturn(Optional.ofNullable(monster));
        when(diceRoller.d20(dto.bonus())).thenReturn(14);
        assertDoesNotThrow(() -> test.calculateInitiative(dto));
        verify(repo, times(1)).findById(dto.id());
        Assertions.assertNotNull(monster);
        monster.setInitiative(14);
        verify(repo, times(1)).save(monster);
    }

    @Test
    void calculateInitiativeWithBadId() {
        var dto = new MonsterInitiativeDto(99L, "name", 13, 1, (short) 5);
        when(repo.findById(dto.id())).thenReturn(Optional.empty());
        assertThrows(ResourceClosedException.class, () -> test.calculateInitiative(dto));
    }

    @Test
    void calculateAllInitiative() {
        var dto1 = new MonsterInitiativeDto(1L, "bob", 8, -1, null);
        var dto2 = new MonsterInitiativeDto(2L, "ross", 14, 2, (short) 16);
        var monster1 = Monster.builder().monsterId(1L).initiative(null).build();
        var monster2 = Monster.builder().monsterId(2L).initiative(16).build();
        when(repo.findAllById(List.of(1L, 2L))).thenReturn(List.of(monster1, monster2));
        when(diceRoller.d20(dto1.bonus())).thenReturn(9);
        assertDoesNotThrow(() -> test.calculateAllInitiative(List.of(dto1, dto2)));
        verify(repo, times(1)).findAllById(List.of(1L, 2L));
        verify(diceRoller, times(1)).d20(dto1.bonus());
        monster1.setInitiative(9);
        verify(repo, times(1)).saveAll(List.of(monster1, monster2));
    }

    @Test
    void calculateAllInitiativeWithBadId() {
        var dto = new MonsterInitiativeDto(99L, "bob", 8, -1, null);
        var monster = Monster.builder().monsterId(2L).initiative(null).build();
        when(repo.findAllById(List.of(99L))).thenReturn(List.of(monster));
        assertThrows(ResourceClosedException.class, () -> test.calculateAllInitiative(List.of(dto)));
    }

    @Test
    void actionsUpdate() {
        var dto = new MonsterActionsUpdateDtos(1L, true, false, true);
        var monster = Monster.builder()
                .monsterId(1L)
                .maxHitPoints(100)
                .currentHitPoints(12)
                .havePlayThisRound(true)
                .action(false)
                .move(false)
                .bonusAction(false)
                .monster(MonsterModel.builder()
                        .modelId(42L)
                        .armorType(ArmorType.builder().build())
                        .monsterType(MonsterType.builder().build())
                        .alignment(Alignment.builder().build())
                        .size(Size.builder().build())
                        .build())
                .build();
        when(repo.findById(dto.monsterId())).thenReturn(Optional.of(monster));
        when(repo.saveAndFlush(any())).thenReturn(monster);
        var actual = assertDoesNotThrow(() -> test.actionsUpdate(dto));
        verify(repo, times(1)).findById(dto.monsterId());
        monster.setAction(dto.action());
        monster.setMove(dto.move());
        monster.setBonusAction(dto.bonusAction());
        verify(repo, times(1)).saveAndFlush(monster);
        assertEquals(1L, actual.monsterId());
        assertTrue(actual.action());
        assertFalse(actual.move());
        assertTrue(actual.bonusAction());
    }

    @Test
    void actionsUpdateWithError() {
        var dto = new MonsterActionsUpdateDtos(1L, true, false, true);
        when(repo.findById(dto.monsterId())).thenReturn(Optional.empty());
        assertThrows(ResourceClosedException.class, () -> test.actionsUpdate(dto));
    }

}