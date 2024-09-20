package com.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.demo.entities.Size;

@Repository
public interface SizeRepository extends CrudRepository<Size, Integer> {
	
	@Query("from Size")
	public List<Size> findAll();
	
	@Query("from Size where id= :id")
	public Size findById(@Param("id") int id);
	
}
