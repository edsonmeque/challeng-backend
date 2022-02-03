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
import com.api.main.domain.services.RegionsServices;

@RestController
@RequestMapping("regions")
public class RegionsController {

	@Autowired
	private RegionsServices service;
	
	// retorna  todas regioes  existente no sistema 
    @GetMapping
		private List<Regions> all(){
		return orderByField(1,"name","desc");	
	}
    
    /**
	 * para listar as region de acordo com asc ou desc usa a url abaixo sitada
	 * localhost:8080/regions/1?sortField=name&sortDir=asc
	 * */
    
    @GetMapping("/page/{pageNum}")
	private List<Regions> orderByField(
			@PathVariable(name="pageNum") int pageNum,
			@Param("sortField") String sortField,
			@Param("sortDir") String sortDir){
		
		Page<Regions> page = service.orderByData(pageNum, sortField, sortDir);
		
		List<Regions> listRegion =page.getContent();
		
		return  listRegion;
	}
	
	// encontra uma regiao especifica com suas propriedade 
	@GetMapping("/{id}")
	private Regions findOne(@PathVariable Integer id) {
		
		return service.getId(id);
	}
	
	// salva uma regiao especifica 
	@PostMapping
	private Regions store(@RequestBody Regions region) {
		
		return service.saveRegion(region);
	}
	
	// actualiza uma regiao especifica 
	@PutMapping("/{id}")
	private ResponseEntity<?> update(@RequestBody Regions region,@PathVariable Integer id){
			
		try {
			Regions existRegion = service.getId(id);
			service.saveRegion(region);
			
			return new ResponseEntity<>(HttpStatus.OK);
			
		} catch (NoSuchElementException  ex) {
			
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
		
	// elimina uma regiao especifica 
	@DeleteMapping("/{id}")
	private void destroy(@PathVariable Integer id) {
		service.delete(id);
	}
}
