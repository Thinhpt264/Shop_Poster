package com.demo.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.demo.entities.Payment;

@Repository
public interface PaymentRepository extends CrudRepository<Payment, Integer> {

}
