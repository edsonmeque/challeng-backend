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

import com.api.main.domain.models.Regions;
import com.api.main.domain.models.SubRegions;
import com.api.main.domain.services.SubRegionsServices;

@RestController
@RequestMapping("subregions")
public class SubRegionsController {
	@Autowired
	private SubRegionsServices service;
    
	// retorna todas sub regiao existente no sistema 
	@GetMapping
	private List<SubRegions> all(){
	return orderByField(1,"name","desc");	
}

	/**
	 * para listar as subregion de acordo com asc ou desc usa a url abaixo sitada
	 * localhost:8080/subregions/1?sortField=name&sortDir=asc
	 * */
@GetMapping("/page/{pageNum}")
private List<SubRegions> orderByField(
		@PathVariable(name="pageNum") int pageNum,
		@Param("sortField") String sortField,
		@Param("sortDir") String sortDir){
	
	Page<SubRegions> page = service.orderByData(pageNum, sortField, sortDir);
	
	List<SubRegions> listSubRegion =page.getContent();
	
	return  listSubRegion;
}
	// encontrar uma sub regiao especifica 
	@GetMapping("/{id}")
	private SubRegions findOne(@PathVariable Integer id) {
		
		return service.getId(id);
	}


	// salva uma regiao  especifica 
	@PostMapping
	private SubRegions store(@RequestBody SubRegions subRegion) {
		
		return service.saveSubRegions(subRegion);
	}
	
	//actualiza uma regiao especifica 
	@PutMapping("/{id}")
	private ResponseEntity<?> update(@RequestBody SubRegions subRegion,@PathVariable Integer id){
			
		try {
			SubRegions existSubRegion = service.getId(id);
			service.saveSubRegions(subRegion);
			
			return new ResponseEntity<>(HttpStatus.OK);
			
		} catch (NoSuchElementException  ex) {
			
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	//elimina uma regiao especifica 	
	@DeleteMapping("/{id}")
	private void destroy(@PathVariable Integer id) {
		service.delete(id);
	}
}
