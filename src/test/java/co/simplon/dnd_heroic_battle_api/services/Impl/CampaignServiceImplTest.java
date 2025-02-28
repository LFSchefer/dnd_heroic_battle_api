package co.simplon.dnd_heroic_battle_api.services.Impl;

import co.simplon.dnd_heroic_battle_api.config.JwtHelper;
import co.simplon.dnd_heroic_battle_api.dtos.campaign.CampaignCreate;
import co.simplon.dnd_heroic_battle_api.dtos.campaign.CampaignUpdate;
import co.simplon.dnd_heroic_battle_api.entities.Campaign;
import co.simplon.dnd_heroic_battle_api.entities.User;
import co.simplon.dnd_heroic_battle_api.repositories.CampaingRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.BadCredentialsException;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Optional;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CampaignServiceImplTest {

    @InjectMocks
    private CampaignServiceImpl test;

    @Mock
    private CampaingRepository repo;

    private MockedStatic<JwtHelper> mockedStatic;

    @BeforeEach
    void setUp() {
        mockedStatic = Mockito.mockStatic(JwtHelper.class);
    }

    @AfterEach
    void cleanUp() {
        mockedStatic.close();
    }

    @Test
    void deleteOne() {
        assertDoesNotThrow( () -> test.deleteOne(1L));
        verify(repo, times(1)).deleteById(1L);
    }

    @Test
    void getOneWithBadId() {
        when(repo.findById(1L)).thenReturn(Optional.empty());
        assertThrows(BadCredentialsException.class, () -> test.getOne(1L));
    }

    @Test
    void getOne() {
        var campaign = Campaign.builder()
                .campaignId(1L)
                .campaignName("name")
                .creationDate(Timestamp.from(Instant.now()))
                .user(User.builder().build())
                .build();
        when(repo.findById(1L)).thenReturn(Optional.of(campaign));
        var actual = assertDoesNotThrow(() -> test.getOne(1L));
        verify(repo, times(1)).findById(1L);
        assertEquals(campaign.getCampaignName(), actual.getCampaignName());
        assertEquals(campaign.getCampaignId(), actual.getCampaignId());
        assertEquals(campaign.getCreationDate(), actual.getCreationDate());
    }

    @Test
    void update() {
        var input = new CampaignUpdate(1L,"campaign name");
        mockedStatic.when(JwtHelper::getSubject).thenReturn(1L);
        assertDoesNotThrow(() -> test.update(input));
        verify(repo, times(1)).update(input.campaignId(),input.campaignName(),1L);
    }

    @Test
    void create() {
        var input = new CampaignCreate("campaign name");
        mockedStatic.when(JwtHelper::getSubject).thenReturn(1L);
        assertDoesNotThrow(() -> test.create(input));
        verify(repo, times(1)).save(any(Campaign.class));
    }

    @Test
    void getAllByUserId() {
        var campaign = Campaign.builder()
                .campaignName("name")
                .campaignId(1L)
                .user(User.builder().build())
                .creationDate(Timestamp.from(Instant.now()))
                .build();
        when(repo.findByUserUserIdOrderByCreationDateDesc(1L)).thenReturn(List.of(campaign));
        mockedStatic.when(JwtHelper::getSubject).thenReturn(1L);
        var actual = assertDoesNotThrow(() -> test.getAllByUserId());
        verify(repo, times(1)).findByUserUserIdOrderByCreationDateDesc(1L);
        assertEquals(1, actual.size());
        assertEquals(campaign.getCampaignName(), actual.getFirst().getCampaignName());
        assertEquals(campaign.getCampaignId(), actual.getFirst().getCampaignId());
        assertEquals(campaign.getCreationDate(), actual.getFirst().getCreationDate());
    }
}