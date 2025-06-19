package co.simplon.dnd_heroic_battle_api.controllers;

import co.simplon.dnd_heroic_battle_api.dtos.monsters.MonsterActionsUpdateDtos;
import co.simplon.dnd_heroic_battle_api.dtos.monsters.MonsterCreateDto;
import co.simplon.dnd_heroic_battle_api.dtos.monsters.MonsterInitiativeDto;
import co.simplon.dnd_heroic_battle_api.dtos.monsters.MonsterInitiativeUpdateDto;
import co.simplon.dnd_heroic_battle_api.services.MonstersService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class MonsterControllerTest {

    @InjectMocks
    private MonsterController test;

    @Mock
    private MonstersService service;

    @Test
    void create() {
        var dto = new MonsterCreateDto("name", 10, 10, 1L, 3L);
        assertDoesNotThrow(() -> test.create(dto));
        verify(service, times(1)).create(dto);
    }

    @Test
    void getInitiative() {
        var monsterInitiativeDto = new MonsterInitiativeDto(1L, "name", 12, 0, null);
        when(service.getAllInitiative(1L)).thenReturn(Set.of(monsterInitiativeDto));
        assertDoesNotThrow(() -> test.getInitiative(1L));
        verify(service, times(1)).getAllInitiative(1L);
    }

    @Test
    void updateInitiative() {
        var monsterInitiativeUpdateDto = new MonsterInitiativeUpdateDto(1L, 15);
        assertDoesNotThrow(() -> test.updateInitiative(monsterInitiativeUpdateDto));
        verify(service, times(1)).updateInitiative(monsterInitiativeUpdateDto);
    }

    @Test
    void calculateInitiative() {
        var monsterInitiativeDto = new MonsterInitiativeDto(1L, "name", 12, 0, null);
        assertDoesNotThrow(() -> test.calculateInitiative(monsterInitiativeDto));
        verify(service, times(1)).calculateInitiative(monsterInitiativeDto);
    }

    @Test
    void calculateAllInitiative() {
        var monsterInitiativeDto = new MonsterInitiativeDto(1L, "name", 12, 0, null);
        assertDoesNotThrow(() -> test.calculateAllInitiative(List.of(monsterInitiativeDto)));
        verify(service, times(1)).calculateAllInitiative(List.of(monsterInitiativeDto));
    }

    @Test
    void updateActions() {
        var monsterActionsUpdateDtos = new MonsterActionsUpdateDtos(1L, true, false, true);
        assertDoesNotThrow(() -> test.updateActions(monsterActionsUpdateDtos));
        verify(service, times(1)).actionsUpdate(monsterActionsUpdateDtos);
    }
}