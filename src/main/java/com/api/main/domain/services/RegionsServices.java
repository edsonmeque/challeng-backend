package com.api.main.domain.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.api.main.domain.models.Regions;
import com.api.main.domain.repository.RegionsRepository;

@Service
@Transactional
public class RegionsServices {
	@Autowired
	private  RegionsRepository regionsRepository;
	
	public static int NUM_COUNTRIE=4;
	
	// Retornar Todas as regioes com sua repeitiva propriedade
	public List<Regions> list(){
		
	return (List<Regions>) regionsRepository.findAll(); }
	
	//retorna os dados organizado de forma ascidente e descidente 
		public Page<Regions> orderByData(Integer pageNum, String sortField,String sortDir) {
			
			Sort sort =Sort.by(sortField);
			
			sort = sortDir.equals("asc") ? sort.ascending():sort.descending();
			
			Pageable page = PageRequest.of(pageNum-1, NUM_COUNTRIE, sort);
					
			return regionsRepository.findAll(page);
		}
	
	// salva uma regiao com suas propriedade 
	public Regions saveRegion(Regions region) {
		
		return regionsRepository.save(region);
	}
	
	//pega uma regiao especifica
	public Regions getId(Integer id) {
		
		return regionsRepository.findById(id).get();
	}
	
	//deleter uma regiao por id 
    public void delete(Integer id) {
		
		regionsRepository.deleteById(id);
	}
	
}
