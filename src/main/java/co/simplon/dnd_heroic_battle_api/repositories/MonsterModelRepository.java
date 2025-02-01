package co.simplon.dnd_heroic_battle_api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import co.simplon.dnd_heroic_battle_api.dtos.monster_model.MonsterModelPreviewDto;
import co.simplon.dnd_heroic_battle_api.entities.MonsterModel;

@Repository
public interface MonsterModelRepository extends JpaRepository<MonsterModel, Long> {

	String FIND_ALL = """
			SELECT model_id, monster_name, challenge_rating FROM monster_models
			LIMIT :limit
			OFFSET :offset
			""";
	
	String FIND_ALL_BY_NAME = """
			SELECT model_id, monster_name, challenge_rating FROM monster_models
			WHERE UPPER(monster_name) LIKE UPPER(CONCAT('%',:name,'%'))
			LIMIT :limit
			OFFSET :offset
			""";

	String COUNT_RESULTS = """
			SELECT count(*) FROM monster_models
			WHERE UPPER(monster_name) LIKE UPPER(CONCAT('%',:name,'%'))
			""";

	String COUNT_TOTAL = """
			SELECT count(*) from monster_models
			""";

	@Query(value = FIND_ALL, nativeQuery = true)
	List<MonsterModelPreviewDto> findAllPreviewDto(@Param("limit") Integer limit, @Param("offset") Integer offset);
	
	@Query(value = FIND_ALL_BY_NAME, nativeQuery = true)
	List<MonsterModelPreviewDto> findByNamePreviewDto(@Param("name") String name, @Param("limit") Integer limit, @Param("offset") Integer offset);

	@Query(value = COUNT_RESULTS, nativeQuery = true)
	Integer countResults(@Param("name") String name);

	@Query(value = COUNT_TOTAL, nativeQuery = true)
	Integer countTotal();

}
