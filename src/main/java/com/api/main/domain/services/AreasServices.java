package com.api.main.domain.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.api.main.domain.models.Areas;
import com.api.main.domain.repository.AreasRepository;

@Service
@Transactional
public class AreasServices {

	@Autowired
	private AreasRepository areasRepository;
	
	public static int NUM_AREA =4;
	
	    // Retornar Todas as Areas das Peovincial Listadas
	 
		public List<Areas> list(){
			
		return (List<Areas>) areasRepository.findAll(); }
		
		//retorn os dados organizado de forma ascidente e descidente 
		public Page<Areas> orderByData(Integer pageNum, String sortField,String sortDir) {
			
			Sort sort =Sort.by(sortField);
			
			sort = sortDir.equals("asc") ? sort.ascending():sort.descending();
			
			Pageable page = PageRequest.of(pageNum-1, NUM_AREA, sort);
					
			return areasRepository.findAll(page);
		}
		
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
