package com.demo.service;

import com.demo.entities.OrderDetail;

public interface OrderDetailService {
	public boolean save(OrderDetail o) ;
	
	public OrderDetail findById(int id);
}
