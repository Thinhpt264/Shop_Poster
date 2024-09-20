package com.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.demo.entities.PosterKeyword;

@Repository
public interface PosterKeywordRepository extends CrudRepository<PosterKeyword, Integer> {
	
	 @Query("from PosterKeyword pk where pk.keyword.id = :keyword_id")
	public List<PosterKeyword> findByKeyword(@Param("keyword_id") int keyword_id);
}
