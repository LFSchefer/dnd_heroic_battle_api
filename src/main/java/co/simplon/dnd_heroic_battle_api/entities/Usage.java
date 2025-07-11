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

@Builder
@AllArgsConstructor
@Entity
@Table(name = "usages")
public class Usage {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "usage_id")
	private Long usageId;

	@Column(name = "usage_type")
	private String usageType;

	@Column(name = "times")
	private Integer time;

	public Usage() {
		// ORM
	}

	public Long getUsageId() {
		return usageId;
	}

	@SuppressWarnings("unused")
	private void setUsageId(Long usageId) {
		this.usageId = usageId;
	}

	public String getUsageType() {
		return usageType;
	}

	public void setUsageType(String usageType) {
		this.usageType = usageType;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	@Override
	public String toString() {
		return "{usageId=" + usageId + ", usageType=" + usageType + ", time=" + time + "}";
	}

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof Usage usage)) return false;
        return Objects.equals(usageType, usage.usageType) && Objects.equals(time, usage.time);
	}

	@Override
	public int hashCode() {
		return Objects.hash(usageType, time);
	}
}
