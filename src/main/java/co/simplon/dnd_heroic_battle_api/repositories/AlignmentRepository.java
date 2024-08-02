package co.simplon.dnd_heroic_battle_api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import co.simplon.dnd_heroic_battle_api.entities.Alignment;

public interface AlignmentRepository extends JpaRepository<Alignment, Long> {

}
