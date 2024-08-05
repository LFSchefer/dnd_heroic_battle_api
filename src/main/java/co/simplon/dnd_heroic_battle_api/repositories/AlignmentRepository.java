package co.simplon.dnd_heroic_battle_api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.simplon.dnd_heroic_battle_api.entities.Alignment;

@Repository
public interface AlignmentRepository extends JpaRepository<Alignment, Long> {

	Alignment findByAlignmentsName(String alignmentsName);

	Alignment findByAlignmentsNameIgnoreCase(String alignmentsName);

}
