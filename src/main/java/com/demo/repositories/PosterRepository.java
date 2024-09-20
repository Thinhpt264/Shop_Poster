package com.demo.repositories;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.demo.entities.Poster;

@Repository
public interface PosterRepository extends CrudRepository<Poster, Integer> {
	
	@Query("from Poster")
	public List<Poster> findAll();
	
	@Query("from Poster where id= :id")
	public Poster findById(@Param("id") int id);
	
	@Query("Select p from Poster p JOIN p.sizes s where s.id = :size_id")
	public List<Poster> findPosterBySizeId(@Param("size_id") int size_id);
	
	@Query("Select p from Poster p JOIN p.frames f where f.id = :frame_id")
	public List<Poster> findPosterByFrameId(@Param("frame_id") int frame_id);
	
	
}
