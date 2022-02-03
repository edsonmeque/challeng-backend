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

import com.api.main.domain.models.Areas;
import com.api.main.domain.services.AreasServices;
@RestController
@RequestMapping("areas")
public class AreasController {
	@Autowired
	private AreasServices service;
    
	
	// retorn todas areas existente no sistema 
    @GetMapping
		private List<Areas> all(){
		return  orderByField(1,"length","desc");
	}

	/**
	 * para listar as provincia de acordo com asc ou desc usa a url abaixo sitada
	 * localhost:8080/areas/page/1?sortField=length&sortDir=desc
	 * */
    @GetMapping("/page/{pageNum}")
	private List<Areas> orderByField(
			@PathVariable(name="pageNum") int pageNum,
			@Param("sortField") String sortField,
			@Param("sortDir") String sortDir){
		
		System.out.print("sortDir"+sortDir);
		System.out.print("SortField"+sortField);
		
		Page<Areas> page = service.orderByData(pageNum, sortField, sortDir);
		
		List<Areas> listArea =page.getContent();
		
		return  listArea;
	}
	
	// encontra  uma aria especifica 
    @GetMapping("/{id}")
	private Areas findOne(@PathVariable Integer id) {
		
		return service.getId(id);
	}
	
	// salva uma area 
    @PostMapping
	private Areas store(@RequestBody Areas areas) {
		
		return service.saveAreas(areas);
	}
	
	// actualiza uma area especifica 
    @PutMapping("/{id}")
	private ResponseEntity<?> update(@RequestBody Areas area,@PathVariable Integer id){
			
		try {
			Areas existAreas = service.getId(id);
			service.saveAreas(area);
			
			return new ResponseEntity<>(HttpStatus.OK);
			
		} catch (NoSuchElementException  ex) {
			
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
    // elimina uma area especica 
	@DeleteMapping("/{id}")
	private void destroy(@PathVariable Integer id) {
		service.delete(id);
	}
	
}
