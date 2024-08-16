package co.simplon.dnd_heroic_battle_api.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Builder
@AllArgsConstructor
@Entity
@Table(name = "dcs")
public class Dc {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "dc_id")
	private Long dcId;

	@Column(name = "dc_type")
	private String dcType;

	@Column(name = "dc_value")
	private Integer dcValue;

	@Column(name = "success_type")
	private String successType;

	public Dc() {
		// ORM
	}

	public Long getId() {
		return dcId;
	}

	@SuppressWarnings("unused")
	private void setId(Long dcId) {
		this.dcId = dcId;
	}

	public String getDcType() {
		return dcType;
	}

	public void setDcType(String dcType) {
		this.dcType = dcType;
	}

	public Integer getDcValue() {
		return dcValue;
	}

	public void setDcValue(Integer dcValue) {
		this.dcValue = dcValue;
	}

	public String getSuccessType() {
		return successType;
	}

	public void setSuccessType(String successType) {
		this.successType = successType;
	}

	@Override
	public String toString() {
		return "{dcId=" + dcId + ", dcType=" + dcType + ", dcValue=" + dcValue + ", successType=" + successType + "}";
	}

}
