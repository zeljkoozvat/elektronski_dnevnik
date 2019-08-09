package com.iktpreobuka.elektronskidnevnik.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Version;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="Nastavnik")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@SQLDelete(sql="Update users SET deleted = 'true' where id=?")
@Where(clause="deleted != 'true'")
public class TeacherEntity extends UserEntity{

	@ManyToMany(fetch=FetchType.LAZY, cascade=CascadeType.REFRESH)
	@JoinTable(name="Teacher_Subject" , joinColumns= {@JoinColumn(name="teacherId", 
		nullable=false, updatable=false)},
	inverseJoinColumns= {@JoinColumn(name="subjectId", 
		nullable=false, updatable=false)})
	protected List<SubjectEntity> subjects = new ArrayList<>();
	
	@JsonIgnore
	@OneToMany(mappedBy="nastavnik", fetch=FetchType.LAZY, cascade= {CascadeType.REFRESH})
	@JsonBackReference
	private List<TeachesEntity> teacherClassSubjects=new ArrayList<>();
	
	@Version
	@Transient
	private Integer version;


	public List<SubjectEntity> getSubjects() {
		return subjects;
	}

	public void setSubjects(List<SubjectEntity> subjects) {
		this.subjects = subjects;
	}

	public List<TeachesEntity> getTeacherClassSubjects() {
		return teacherClassSubjects;
	}

	public void setTeacherClassSubjects(List<TeachesEntity> teacherClassSubjects) {
		this.teacherClassSubjects = teacherClassSubjects;
	}

	public TeacherEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TeacherEntity(Integer userId, @NotEmpty(message = "Morate uneti ime!") String name,
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
