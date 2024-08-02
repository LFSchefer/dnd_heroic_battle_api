package co.simplon.dnd_heroic_battle_api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import co.simplon.dnd_heroic_battle_api.entities.DamageType;

public interface DamageTypeRepository extends JpaRepository<DamageType, Long> {

}
