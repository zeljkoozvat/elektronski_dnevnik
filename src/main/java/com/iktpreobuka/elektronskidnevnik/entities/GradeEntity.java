package com.iktpreobuka.elektronskidnevnik.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.iktpreobuka.elektronskidnevnik.enumeration.EGradeType;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name="ocena")
public class GradeEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ocena_id")
	private Integer gradeId;
	
	@Column(name="ocena")
	private EGradeType vrednostOcene;
	
	@ManyToOne(cascade=CascadeType.REFRESH, fetch=FetchType.LAZY)
	@JoinColumn(name="student")
	@JsonManagedReference
	private StudentEntity student;
	
	@ManyToOne(cascade=CascadeType.REFRESH, fetch=FetchType.LAZY)
	@JoinColumn(name="predaje")
	@JsonManagedReference
	private TeachesEntity predaje;

	public Integer getGradeId() {
		return gradeId;
	}

	public void setGradeId(Integer gradeId) {
		this.gradeId = gradeId;
	}

	public EGradeType getVrednostOcene() {
		return vrednostOcene;
	}

	public void setVrednostOcene(EGradeType vrednostOcene) {
		this.vrednostOcene = vrednostOcene;
	}

	public StudentEntity getStudent() {
		return student;
	}

	public void setStudent(StudentEntity student) {
		this.student = student;
	}

	public GradeEntity() {
		super();
	}

	public GradeEntity(Integer gradeId, EGradeType vrednostOcene, StudentEntity student, TeachesEntity predaje) {
		super();
		this.gradeId = gradeId;
		this.vrednostOcene = vrednostOcene;
		this.student = student;
		this.predaje = predaje;
	}
}
