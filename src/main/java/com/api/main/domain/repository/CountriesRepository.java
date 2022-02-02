package com.api.main.domain.repository;


import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.api.main.domain.models.Countries;
@Repository
public interface CountriesRepository extends PagingAndSortingRepository<Countries, Integer>{

}
