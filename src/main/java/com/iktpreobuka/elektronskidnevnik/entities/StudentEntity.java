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
@Table(name="Ucenik")
public class StudentEntity extends UserEntity{

	@ManyToMany(fetch=FetchType.LAZY, cascade=CascadeType.REFRESH)
	@JoinTable(name="Parent_Student" , joinColumns= {@JoinColumn(name="studentId", 
		nullable=false, updatable=false)},
	inverseJoinColumns= {@JoinColumn(name="parentId", 
		nullable=false, updatable=false)})
	protected List<ParentEntity> parents=new ArrayList<>();
}
