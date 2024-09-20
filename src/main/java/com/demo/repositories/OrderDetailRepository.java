package com.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.demo.entities.OrderDetail;

@Repository
public interface OrderDetailRepository extends CrudRepository<OrderDetail, Integer> {
	
	@Query("from OrderDetail where id = :id")
	public OrderDetail findById(@Param("id") int id);
	
	@Query("SELECT od FROM OrderDetail od WHERE od.order.id = :order_id")
	public List<OrderDetail> findOrderDetailByOrderId(@Param("order_id") int order_id);
}
