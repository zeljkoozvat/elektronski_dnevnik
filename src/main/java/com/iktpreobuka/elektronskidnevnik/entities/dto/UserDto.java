package com.iktpreobuka.elektronskidnevnik.entities.dto;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class UserDto {

	@NotEmpty(message="Morate uneti ime!")
	@Column(name="ime")
	private String name;
	
	@NotEmpty(message="Morate uneti prezime!")
	@Column(name="prezime")
	private String surname;
	
	@NotEmpty(message="Morate uneti jmbg!")
	@Column(name="jmbg")
	@Size(min=13, max=13, message="Neispravan podatak. Jmbg mora imati 13 cifara.")
	private String jmbg;
	
	@NotEmpty(message="Morate uneti adresu!")
	@Column(name="adresa")
	private String address;
	
	@NotEmpty(message="Morate uneti mesto boravka!")
	@Column(name="mesto_boravka")
	private String city;
	
	@Column
	@Pattern(regexp = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$",
	message="Email is not valid.")
	@NotNull
	private String email;
	
	@Column(name="broj_telefona")
	private String phoneNumber;
	
}
