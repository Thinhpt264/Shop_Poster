package com.demo.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.demo.entities.Keyword;

@Repository
public interface KeywordRepository extends CrudRepository<Keyword, Integer> {
	
	@Query("from Keyword where name = :name")
	public Keyword findByName(@Param("name")String name);
	
	
}
