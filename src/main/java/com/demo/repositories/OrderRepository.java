package com.demo.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.demo.entities.Order;

@Repository
public interface OrderRepository extends CrudRepository<Order, Integer> {
	
	public Order findById(int id);
	
	public List<Order> findAll();
	
}
