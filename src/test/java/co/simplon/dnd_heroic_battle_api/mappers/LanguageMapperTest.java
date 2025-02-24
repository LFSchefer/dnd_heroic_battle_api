package co.simplon.dnd_heroic_battle_api.mappers;

import co.simplon.dnd_heroic_battle_api.entities.Language;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class LanguageMapperTest {

    @Test
    void entitiesToNames() {
        var language1 = Language.builder().languagesName("common").build();
        var language2 = Language.builder().languagesName("deep").build();
        var languages = Set.of(language1, language2);
        var actual =  assertDoesNotThrow(() -> LanguageMapper.entitiesToNames(languages));
        assertTrue(actual.contains(language1.getLanguagesName()));
        assertTrue(actual.contains(language2.getLanguagesName()));
    }
}