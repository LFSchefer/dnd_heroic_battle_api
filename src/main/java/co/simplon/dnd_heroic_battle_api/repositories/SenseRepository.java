package co.simplon.dnd_heroic_battle_api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.simplon.dnd_heroic_battle_api.entities.Sense;

@Repository
public interface SenseRepository extends JpaRepository<Sense, Long> {

	Sense findByDarkvisionAndPassivePerception(Integer darkvision, Integer passivePerception);

}
