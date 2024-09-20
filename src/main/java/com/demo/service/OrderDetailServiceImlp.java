package com.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.entities.Order;
import com.demo.entities.OrderDetail;
import com.demo.repositories.OrderDetailRepository;

@Service
public class OrderDetailServiceImlp implements OrderDetailService {
	
	@Autowired
	private OrderDetailRepository orderDetailRepository;
	
	@Override
	public boolean save(OrderDetail o) {
		// TODO Auto-generated method stub
		if(orderDetailRepository.save(o) != null) {
			return true;
		} else {
			return false ;
		}
	
	}

	@Override
	public OrderDetail findById(int id) {
		// TODO Auto-generated method stub
		return orderDetailRepository.findById(id);
	}

}
