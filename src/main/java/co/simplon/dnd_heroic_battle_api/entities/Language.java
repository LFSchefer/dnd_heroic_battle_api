package co.simplon.dnd_heroic_battle_api.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Entity
@Table(name = "languages")
@Builder
@AllArgsConstructor
public class Language {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "languages_name")
    private String languagesName;

    public Language() {
	// ORM
    }

    public long getId() {
	return id;
    }

    public void setId(long id) {
	this.id = id;
    }

    public String getLanguagesName() {
	return languagesName;
    }

    public void setLanguagesName(String languagesName) {
	this.languagesName = languagesName;
    }

    @Override
    public String toString() {
	return "{id=" + id + ", languagesName=" + languagesName + "}";
    }

}
