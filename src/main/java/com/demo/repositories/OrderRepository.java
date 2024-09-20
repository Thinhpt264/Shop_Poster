package com.demo.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.demo.entities.Order;

@Repository
public interface OrderRepository extends CrudRepository<Order, Integer> {
	
	public Order findById(int id);
	
}
