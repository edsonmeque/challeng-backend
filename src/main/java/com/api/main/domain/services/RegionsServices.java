package com.api.main.domain.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.main.domain.models.Regions;
import com.api.main.domain.repository.RegionsRepository;

@Service
@Transactional
public class RegionsServices {
	@Autowired
	private  RegionsRepository regionsRepository;
	
	
	
	public List<Regions> list(){
		
	return (List<Regions>) regionsRepository.findAll(); }
	
	
	public Regions saveRegion(Regions region) {
		
		return regionsRepository.save(region);
	}
	public Regions getId(Integer id) {
		
		return regionsRepository.findById(id).get();
	}
		
    public void delete(Integer id) {
		
		regionsRepository.deleteById(id);
	}
	
}
