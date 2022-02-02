package com.api.main.domain.controllers;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.api.main.domain.models.Countries;
import com.api.main.domain.repository.CountriesRepository;
import com.api.main.domain.services.CountriesServices;



@RestController
@RequestMapping("countries")
public class CountriesController {

	@Autowired
	private CountriesServices service;
    @Autowired
	private CountriesRepository CountriesRepository;
    public Page<Countries> getCountries(
			@RequestParam Optional<Integer> page,
			@RequestParam Optional<String> sortBy){
		return  CountriesRepository.findAll(		
				       PageRequest.of(page.orElse(0),
						5,Sort.Direction.ASC, 
						sortBy.orElse("id"))); }
    @GetMapping
		private List<Countries> all(){
		return service.list();	
	}
	
	@GetMapping("/{id}")
	private Countries findOne(@PathVariable Integer id) {
		
		return service.getId(id);
	}


	@GetMapping("/page/{parm}")
	private List<Countries> orderByField(
			@PathVariable(name="pageNum") int pageNum,
			@Param("sortField") String sortField,
			@Param("sortDir") String sortDir){
		
		System.out.print("sortDir"+sortDir);
		System.out.print("SortField"+sortField);
		
		Page<Countries> page = service.orderByData(pageNum, sortField, sortDir);
		
		List<Countries> listCountries =page.getContent();
		
		
		long startCount =(pageNum-1)*service.NUM_COUNTRIE+1;
		long endCount = startCount + service.NUM_COUNTRIE-1;
		
		if(endCount>page.getTotalElements()) {
			endCount = page.getTotalElements();
		}
		String reverseSortDir = sortDir.equals("asc")? "desc":"asc";
		
		return  listCountries;
	}
	
	@PostMapping
	private Countries store(@RequestBody Countries countrie) {
		
		return service.saveCountries(countrie);
	}
	
	@PutMapping("/{id}")
	private ResponseEntity<?> update(@RequestBody Countries countrie,@PathVariable Integer id){
			
		try {
			Countries existCountrie = service.getId(id);
			service.saveCountries(existCountrie);
			
			return new ResponseEntity<>(HttpStatus.OK);
			
		} catch (NoSuchElementException  ex) {
			
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
		
	@DeleteMapping("/{id}")
	private void destroy(@PathVariable Integer id) {
		service.delete(id);
	}
	
}
