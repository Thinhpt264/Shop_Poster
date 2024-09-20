package com.demo.service;

import java.util.List;
import java.util.Set;

import com.demo.entities.Poster;

public interface PosterService {
	public List<Poster> findAll();
	
	public Poster findById(int id);
	
	public List<Poster> findPosterBySize(int sizeid);
	
	public List<Poster> findPosterByFrame(int frameid);
	

}
