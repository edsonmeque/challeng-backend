package com.api.main.domain.controllers;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.main.domain.models.Countries;
import com.api.main.domain.services.CountriesServices;

@RestController
@RequestMapping("countries")
public class CountriesController {

	@Autowired
	private CountriesServices service;
	
	// retorna todos os paises exixtente no sistema 
	@GetMapping
	private List<Countries> all(){
		
		return orderByField(1,"name","desc");
	}
	
	//encontra um pais especifico 
	@GetMapping("/{id}")
	private ResponseEntity<Countries> findOne(@PathVariable Integer id) {
		
		try {
			
			Countries countries = service.getId(id);
			
			return new ResponseEntity<Countries>(countries, HttpStatus.OK);
		} catch (NoSuchElementException ex) {
			
			return new ResponseEntity<Countries>(HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * para listar as provincia de acordo com asc ou desc usa a url abaixo sitada
	 * localhost:8080/countries/page/1?sortField=name&sortDir=asc
	 * */

	@GetMapping("/page/{pageNum}")
	private List<Countries> orderByField(
			@PathVariable(name="pageNum") int pageNum,
			@Param("sortField") String sortField,
			@Param("sortDir") String sortDir){
		
		Page<Countries> page = service.orderByData(pageNum, sortField, sortDir);
		
		List<Countries> listCountries =page.getContent();
		
		return  listCountries;
	}
	

	// salva um pais especifico 
	@PostMapping
	private Countries store(@RequestBody Countries countrie) {
		
		return service.saveCountries(countrie);
	}
	
	// actualiza um pais especifico 
	@PutMapping("/{id}")
	private ResponseEntity<?> update(@RequestBody Countries countrie,@PathVariable Integer id){
			
		try {
			Countries existCountrie = service.getId(id);
		
			service.saveCountries(countrie);
			
			return new ResponseEntity<>(HttpStatus.OK);
			
		} catch (NoSuchElementException  ex) {
			
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	// deleta um pais especifico 
	@DeleteMapping("/{id}")
	private void destroy(@PathVariable Integer id) {
		service.delete(id);
	}
	
}
