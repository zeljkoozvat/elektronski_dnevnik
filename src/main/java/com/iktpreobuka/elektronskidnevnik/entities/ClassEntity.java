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
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name="odeljenje")
public class ClassEntity {

	@ManyToOne(cascade=CascadeType.REFRESH, fetch=FetchType.LAZY)
	@JoinColumn(name="years")
	@JsonManagedReference
	private YearOfStudy years;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="odeljenje_id")
	private Integer classId;
	
	@Column(name="oznaka_odeljenja")
	@Size(min=2, max=4)
	private String className;
}
