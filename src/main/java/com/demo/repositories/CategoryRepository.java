package com.demo.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.demo.entities.Categories;

@Repository
public interface CategoryRepository extends CrudRepository<Categories, Integer>  {
	
	@Query("from Categories where id = :id")
	public Categories findCategoryById(@Param("id") int id);
}
