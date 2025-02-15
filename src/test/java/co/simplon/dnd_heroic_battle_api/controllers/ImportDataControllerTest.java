package co.simplon.dnd_heroic_battle_api.controllers;

import co.simplon.dnd_heroic_battle_api.services.ImportDataService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ImportDataControllerTest {

    @InjectMocks
    private ImportDataController test;

    @Mock
    private ImportDataService service;

    @Test
    void importData() {
        assertDoesNotThrow(() -> test.importData());
        verify(service,times(1)).importData();
    }

}