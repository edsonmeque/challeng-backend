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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.api.main.domain.models.Countries;
import com.api.main.domain.repository.CountriesRepository;

@Service
@Transactional
public class CountriesServices {
	@Autowired
	private  CountriesRepository countriesRepository;
    public static Integer NUM_COUNTRIE=4;
	
	// Retornar Todas as Provincia com sua repeitiva propriedade
	public List<Countries> list(){
		
	return (List<Countries>) countriesRepository.findAll(); }
	
	
	//retorn os dados organizado de forma ascidente e descidente 
	public Page<Countries> orderByData(Integer pageNum, String sortField,String sortDir) {
		
		Sort sort =Sort.by(sortField);
		
		sort = sortDir.equals("asc") ? sort.ascending():sort.descending();
		
		Pageable page = PageRequest.of(pageNum-1, NUM_COUNTRIE, sort);
				
		return countriesRepository.findAll(page);
	}
	
	// salva uma privincia com suas propriedade 
	public Countries saveCountries(Countries countrie) {
		
		return countriesRepository.save(countrie);
	}
	
	//pega uma provincia especifica
	public Countries getId(Integer id) {
		
		return countriesRepository.findById(id).get();
	}
		
	//deleta uma provincia com suas respeitivas propriedade 
    public void delete(Integer id) {
		
		countriesRepository.deleteById(id);
	}
	
}
