package com.demo.service;

import com.demo.entities.Order;


public interface OrderService {
	public boolean save(Order o);
	
	public Order findById(int id);
}
