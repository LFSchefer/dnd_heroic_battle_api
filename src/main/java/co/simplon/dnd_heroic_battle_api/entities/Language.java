package co.simplon.dnd_heroic_battle_api.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.util.Objects;

@Entity
@Table(name = "languages")
@Builder
@AllArgsConstructor
public class Language {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "language_id")
	private long languageId;

	@Column(name = "languages_name")
	private String languagesName;

	public Language() {
		// ORM
	}

	public String getLanguagesName() {
		return languagesName;
	}

	public void setLanguagesName(String languagesName) {
		this.languagesName = languagesName;
	}

	public long getLanguageId() {
		return languageId;
	}

	public void setLanguageId(long languageId) {
		this.languageId = languageId;
	}

	@Override
	public String toString() {
		return "{languageId=" + languageId + ", languagesName=" + languagesName + "}";
	}

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof Language language)) return false;
        return Objects.equals(languagesName, language.languagesName);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(languagesName);
	}
}
