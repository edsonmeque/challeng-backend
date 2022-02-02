package com.api.main.domain.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.main.domain.models.Regions;
import com.api.main.domain.models.SubRegions;
import com.api.main.domain.repository.RegionsRepository;
import com.api.main.domain.repository.SubRegionsRepository;

@Service
@Transactional
public class SubRegionsServices {
	@Autowired
	private  SubRegionsRepository subRegionsRepository;
	
	
	
	public List<SubRegions> list(){
		
	return (List<SubRegions>) subRegionsRepository.findAll(); }
	
	
	public SubRegions saveSubRegions(SubRegions subRegion) {
		
		return subRegionsRepository.save(subRegion);
	}
	
	public SubRegions getId(Integer id) {
		
		return subRegionsRepository.findById(id).get();
	}
		
    public void delete(Integer id) {
		
    	subRegionsRepository.deleteById(id);
	}
	
	

}
