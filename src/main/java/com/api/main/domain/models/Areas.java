package com.api.main.domain.models;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Areas {

	//======================================variables======================================
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name ="length")
	private double length;
	
	@Column(name="unit", length = 16)
	private String unit;
	
	//====================================== Relationship==================================
	@JsonIgnore
	@OneToOne(cascade = CascadeType.ALL)
	private Countries countrie;
	
	//========================================Construtors ==================================
	public Areas() {
	
	}

	public Areas(Integer id, double length, String unit) {
		this.id = id;
		this.length = length;
		this.unit = unit;	
	}
	

	public Areas(Integer id, double length, String unit, Countries countrie) {
		this.id = id;
		this.length = length;
		this.unit = unit;
		this.countrie = countrie;
	}
	
	//========================================== Get and Set Methods==========================

	public Countries getCountrie() {
		return countrie;
	}

	public void setCountrie(Countries countrie) {
		this.countrie = countrie;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public double getLength() {
		return length;
	}

	public void setLength(double length) {
		this.length = length;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	@Override
	public String toString() {
		return "Areas [id=" + id + ", length=" + length + ", unit=" + unit + ", countrie=" + countrie + "]";
	}
	
	
}

