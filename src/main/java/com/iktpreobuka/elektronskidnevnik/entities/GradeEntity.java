package com.iktpreobuka.elektronskidnevnik.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.iktpreobuka.elektronskidnevnik.enumeration.EGradeType;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name="ocena")
public class GradeEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ocena_id")
	private Integer gradeId;
	
	@Column(name="ocena")
	private EGradeType vrednostOcene;
}
