package com.demo.service;

import java.util.List;

import com.demo.entities.Frame;

public interface FrameService {
	public List<Frame> findAll();
	
	public Frame findById(int id);
}
