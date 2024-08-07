package co.simplon.dnd_heroic_battle_api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.simplon.dnd_heroic_battle_api.entities.ArmorClass;

@Repository
public interface ArmorClassRepository extends JpaRepository<ArmorClass, Long> {

	ArmorClass findByArmorTypeAndArmorValue(String armorType, Integer armorValue);

}
