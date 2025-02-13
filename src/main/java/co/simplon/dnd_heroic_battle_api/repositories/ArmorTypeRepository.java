package co.simplon.dnd_heroic_battle_api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.simplon.dnd_heroic_battle_api.entities.ArmorType;

@Repository
public interface ArmorTypeRepository extends JpaRepository<ArmorType, Long> {

	ArmorType findByArmorType(String armorType);

}
