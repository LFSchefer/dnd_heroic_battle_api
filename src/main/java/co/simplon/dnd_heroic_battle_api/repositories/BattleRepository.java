package co.simplon.dnd_heroic_battle_api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import co.simplon.dnd_heroic_battle_api.entities.Battle;

@Repository
public interface BattleRepository extends JpaRepository<Battle, Long> {

    List<Battle> findAllByCampaignId(Long id);

    String FIND_CAMPAIGN_ID_BY_BATTLE_ID = """
    	SELECT campaign_id FROM battles
    	WHERE id = :id
    	""";

    @Query(value = FIND_CAMPAIGN_ID_BY_BATTLE_ID, nativeQuery = true)
    long findcampaignIdByBattleId(@Param("id") long id);

    boolean existsByBattleNameAndCampaignId(String battleName, Long campaignId);

    String BATTLE_NAME_DONT_EXIST_FOR_THIS_CAMPAIGN = """
    	SELECT CASE WHEN EXISTS (
    	    SELECT *
    	    FROM battles b
    	    WHERE battle_name <> :battleName
    	    AND id <> :id
    	    AND campaign_id = (SELECT campaign_id FROM battles WHERE id = :id)
    	)
    	THEN TRUE
    	ELSE FALSE END
    	""";

    @Query(value = BATTLE_NAME_DONT_EXIST_FOR_THIS_CAMPAIGN, nativeQuery = true)
    boolean battleNameNotExistForCampaign(@Param("id") long id, @Param("battleName") String battleName);
}
