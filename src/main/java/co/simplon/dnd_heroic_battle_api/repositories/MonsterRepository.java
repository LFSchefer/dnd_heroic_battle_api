package co.simplon.dnd_heroic_battle_api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import co.simplon.dnd_heroic_battle_api.dtos.monster.MonsterPreviewDto;
import co.simplon.dnd_heroic_battle_api.entities.Monster;

@Repository
public interface MonsterRepository extends JpaRepository<Monster, Long> {

	String FIND_ALL = """
			SELECT monster_id, monster_name, challenge_rating FROM monsters
			LIMIT :limit
			OFFSET :offset
			""";
	
	String FIND_ALL_BY_NAME = """
			SELECT monster_id, monster_name, challenge_rating FROM monsters
			WHERE UPPER(monster_name) LIKE UPPER(CONCAT('%',:name,'%'))
			LIMIT :limit
			OFFSET :offset
			""";

	@Query(value = FIND_ALL, nativeQuery = true)
	List<MonsterPreviewDto> findAllPreviewDto(@Param("limit") Integer limit, @Param("offset") Integer offset);
	
	@Query(value = FIND_ALL_BY_NAME, nativeQuery = true)
	List<MonsterPreviewDto> findByNamePreviewDto(@Param("name") String name, @Param("limit") Integer limit, @Param("offset") Integer offset);

}
