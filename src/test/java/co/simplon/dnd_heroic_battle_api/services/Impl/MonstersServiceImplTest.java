package co.simplon.dnd_heroic_battle_api.services.Impl;

import co.simplon.dnd_heroic_battle_api.dtos.monsters.MonsterCreateDto;
import co.simplon.dnd_heroic_battle_api.dtos.monsters.MonsterInitiativeDto;
import co.simplon.dnd_heroic_battle_api.entities.Monster;
import co.simplon.dnd_heroic_battle_api.entities.MonsterModel;
import co.simplon.dnd_heroic_battle_api.repositories.MonsterRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MonstersServiceImplTest {

    @InjectMocks
    private MonstersServiceImpl test;

    @Mock
    private MonsterRepository repo;

    @Test
    void create() {
        var input = new MonsterCreateDto("name",42,42,1L,1L);
        assertDoesNotThrow(() -> test.create(input));
        verify(repo, times(1)).create("name",42,42,1L,1L);
    }

    @Test
    void getAllInitiative() {
        var monsterModel = MonsterModel.builder()
                .dexterity(16)
                .build();
        var monster = Monster.builder()
                .monsterId(1L)
                .name("name")
                .initiative(null)
                .monster(monsterModel)
                .build();
        when(repo.getAllByBattle(7L)).thenReturn(Set.of(monster));
        Set<MonsterInitiativeDto> actual = assertDoesNotThrow(() -> test.getAllInitiative(7L));
        verify(repo, times(1)).getAllByBattle(7L);
        assertEquals(actual.stream().findFirst().get().id(), monster.getMonsterId());
        assertEquals(actual.stream().findFirst().get().name(), monster.getName());
        assertEquals(actual.stream().findFirst().get().initiative(), monster.getInitiative());
        assertEquals(actual.stream().findFirst().get().dexterity(), monsterModel.getDexterity());
    }

}