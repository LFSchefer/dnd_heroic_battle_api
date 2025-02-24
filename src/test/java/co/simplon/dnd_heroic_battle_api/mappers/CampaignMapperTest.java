package co.simplon.dnd_heroic_battle_api.mappers;

import co.simplon.dnd_heroic_battle_api.dtos.campaign.CampaignCreate;
import co.simplon.dnd_heroic_battle_api.entities.Campaign;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CampaignMapperTest {

    @Test
    void campaignCreateToEntity() {
        var campaignCreate = new CampaignCreate("campaign name");
        var userId = 423L;
        var actual = assertDoesNotThrow(() -> CampaignMapper.campaignCreateToEntity(campaignCreate, userId));
        assertEquals(campaignCreate.campaignName(), actual.getCampaignName());
        assertEquals(userId, actual.getUser().getUserId());
    }

    @Test
    void entitiesToCampaignModel() {
        var campaign = Campaign.builder()
                .campaignId(1L)
                .campaignName("name")
                .creationDate(Timestamp.from(Instant.now()))
                .build();
        var campaigns = List.of(campaign);
        var actual = assertDoesNotThrow(() -> CampaignMapper.entitiesToCampaignModel(campaigns));
        assertEquals(campaigns.getFirst().getCampaignId(), actual.getFirst().getCampaignId());
        assertEquals(campaigns.getFirst().getCampaignName(), actual.getFirst().getCampaignName());
        assertEquals(campaigns.getFirst().getCreationDate(), actual.getFirst().getCreationDate());
    }

    @Test
    void entityToCampaignModel() {
        var campaign = Campaign.builder()
                .campaignId(1L)
                .campaignName("name")
                .creationDate(Timestamp.from(Instant.now()))
                .build();
        var actual = assertDoesNotThrow(() -> CampaignMapper.entityToCampaignModel(campaign));
        assertEquals(campaign.getCampaignId(), actual.getCampaignId());
        assertEquals(campaign.getCampaignName(), actual.getCampaignName());
        assertEquals(campaign.getCreationDate(), actual.getCreationDate());
    }
}