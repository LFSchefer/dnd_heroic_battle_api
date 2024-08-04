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
@Table(name = "sizes")
@Builder
@AllArgsConstructor
public class Size {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "size_name")
    private String sizeName;

    public Size() {
	// ORM
    }

    public Long getId() {
	return id;
    }

    @SuppressWarnings("unused")
    private void setId(Long id) {
	this.id = id;
    }

    public String getSizeName() {
	return sizeName;
    }

    public void setSizeName(String sizeName) {
	this.sizeName = sizeName;
    }

    @Override
    public String toString() {
	return "{id=" + id + ", sizeName=" + sizeName + "}";
    }

}
