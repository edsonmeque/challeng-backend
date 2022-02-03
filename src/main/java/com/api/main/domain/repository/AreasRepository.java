package com.api.main.domain.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.api.main.domain.models.Areas;

public interface AreasRepository extends PagingAndSortingRepository<Areas,Integer> {

}
