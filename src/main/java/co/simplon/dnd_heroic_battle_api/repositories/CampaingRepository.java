package co.simplon.dnd_heroic_battle_api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import co.simplon.dnd_heroic_battle_api.entities.Campaign;

@Repository
public interface CampaingRepository extends JpaRepository<Campaign, Long> {

    String UPDATE_CAMPAIGN = """
    	UPDATE Campaign
    		SET campaignName = :campaignName
    		WHERE id = :id
    	""";

    boolean existsByCampaignName(String campaignName);

    @Modifying
    @Query(value = UPDATE_CAMPAIGN)
    void update(@Param("id") long id, @Param("campaignName") String campaignName);

    boolean existsByCampaignNameAndIdNot(String campaignName, long id);

}
