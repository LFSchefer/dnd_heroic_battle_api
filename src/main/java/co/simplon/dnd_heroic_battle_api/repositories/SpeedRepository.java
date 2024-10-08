package co.simplon.dnd_heroic_battle_api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.simplon.dnd_heroic_battle_api.entities.Speed;

@Repository
public interface SpeedRepository extends JpaRepository<Speed, Long> {

	Speed findByWalkAndSwimAndFly(Short walk, Short swim, Short fly);

}
