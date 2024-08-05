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
	@Column(name = "size_id")
	private Long sizeId;

	@Column(name = "size_name")
	private String sizeName;

	public Size() {
		// ORM
	}

	public String getSizeName() {
		return sizeName;
	}

	public void setSizeName(String sizeName) {
		this.sizeName = sizeName;
	}

	public Long getSizeId() {
		return sizeId;
	}

	@SuppressWarnings("unused")
	private void setSizeId(Long sizeId) {
		this.sizeId = sizeId;
	}

	@Override
	public String toString() {
		return "{sizeId=" + sizeId + ", sizeName=" + sizeName + "}";
	}

}
