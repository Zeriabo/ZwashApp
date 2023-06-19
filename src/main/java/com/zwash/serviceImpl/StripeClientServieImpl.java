package com.zwash.serviceImpl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stripe.Stripe;
import com.stripe.model.Charge;
import com.stripe.model.Customer;
import com.stripe.param.ChargeCreateParams;
import com.zwash.service.StripeClientService;

@Service
public class StripeClientServieImpl implements StripeClientService {

	@Autowired
	StripeClientServieImpl() {
        Stripe.apiKey = "sk_test_51NInIUC7hkCZnQICPVg265tvEEClxVcWdBmavlo8LBBtnCjc4VVCtPaegEyry1YJ7pAUCoBuPfmJ8yoQ068uERae001BvwzOiW";
    }
	
	@Override
	   public Customer createCustomer(String token, String email) throws Exception {
        Map<String, Object> customerParams = new HashMap<String, Object>();
        customerParams.put("email", email);
        customerParams.put("source", token);
        return Customer.create(customerParams);
    }
    private Customer getCustomer(String id) throws Exception {
        return Customer.retrieve(id);
    }
	@Override
	  public Charge chargeNewCard(String token, double amount) throws Exception {
//        Map<String, Object> chargeParams = new HashMap<String, Object>();
//        chargeParams.put("amount", (int)(amount));
//        chargeParams.put("currency", "EUR");
//        chargeParams.put("source", token);
        // Create a charge
        ChargeCreateParams chargeParams =
            ChargeCreateParams.builder()
                .setAmount((long) 1000) // Amount in cents
                .setCurrency("usd")
                .setSource("tok_visa") // Token generated from client-side
                .setDescription("Payment description")
                .build();

        Charge charge = Charge.create(chargeParams);
      
        return charge;
    }

	@Override
    public Charge chargeCustomerCard(String customerId, int amount) throws Exception {
        String sourceCard = getCustomer(customerId).getDefaultSource();
        Map<String, Object> chargeParams = new HashMap<String, Object>();
        chargeParams.put("amount", amount);
        chargeParams.put("currency", "USD");
        chargeParams.put("customer", customerId);
        chargeParams.put("source", sourceCard);
        Charge charge = Charge.create(chargeParams);
        return charge;
    
}
	

}
