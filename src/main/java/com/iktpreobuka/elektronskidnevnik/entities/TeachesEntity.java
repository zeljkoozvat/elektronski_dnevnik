package com.iktpreobuka.elektronskidnevnik.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name="predaje")
public class TeachesEntity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private Integer teachesId;

	@ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.REFRESH)
	@JoinColumn(name="nastavnik")
	@JsonManagedReference
	private TeacherEntity nastavnik;
	
	@ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.REFRESH)
	@JoinColumn(name="predmet")
	@JsonManagedReference
	private SubjectEntity predmet;
	
	@ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.REFRESH)
	@JoinColumn(name="razred")
	@JsonManagedReference
	private ClassEntity razred;
	
	@JsonIgnore
	@OneToMany(mappedBy="predaje", fetch=FetchType.LAZY, cascade= {CascadeType.REFRESH})
	@JsonBackReference
	private List<GradeEntity> grade=new ArrayList<>();

	public TeacherEntity getNastavnik() {
		return nastavnik;
	}

	public void setNastavnik(TeacherEntity nastavnik) {
		this.nastavnik = nastavnik;
	}

	public SubjectEntity getPredmet() {
		return predmet;
	}

	public void setPredmet(SubjectEntity predmet) {
		this.predmet = predmet;
	}

	public ClassEntity getRazred() {
		return razred;
	}

	public void setRazred(ClassEntity razred) {
		this.razred = razred;
	}

	public List<GradeEntity> getGrade() {
		return grade;
	}

	public void setGrade(List<GradeEntity> grade) {
		this.grade = grade;
	}

	public TeachesEntity() {
		super();
	}

	public TeachesEntity(TeacherEntity nastavnik, SubjectEntity predmet, ClassEntity razred, List<GradeEntity> grade) {
		super();
		this.nastavnik = nastavnik;
		this.predmet = predmet;
		this.razred = razred;
		this.grade = grade;
	}
}
