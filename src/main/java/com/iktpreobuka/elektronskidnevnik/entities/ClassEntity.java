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
import javax.persistence.Transient;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name="odeljenje")
@SQLDelete(sql="Update users SET deleted = 'true' where id=?")
@Where(clause="deleted != 'true'")
public class ClassEntity {

	@ManyToOne(cascade=CascadeType.REFRESH, fetch=FetchType.LAZY)
	@JoinColumn(name="year")
	@JsonManagedReference
	private YearOfStudy year;
	
	@JsonIgnore
	@OneToMany(mappedBy="clas", fetch=FetchType.LAZY, cascade= {CascadeType.REFRESH})
	@JsonBackReference(value="clas")
	private List<StudentEntity> students=new ArrayList<>();
	
	@JsonIgnore
	@OneToMany(mappedBy="razred", fetch=FetchType.LAZY, cascade= {CascadeType.REFRESH})
	@JsonBackReference(value="razred")
	private List<TeachesEntity> teacherClassSubjects=new ArrayList<>();
	
	@JsonIgnore
	@OneToMany(mappedBy="student", fetch=FetchType.LAZY, cascade= {CascadeType.REFRESH})
	@JsonBackReference
	private List<GradeEntity> grades=new ArrayList<>();
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="odeljenje_id")
	private Integer classId;
	
	@Column(name="oznaka_odeljenja")
	@Size(min=2, max=4)
	private String className;
	
	@JsonIgnore
	@Column
	@NotNull
	private String deleted;

	@Transient
	@Version
	private Integer version;
	
	public YearOfStudy getYear() {
		return year;
	}

	public void setYear(YearOfStudy year) {
		this.year = year;
	}

	public List<StudentEntity> getStudents() {
		return students;
	}

	public void setStudents(List<StudentEntity> students) {
		this.students = students;
	}

	public List<TeachesEntity> getTeacherClassSubjects() {
		return teacherClassSubjects;
	}

	public void setTeacherClassSubjects(List<TeachesEntity> teacherClassSubjects) {
		this.teacherClassSubjects = teacherClassSubjects;
	}

	public List<GradeEntity> getGrades() {
		return grades;
	}

	public void setGrades(List<GradeEntity> grades) {
		this.grades = grades;
	}

	public Integer getClassId() {
		return classId;
	}

	public void setClassId(Integer classId) {
		this.classId = classId;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public ClassEntity() {
		super();
	}

	public ClassEntity(YearOfStudy year, List<StudentEntity> students, List<TeachesEntity> teacherClassSubjects,
			List<GradeEntity> grades, Integer classId, @Size(min = 2, max = 4) String className,
			@NotNull String deleted) {
		super();
		this.year = year;
		this.students = students;
		this.teacherClassSubjects = teacherClassSubjects;
		this.grades = grades;
		this.classId = classId;
		this.className = className;
		this.deleted = deleted;
	}

	public String getDeleted() {
		return deleted;
	}

	public void setDeleted(String deleted) {
		this.deleted = deleted;
	}
	
}
