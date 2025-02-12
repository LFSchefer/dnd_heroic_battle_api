package co.simplon.dnd_heroic_battle_api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import co.simplon.dnd_heroic_battle_api.entities.Campaign;

@Repository
public interface CampaingRepository extends JpaRepository<Campaign, Long> {

	String UPDATE_CAMPAIGN = """
			UPDATE campaigns
				SET campaign_name = :campaignName
				WHERE campaign_id = :id
				AND user_id = :userId
			""";

	boolean existsByCampaignName(String campaignName);

	@Modifying
	@Query(value = UPDATE_CAMPAIGN, nativeQuery = true)
	void update(@Param("id") long id, @Param("campaignName") String campaignName, @Param("userId") Long userId);

	boolean existsByCampaignNameAndIdNot(String campaignName, long id);

	List<Campaign> findByUserUserIdOrderByCreationDateDesc(Long id);

}
