package com.zwash.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stripe.model.Charge;
import com.zwash.service.StripeClientService;
@RestController
@CrossOrigin("*")
@RequestMapping("/v1/payment")
public class PaymentGatewayController {

	@Autowired
    private StripeClientService stripeClient;

    PaymentGatewayController() {

    }

    @PostMapping(value = "/charge", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String>  chargeCard(@RequestHeader(value="token") String token, @RequestHeader(value="amount") Double amount) throws Exception {
       Charge succeed = this.stripeClient.chargeNewCard(token, amount);

       String status = succeed.getStatus();
       if ("succeeded".equals(status)) {
           // Payment succeeded
           // Handle successful payment logic here
    	   return ResponseEntity.status(303)
                   .header("Location", status)
                   .body("");
       } else {
           // Payment failed or has a different status
           // Handle failed payment logic here
    	   return ResponseEntity.status(500)
                   .header("Location", status)
                   .body("");
       }
    }


}