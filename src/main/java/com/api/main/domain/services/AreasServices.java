package com.api.main.domain.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.api.main.domain.models.Areas;
import com.api.main.domain.models.Countries;
import com.api.main.domain.repository.AreasRepository;

@Service
@Transactional
public class AreasServices {

	@Autowired
	private AreasRepository areasRepository;
	
	// Retornar Todas as Areas das Peovincial Listadas 
		public List<Areas> list(){
			
		return (List<Areas>) areasRepository.findAll(); }
		
		// salvar a area de uma determinadas provincia 
		public Areas saveAreas(Areas areas) {
			
		return areasRepository.save(areas);	}
		
		
		
		//pegar uma area especifica 
		public Areas getId(Integer id) {
			
			return  areasRepository.findById(id).get();
		}
		
		
		//deleta uma provincia com suas respeitivas propriedade 
	    public void delete(Integer id) {
			
	    	 areasRepository.deleteById(id);
		}
		
		
}
