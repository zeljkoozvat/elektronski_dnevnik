package com.iktpreobuka.elektronskidnevnik.entities;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name="Administrator")
public class AdminEntity extends UserEntity{

	public AdminEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AdminEntity(Integer userId, @NotEmpty(message = "Morate uneti ime!") String name,
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
