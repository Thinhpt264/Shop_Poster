package com.demo.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.demo.entities.Artist;


@Repository
public interface ArtistRepository extends CrudRepository<Artist, Integer>  {
	
	@Query("from Artist where id = :id")
	public Artist findArtistById(@Param("id") int id);
}
