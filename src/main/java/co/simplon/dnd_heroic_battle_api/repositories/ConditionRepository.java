package co.simplon.dnd_heroic_battle_api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.simplon.dnd_heroic_battle_api.entities.Condition;

@Repository
public interface ConditionRepository extends JpaRepository<Condition, Long> {

	Condition findByConditionName(String name);

}
