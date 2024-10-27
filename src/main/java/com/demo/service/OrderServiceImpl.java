package com.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.entities.Order;

import com.demo.repositories.OrderRepository;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepository orderRepository;
	
	@Override
	public boolean save(Order o) {
		// TODO Auto-generated method stub
		if(orderRepository.save(o) != null) {
			return true;
		} else {
			return false ;
		}
	}

	@Override
	public Order findById(int id) {
		// TODO Auto-generated method stub
		return orderRepository.findById(id);
	}
	
	@Override
	public List<Order> finđAll() {
		// TODO Auto-generated method stub
		return orderRepository.findAll();
	}
	
}
