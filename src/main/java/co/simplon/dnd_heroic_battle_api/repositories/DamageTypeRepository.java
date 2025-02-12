package co.simplon.dnd_heroic_battle_api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.simplon.dnd_heroic_battle_api.entities.DamageType;

@Repository
public interface DamageTypeRepository extends JpaRepository<DamageType, Long> {

	DamageType findByDamageTypeName(String damageTypeName);
	
	DamageType findByDamageTypeNameIgnoreCase(String damageTypeName);


}
