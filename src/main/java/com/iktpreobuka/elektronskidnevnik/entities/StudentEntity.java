package com.iktpreobuka.elektronskidnevnik.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="Ucenik")
public class StudentEntity extends UserEntity{

	@ManyToMany(fetch=FetchType.LAZY, cascade=CascadeType.REFRESH)
	@JoinTable(name="Parent_Student" , joinColumns= {@JoinColumn(name="studentId", 
		nullable=false, updatable=false)},
	inverseJoinColumns= {@JoinColumn(name="parentId", 
		nullable=false, updatable=false)})
	protected List<ParentEntity> parents=new ArrayList<>();
	
	@JsonIgnore
	@OneToMany(mappedBy="student", fetch=FetchType.LAZY, cascade= {CascadeType.REFRESH})
	@JsonBackReference
	private List<GradeEntity> grades=new ArrayList<>();
	
	@ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.REFRESH)
	@JoinColumn(name="clas")
	@JsonManagedReference
	private ClassEntity clas;

	public List<ParentEntity> getParents() {
		return parents;
	}

	public void setParents(List<ParentEntity> parents) {
		this.parents = parents;
	}

	public List<GradeEntity> getGrades() {
		return grades;
	}

	public void setGrades(List<GradeEntity> grades) {
		this.grades = grades;
	}

	public ClassEntity getClas() {
		return clas;
	}

	public void setClas(ClassEntity clas) {
		this.clas = clas;
	}

	public StudentEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public StudentEntity(Integer userId, @NotEmpty(message = "Morate uneti ime!") String name,
			@NotEmpty(message = "Morate uneti prezime!") String surname,
			@NotEmpty(message = "Morate uneti jmbg!") @Size(min = 13, max = 13, message = "Neispravan podatak. Jmbg mora imati 13 cifara.") String jmbg,
			@NotEmpty(message = "Morate uneti adresu!") String address,
			@NotEmpty(message = "Morate uneti mesto boravka!") String city,
			@Pattern(regexp = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$", message = "Email is not valid.") String email,
			String phoneNumber, @NotNull String username, String password, @NotNull Boolean deleted, RoleEntity role) {
		super(userId, name, surname, jmbg, address, city, email, phoneNumber, username, password, deleted, role);
		// TODO Auto-generated constructor stub
	}
}
