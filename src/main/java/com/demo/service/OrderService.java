package com.demo.service;

import java.util.List;

import com.demo.entities.Order;


public interface OrderService {
	public boolean save(Order o);
	
	public Order findById(int id);
	
	public List<Order> finđAll();
}
