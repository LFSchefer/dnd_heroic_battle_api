package co.simplon.dnd_heroic_battle_api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.simplon.dnd_heroic_battle_api.entities.SpecialAbility;

@Repository
public interface SpecialAbilityRepository extends JpaRepository<SpecialAbility, Long> {

	SpecialAbility findBySpecialAbilityNameAndSpecialAbilityDescription(String specialAbilityName, String specialAbilityDescription);

}
