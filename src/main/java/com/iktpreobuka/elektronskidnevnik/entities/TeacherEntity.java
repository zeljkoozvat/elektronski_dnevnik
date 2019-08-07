package com.iktpreobuka.elektronskidnevnik.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="Nastavnik")
public class TeacherEntity extends UserEntity{

	@ManyToMany(fetch=FetchType.LAZY, cascade=CascadeType.REFRESH)
	@JoinTable(name="Teacher_Subject" , joinColumns= {@JoinColumn(name="teacherId", 
		nullable=false, updatable=false)},
	inverseJoinColumns= {@JoinColumn(name="subjectId", 
		nullable=false, updatable=false)})
	protected List<SubjectEntity> subjects = new ArrayList<>();
}
