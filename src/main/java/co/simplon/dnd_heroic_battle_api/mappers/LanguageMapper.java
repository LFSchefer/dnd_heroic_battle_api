package co.simplon.dnd_heroic_battle_api.mappers;

import co.simplon.dnd_heroic_battle_api.entities.Language;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public final class LanguageMapper {

    private LanguageMapper() {
        throw new IllegalStateException("Utility class");
    }

    public static Set<String> entitiesToNames(Set<Language> languages) {
        return languages.stream().map(Language::getLanguagesName).collect(Collectors.toSet());
    }
}
