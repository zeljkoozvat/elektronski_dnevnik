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
import javax.persistence.Transient;
import javax.persistence.Version;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name="predmet")
@SQLDelete(sql="Update users SET deleted = 'true' where id=?")
@Where(clause="deleted != 'true'")
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
	
	@JsonIgnore
	@OneToMany(mappedBy="predmet", fetch=FetchType.LAZY, cascade= {CascadeType.REFRESH})
	@JsonBackReference
	private List<TeachesEntity> teacherClassSubjects=new ArrayList<>();
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="predmet_id")
	private Integer subjectId;
	
	@NotEmpty(message="Unesite naziv predmeta")
	@Column(name="naziv_predmeta")
	private String subjectName;
	
	@NotEmpty(message="Unesite nedeljni fond casova")
	@Column(name="nedeljni_fond_casova")
	private Integer weeklyTeachingHours;
	
	@NotEmpty(message="Unesite godisnji fond casova")
	@Column(name="godisnji_fond_casova")
	private Integer yearlyTeachingHours;
	
	@JsonIgnore
	@Column
	@NotNull
	private Boolean deleted;
	
	@Transient
	@Version
	private Integer version;
	
	public List<YearOfStudy> getYears() {
		return years;
	}

	public void setYears(List<YearOfStudy> years) {
		this.years = years;
	}

	public List<TeacherEntity> getTeachers() {
		return teachers;
	}

	public void setTeachers(List<TeacherEntity> teachers) {
		this.teachers = teachers;
	}

	public List<TeachesEntity> getTeacherClassSubjects() {
		return teacherClassSubjects;
	}

	public void setTeacherClassSubjects(List<TeachesEntity> teacherClassSubjects) {
		this.teacherClassSubjects = teacherClassSubjects;
	}

	public Integer getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(Integer subjectId) {
		this.subjectId = subjectId;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public Integer getWeeklyTeachingHours() {
		return weeklyTeachingHours;
	}

	public void setWeeklyTeachingHours(Integer weeklyTeachingHours) {
		this.weeklyTeachingHours = weeklyTeachingHours;
	}

	public Integer getYearlyTeachingHours() {
		return yearlyTeachingHours;
	}

	public void setYearlyTeachingHours(Integer yearlyTeachingHours) {
		this.yearlyTeachingHours = yearlyTeachingHours;
	}
	

	public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}
	

	public SubjectEntity(List<YearOfStudy> years, List<TeacherEntity> teachers,
			List<TeachesEntity> teacherClassSubjects, Integer subjectId,
			@NotEmpty(message = "Unesite naziv predmeta") String subjectName,
			@NotEmpty(message = "Unesite nedeljni fond casova") Integer weeklyTeachingHours,
			@NotEmpty(message = "Unesite godisnji fond casova") Integer yearlyTeachingHours, @NotNull Boolean deleted) {
		super();
		this.years = years;
		this.teachers = teachers;
		this.teacherClassSubjects = teacherClassSubjects;
		this.subjectId = subjectId;
		this.subjectName = subjectName;
		this.weeklyTeachingHours = weeklyTeachingHours;
		this.yearlyTeachingHours = yearlyTeachingHours;
		this.deleted = deleted;
	}

	public SubjectEntity() {
		super();
	}


	
	
}
