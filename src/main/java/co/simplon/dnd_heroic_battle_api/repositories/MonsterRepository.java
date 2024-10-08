package co.simplon.dnd_heroic_battle_api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import co.simplon.dnd_heroic_battle_api.dtos.monster.MonsterPreviewDto;
import co.simplon.dnd_heroic_battle_api.entities.Monster;

@Repository
public interface MonsterRepository extends JpaRepository<Monster, Long> {

	String FIND_ALL = """
			SELECT m FROM Monster m
			""";

	@Query(value = FIND_ALL)
	List<MonsterPreviewDto> findAllPreviewDtos();

}
