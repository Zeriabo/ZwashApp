package com.zwash.service;

import org.springframework.stereotype.Service;

import com.stripe.model.Charge;
import com.stripe.model.Customer;

@Service
public interface StripeClientService {

	public Customer createCustomer(String token, String email) throws Exception;
	 public Charge chargeNewCard(String token, double amount) throws Exception;
	 public Charge chargeCustomerCard(String customerId, int amount) throws Exception;

}
