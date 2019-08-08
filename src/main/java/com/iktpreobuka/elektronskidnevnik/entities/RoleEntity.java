package com.iktpreobuka.elektronskidnevnik.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.iktpreobuka.elektronskidnevnik.enumeration.ERoleName;

@Entity
@Table(name="role")
public class RoleEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private Integer roleId;
	
	@Column(name="naziv_uloge")
	private ERoleName roleName;
	
	@Version
	private Integer version;
	
	@JsonIgnore
	@OneToMany(mappedBy="role", fetch = FetchType.LAZY, cascade= {CascadeType.REFRESH} )
	@JsonBackReference
	private List<UserEntity> users = new ArrayList<>();
	
	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public ERoleName getRoleName() {
		return roleName;
	}

	public void setRoleName(ERoleName roleName) {
		this.roleName = roleName;
	}

	public List<UserEntity> getUsers() {
		return users;
	}

	public void setUsers(List<UserEntity> users) {
		this.users = users;
	}

	public RoleEntity() {
		super();
	}

	public RoleEntity(Integer roleId, ERoleName roleName, List<UserEntity> users) {
		super();
		this.roleId = roleId;
		this.roleName = roleName;
		this.users = users;
	}

	
}
