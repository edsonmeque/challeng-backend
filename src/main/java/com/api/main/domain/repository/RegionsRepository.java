package com.api.main.domain.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.api.main.domain.models.Regions;

public interface RegionsRepository extends PagingAndSortingRepository<Regions, Integer>{

}
