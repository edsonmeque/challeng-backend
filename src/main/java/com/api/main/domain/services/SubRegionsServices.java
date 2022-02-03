package com.api.main.domain.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.api.main.domain.models.SubRegions;
import com.api.main.domain.repository.SubRegionsRepository;

@Service
@Transactional
public class SubRegionsServices {
	@Autowired
	private  SubRegionsRepository subRegionsRepository;
	
	public static int NUM_COUNTRIE=4;
	
	// Retornar Todas as sub regioes com sua repeitiva propriedade
	public List<SubRegions> list(){
		
	return (List<SubRegions>) subRegionsRepository.findAll(); }
	
	//retorn os dados organizado de forma ascidente e descidente 
		public Page<SubRegions> orderByData(Integer pageNum, String sortField,String sortDir) {
			
			Sort sort =Sort.by(sortField);
			
			sort = sortDir.equals("asc") ? sort.ascending():sort.descending();
			
			Pageable page = PageRequest.of(pageNum-1, NUM_COUNTRIE, sort);
					
			return subRegionsRepository.findAll(page);
		}
	
	// salva uma sub regiao com suas propriedade 
	public SubRegions saveSubRegions(SubRegions subRegion) {
		
		return subRegionsRepository.save(subRegion);
	}
	//pega uma sub regiao especifica
	public SubRegions getId(Integer id) {
		
		return subRegionsRepository.findById(id).get();
	}
	
	//deleter uma regiao por id 
    public void delete(Integer id) {
		
    	subRegionsRepository.deleteById(id);
	}
	
	

}
