package com.demo.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.entities.Poster;
import com.demo.repositories.PosterRepository;

@Service
public class PosterServiceImpl implements PosterService {

	@Autowired
	private PosterRepository posterRepository;
	
	@Override
	public List<Poster> findAll() {
		// TODO Auto-generated method stub
		return posterRepository.findAll();
	}

	@Override
	public Poster findById(int id) {
		// TODO Auto-generated method stub
		return posterRepository.findById(id);
	}

	@Override
	public List<Poster> findPosterBySize(int sizeid) {
		// TODO Auto-generated method stub
		return posterRepository.findPosterBySizeId(sizeid);
	}

	@Override
	public List<Poster> findPosterByFrame(int frameid) {
		// TODO Auto-generated method stub
		return posterRepository.findPosterByFrameId(frameid);
	}

	
	
}	
