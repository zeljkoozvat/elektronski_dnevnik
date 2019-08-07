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
import javax.persistence.Transient;
import javax.persistence.Version;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name="korisnik")
public class UserEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="korisnik_id")
	private Integer userId;
	
	@NotEmpty(message="Morate uneti ime!")
	@Column(name="ime")
	private String name;
	
	@NotEmpty(message="Morate uneti prezime!")
	@Column(name="prezime")
	private String surname;
	
	@NotEmpty(message="Morate uneti jmbg!")
	@Column(name="jmbg")
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
	
	@Transient
	@JsonIgnore
	private Boolean isActive;
	
	@Version
	private Integer version;
	
	@ManyToOne(cascade=CascadeType.REFRESH, fetch=FetchType.LAZY)
	@JoinColumn(name="role")
	@JsonManagedReference
	private RoleEntity role;
	
}
