package com.api.main.domain.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
public class Regions {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="name",length = 128)
	private String name;
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="countrie_id")
	private Countries countrie;

	@OneToMany(targetEntity = SubRegions.class,cascade =CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="region_id",referencedColumnName = "id")
	private List<SubRegions> sub_regions = new ArrayList<>();
	
	public Regions() {
		
	}

	
	public List<SubRegions> getSub_regions() {
		return sub_regions;
	}

	public void setSub_regions(List<SubRegions> sub_regions) {
		this.sub_regions = sub_regions;
	}

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

   public Countries getCountrie() {
		return countrie;
	}

	public void setCountrie(Countries countrie) {
		this.countrie = countrie;
	}
	

}
