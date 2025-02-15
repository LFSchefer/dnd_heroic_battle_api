package co.simplon.dnd_heroic_battle_api.controllers;

import co.simplon.dnd_heroic_battle_api.dtos.monsters.MonsterCreateDto;
import co.simplon.dnd_heroic_battle_api.services.MonstersService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


@ExtendWith(MockitoExtension.class)
class MonsterControllerTest {

    @InjectMocks
    private MonsterController test;

    @Mock
    private MonstersService service;

    @Test
    void create() {
        var dto = new MonsterCreateDto("name",10,10,1L,3L);
        assertDoesNotThrow(()-> test.create(dto));
        verify(service,times(1)).create(dto);
    }
}