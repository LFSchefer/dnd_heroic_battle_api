package co.simplon.dnd_heroic_battle_api.controllers;

import co.simplon.dnd_heroic_battle_api.services.MonsterModelService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class MonsterModelControllerTest {

    @InjectMocks
    private MonsterModelController test;

    @Mock
    private MonsterModelService service;

    @Test
    void get() {
        assertDoesNotThrow(() -> test.get("name",10,1));
        verify(service, times(1)).get("name",10,1);
    }

    @Test
    void getOne() {
        assertDoesNotThrow(() -> test.getOne(1L));
        verify(service,times(1)).getOne(1L);
    }

    @Test
    void getDetail() {
        assertDoesNotThrow(() -> test.getDetail(1L));
        verify(service, times(1)).getDetail(1L);
    }
  
}