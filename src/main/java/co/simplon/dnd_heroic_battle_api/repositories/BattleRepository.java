package co.simplon.dnd_heroic_battle_api.repositories;

import co.simplon.dnd_heroic_battle_api.entities.Battle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BattleRepository extends JpaRepository<Battle, Long> {

    String FIND_CAMPAIGN_ID_BY_BATTLE_ID = """
            SELECT campaign_id FROM battles
            WHERE battle_id = :id
            """;
    String BATTLE_NAME_DONT_EXIST_FOR_THIS_CAMPAIGN = """
            SELECT CASE WHEN EXISTS (
                SELECT *
                FROM battles b
                WHERE battle_name = :battleName
                AND battle_id <> :id
                AND campaign_id = (SELECT campaign_id FROM battles WHERE battle_id = :id)
            )
            THEN FALSE
            ELSE TRUE END
            """;
    String FIND_ALL_BY_CAMPAIGN_ID = """
            SELECT * FROM battles b
            WHERE campaign_id = :id
            ORDER BY battle_id DESC;
            """;

    @NativeQuery(value = FIND_CAMPAIGN_ID_BY_BATTLE_ID)
    long findcampaignIdByBattleId(@Param("id") long id);

    boolean existsByBattleNameAndCampaignId(String battleName, Long campaignId);

    @NativeQuery(value = BATTLE_NAME_DONT_EXIST_FOR_THIS_CAMPAIGN)
    boolean battleNameNotExistForCampaign(@Param("id") long id, @Param("battleName") String battleName);

    @NativeQuery(value = FIND_ALL_BY_CAMPAIGN_ID)
    List<Battle> findAllByCampaignId(@Param("id") Long id);
}
