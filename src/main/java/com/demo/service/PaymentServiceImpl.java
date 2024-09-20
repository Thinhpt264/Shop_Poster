package com.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.entities.Payment;
import com.demo.repositories.PaymentRepository;

@Service
public class PaymentServiceImpl implements PaymentService {
	@Autowired
	private PaymentRepository paymentRepository;
	
	@Override
	public boolean save(Payment payment) {
		// TODO Auto-generated method stub
		if(paymentRepository.save(payment)  != null) {
			return true;
		}else {
			return false;
		}
		
	}

}
