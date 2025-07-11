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
@Table(name = "alignments")
@Builder
@AllArgsConstructor
public class Alignment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "alignment_id")
	private long alignmentId;

	@Column(name = "alignments_name")
	private String alignmentsName;

	@Column(name = "description")
	private String description;

	public Alignment() {
		// ORM
	}

	public String getAlignmentsName() {
		return alignmentsName;
	}

	public void setAlignmentsName(String alignmentsName) {
		this.alignmentsName = alignmentsName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public long getAlignmentId() {
		return alignmentId;
	}

	@SuppressWarnings("unused")
	private void setAlignmentId(long alignmentId) {
		this.alignmentId = alignmentId;
	}

	@Override
	public String toString() {
		return "{alignmentId=" + alignmentId + ", alignmentsName=" + alignmentsName + ", description=" + description + "}";
	}

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof Alignment alignment)) return false;
        return Objects.equals(alignmentsName, alignment.alignmentsName);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(alignmentsName);
	}
}
