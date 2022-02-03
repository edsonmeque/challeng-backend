package com.api.main.domain.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class SubRegions {

	//======================================variables======================================
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY )
	private Integer id;
	
	@Column(name="name",length = 128)
	private String name;
	
	//====================================== Relationship==================================
	@JsonIgnore
	@ManyToOne
	private Regions region;

	//========================================Construtors ==================================
	public SubRegions() {

	}
	
	public SubRegions(String name, Regions region) {
		
		this.name = name;
	this.region = region;
	}
	
	//========================================== Get and Set Methods==========================

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Regions getRegion() {
		return region;
	}

	public void setRegion(Regions region) {
		this.region = region;
	}

	@Override
	public String toString() {
		return "SubRegions [id=" + id + ", name=" + name + ", region=" + region + "]";
	}
	
	
}
