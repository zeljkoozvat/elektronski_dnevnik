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
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name="predmet")
public class SubjectEntity {

	@ManyToMany(fetch=FetchType.LAZY, cascade=CascadeType.REFRESH)
	@JoinTable(name="Subject_Year" , joinColumns= {@JoinColumn(name="subjectId", 
		nullable=false, updatable=false)},
	inverseJoinColumns= {@JoinColumn(name="yearOfStudyId", 
		nullable=false, updatable=false)})
	protected List<YearOfStudy> years=new ArrayList<>();
	
	@ManyToMany(fetch=FetchType.LAZY, cascade=CascadeType.REFRESH)
	@JoinTable(name="Teacher_Subject" , joinColumns= {@JoinColumn(name="subjectId", 
		nullable=false, updatable=false)},
	inverseJoinColumns= {@JoinColumn(name="teacherId", 
		nullable=false, updatable=false)})
	protected List<TeacherEntity> teachers=new ArrayList<>();
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="predmet_id")
	private Integer subjectId;
	
	@NotEmpty(message="Unesite naziv predmeta")
	@Column(name="naziv_predmeta")
	private String subjectName;
	
	@NotEmpty
	@Column(name="nedeljni_fond_casova")
	private Integer weeklyTeachingHours;
	
	@NotEmpty
	@Column(name="godisnji_fond_casova")
	private Integer yearlyTeachingHours;
	
	@Version
	private Integer version;
}
