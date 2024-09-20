package com.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.demo.entities.Frame;

@Repository
public interface FrameRepository extends CrudRepository<Frame, Integer> {
	
	@Query("from Frame")
	public List<Frame> findAll();
	
	public Frame findById(int id);
}
