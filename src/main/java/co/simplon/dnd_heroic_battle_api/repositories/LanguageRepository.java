package co.simplon.dnd_heroic_battle_api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.simplon.dnd_heroic_battle_api.entities.Language;

@Repository
public interface LanguageRepository extends JpaRepository<Language, Long> {

	Language findByLanguagesName(String name);

}
