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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Countries {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id; 
	
	@Column(name="name", length = 128)
	private String name;
	
	@Column(name="capital",length = 128)
	private String Capital;
	
	@OneToMany(targetEntity = Regions.class,cascade =CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="countrie_id",referencedColumnName = "id")
	private List<Regions> regions = new ArrayList<>();
		
	@OneToOne(targetEntity = Areas.class,cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="area_id",referencedColumnName = "id")
	private  Areas area;

	public Countries() {
	}

	public Countries(Integer id, String name, String capital, List<Regions> regions, Areas area) {
		this.id = id;
		this.name = name;
		Capital = capital;
		this.regions = regions;
		this.area = area;
	}

	public Integer getId() {
		return id;
	}

	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCapital() {
		return Capital;
	}

	public void setCapital(String capital) {
		Capital = capital;
	}

	public List<Regions> getRegions() {
		return regions;
	}

	public void setRegions(List<Regions> regions) {
		this.regions = regions;
	}

	public Areas getArea() {
		return area;
	}

	public void setAreas(Areas area) {
		this.area = area;
	}
	
}
