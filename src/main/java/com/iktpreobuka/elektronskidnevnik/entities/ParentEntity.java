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
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name="Roditelj")
public class ParentEntity extends UserEntity{

	@ManyToMany(fetch=FetchType.LAZY, cascade=CascadeType.REFRESH)
	@JoinTable(name="Parent_Student" , joinColumns= {@JoinColumn(name="parentId", 
		nullable=false, updatable=false)},
	inverseJoinColumns= {@JoinColumn(name="studentId", 
		nullable=false, updatable=false)})
	protected List<StudentEntity> children = new ArrayList<>();

	public List<StudentEntity> getChildren() {
		return children;
	}

	public void setChildren(List<StudentEntity> children) {
		this.children = children;
	}

	public ParentEntity(Integer userId, @NotEmpty(message = "Morate uneti ime!") String name,
			@NotEmpty(message = "Morate uneti prezime!") String surname,
			@NotEmpty(message = "Morate uneti jmbg!") @Size(min = 13, max = 13, message = "Neispravan podatak. Jmbg mora imati 13 cifara.") String jmbg,
			@NotEmpty(message = "Morate uneti adresu!") String address,
			@NotEmpty(message = "Morate uneti mesto boravka!") String city,
			@Pattern(regexp = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$", message = "Email is not valid.") String email,
			String phoneNumber, @NotNull String deleted, RoleEntity role, UserAccount account) {
		super(userId, name, surname, jmbg, address, city, email, phoneNumber, deleted, role, account);
	}

	public ParentEntity(Integer userId, @NotEmpty(message = "Morate uneti ime!") String name,
			@NotEmpty(message = "Morate uneti prezime!") String surname,
			@NotEmpty(message = "Morate uneti jmbg!") @Size(min = 13, max = 13, message = "Neispravan podatak. Jmbg mora imati 13 cifara.") String jmbg,
			@NotEmpty(message = "Morate uneti adresu!") String address,
			@NotEmpty(message = "Morate uneti mesto boravka!") String city,
			@Pattern(regexp = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$", message = "Email is not valid.") String email,
			String phoneNumber, @NotNull String deleted, RoleEntity role, UserAccount account,
			List<StudentEntity> children) {
		super(userId, name, surname, jmbg, address, city, email, phoneNumber, deleted, role, account);
		this.children = children;
	}
}
