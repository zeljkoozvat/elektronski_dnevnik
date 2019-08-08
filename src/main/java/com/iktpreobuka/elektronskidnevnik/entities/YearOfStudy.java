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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="razred")
public class YearOfStudy {
	
	@JsonIgnore
	@OneToMany(mappedBy="year", fetch=FetchType.LAZY, cascade= {CascadeType.REFRESH})
	@JsonBackReference
	private List<ClassEntity> classes=new ArrayList<>();
	
	public YearOfStudy() {
		super();
	}

	public YearOfStudy(List<ClassEntity> classes, List<SubjectEntity> subjects, Integer yearOfStudyId, Integer year) {
		super();
		this.classes = classes;
		this.subjects = subjects;
		this.yearOfStudyId = yearOfStudyId;
		this.year = year;
	}

	public List<ClassEntity> getClasses() {
		return classes;
	}

	public void setClasses(List<ClassEntity> classes) {
		this.classes = classes;
	}

	public List<SubjectEntity> getSubjects() {
		return subjects;
	}

	public void setSubjects(List<SubjectEntity> subjects) {
		this.subjects = subjects;
	}

	public Integer getYearOfStudyId() {
		return yearOfStudyId;
	}

	public void setYearOfStudyId(Integer yearOfStudyId) {
		this.yearOfStudyId = yearOfStudyId;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	@ManyToMany(fetch=FetchType.LAZY, cascade=CascadeType.REFRESH)
	@JoinTable(name="Subject_Year" , joinColumns= {@JoinColumn(name="yearOfStudyId", 
		nullable=false, updatable=false)},
	inverseJoinColumns= {@JoinColumn(name="subjectId", 
		nullable=false, updatable=false)})
	protected List<SubjectEntity> subjects=new ArrayList<>();
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="razred_id")
	private Integer yearOfStudyId;
	
	@Column(name="razred")
	private Integer year;
}
