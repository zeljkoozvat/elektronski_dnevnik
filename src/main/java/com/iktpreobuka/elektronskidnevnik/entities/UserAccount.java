package com.iktpreobuka.elektronskidnevnik.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="korisnicki_nalog")
@SQLDelete(sql="Update users SET deleted = 'true' where id=?")
@Where(clause="deleted != 'true'")
public class UserAccount {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private Integer accountId;
	
	@NotNull
	@Column(name="korisnicko_ime", unique=true)
	private String username;
	
	@Column(name="password", nullable=false, unique=true)
	@JsonIgnore
	private String password;
	
	@JsonManagedReference
	@OneToOne(cascade = CascadeType.REFRESH,fetch = FetchType.LAZY)
	@JoinColumn(name="user")
	private UserEntity user;
	
	@JsonIgnore
	@Column
	@NotNull
	private String deleted;
	
	@Version
	private Integer version;

	public Integer getAccountId() {
		return accountId;
	}

	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

	public UserAccount() {
		super();
	}

	public UserAccount(Integer accountId, @NotNull String username, String password, UserEntity user,
			@NotNull String deleted) {
		super();
		this.accountId = accountId;
		this.username = username;
		this.password = password;
		this.user = user;
		this.deleted = deleted;
	}

	public String getDeleted() {
		return deleted;
	}

	public void setDeleted(String deleted) {
		this.deleted = deleted;
	}

}
