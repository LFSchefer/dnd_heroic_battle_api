package co.simplon.dnd_heroic_battle_api.controllers;

import co.simplon.dnd_heroic_battle_api.dtos.battle.BattleCreate;
import co.simplon.dnd_heroic_battle_api.dtos.battle.BattleUpdate;
import co.simplon.dnd_heroic_battle_api.services.BattleService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class BattleControllerTest {

    @InjectMocks
    private BattleController test;

    @Mock
    private BattleService service;

    @Test
    void getOne() {
        assertDoesNotThrow(() -> test.getOne(1L));
        verify(service, times(1)).getOne(1L);
    }

    @Test
    void getAllFromCampaign() {
        assertDoesNotThrow(() -> test.getAllFromCampaign(1L));
        verify(service, times(1)).getAllFromCampaign(1L);
    }

    @Test
    void deleteOne() {
        assertDoesNotThrow(() -> test.deleteOne(1L));
        verify(service, times(1)).deleteOne(1L);
    }

    @Test
    void create() {
        var dto = new BattleCreate("name", 1L);
        assertDoesNotThrow(() -> test.create(dto));
        verify(service, times(1)).create(dto);
    }

    @Test
    void update() {
        var dto = new BattleUpdate(1L, "name", 0);
        assertDoesNotThrow(() -> test.update(dto));
        verify(service, times(1)).update(dto);
    }

}