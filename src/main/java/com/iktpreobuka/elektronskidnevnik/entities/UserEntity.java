package com.iktpreobuka.elektronskidnevnik.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name="Korisnik")
public class UserEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="korisnik_id")
	private Integer id;
	
	@NotEmpty(message="Morate uneti ime!")
	@Column(name="Ime")
	private String name;
	
	@NotEmpty(message="Morate uneti prezime!")
	@Column(name="Prezime")
	private String surname;
	
	@NotEmpty(message="Morate uneti jmbg!")
	@Column(name="Jmbg")
	@Size(min=13, max=13)
	private String jmbg;
	
	@NotEmpty(message="Morate uneti adresu!")
	@Column(name="adresa")
	private String address;
	
	@Column(name="broj_telefona")
	private String phoneNumber;
	
	@Column(name="password")
	@JsonIgnore
	private String password;
	
	@Version
	private Integer version;
	
	@ManyToOne(cascade=CascadeType.REFRESH, fetch=FetchType.LAZY)
	@JoinColumn(name="role")
	private RoleEntity role;
	
}
