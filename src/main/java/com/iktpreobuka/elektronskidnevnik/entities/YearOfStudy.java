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
	@OneToMany(mappedBy="years", fetch=FetchType.LAZY, cascade= {CascadeType.REFRESH})
	@JsonBackReference
	private List<ClassEntity> classes=new ArrayList<>();
	
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
