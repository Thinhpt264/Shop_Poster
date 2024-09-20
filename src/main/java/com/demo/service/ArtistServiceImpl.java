package com.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.entities.Artist;
import com.demo.entities.Categories;
import com.demo.repositories.ArtistRepository;
import com.demo.repositories.CategoryRepository;

@Service
public class ArtistServiceImpl implements ArtistService {
	
	@Autowired
	private ArtistRepository artistRepository;
	
	@Override
	public Artist findById(int id) {
		return artistRepository.findArtistById(id);
	}
	
}
