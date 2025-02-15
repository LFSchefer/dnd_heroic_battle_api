package co.simplon.dnd_heroic_battle_api.controllers;

import co.simplon.dnd_heroic_battle_api.dtos.campaign.CampaignCreate;
import co.simplon.dnd_heroic_battle_api.dtos.campaign.CampaignUpdate;
import co.simplon.dnd_heroic_battle_api.services.CampaignService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


@ExtendWith(MockitoExtension.class)
class CampaignControllerTest {

    @InjectMocks
    private CampaignController test;

    @Mock
    private CampaignService service;

    @Test
    void create() {
        var dto = new CampaignCreate("name");
        assertDoesNotThrow(()-> test.create(dto));
        verify(service,times(1)).create(dto);
    }

    @Test
    void getAllByUserId() {
        assertDoesNotThrow(() -> test.getAllByUserId());
        verify(service,times(1)).getAllByUserId();
    }

    @Test
    void getOne() {
        assertDoesNotThrow(() -> test.getOne(1L));
        verify(service, times(1)).getOne(1L);
    }

    @Test
    void deleteOne() {
        assertDoesNotThrow(() -> test.deleteOne(1L));
        verify(service, times(1)).deleteOne(1L);
    }

    @Test
    void update() {
        var dto = new CampaignUpdate(12L,"name");
        assertDoesNotThrow(() -> test.update(dto));
        verify(service, times(1)).update(dto);
    }

}