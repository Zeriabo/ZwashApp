package com.zwash.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import com.zwash.pojos.CarWashingProgram;
import com.zwash.pojos.Station;
import com.zwash.service.PaymentService;
import com.zwash.service.StationService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/v1/payment")
public class PaymentController {
	

	@ApiOperation("creates a checkout session")
	@PostMapping("/create-checkout-session")
	public ResponseEntity<?>createCheckoutSession() throws StripeException {
		
		String DOMAIN = "http://localhost:7001";
        SessionCreateParams params =
          SessionCreateParams.builder()
            .setMode(SessionCreateParams.Mode.PAYMENT)
            .setSuccessUrl(DOMAIN + "?success=true")
            .setCancelUrl(DOMAIN + "?canceled=true")
            .addLineItem(
              SessionCreateParams.LineItem.builder()
                .setQuantity(1L)
                // Provide the exact Price ID (for example, pr_1234) of the product you want to sell
                .setPrice("{{PRICE_ID}}")
                .build())
            .build();
      Session session = Session.create(params);
      String sessionUrl = DOMAIN + "/checkout/" + session.getId();  // Replace "/checkout" with your desired URL path

      // Create the ResponseEntity with the redirect URL and HTTP status code
      ResponseEntity<String> responseEntity = ResponseEntity
          .status(303)
          .header("Location",sessionUrl)
          .body("");

      return responseEntity;
 
   
	}
	
	   @ApiOperation("checkout session")
	   @GetMapping("/checkout/{sessionId}")
	    public ResponseEntity<String> handleCheckout(@PathVariable String sessionId) {
	        // Here you can perform any necessary logic for handling the checkout
	        // process, such as retrieving session details from the database, validating
	        // the session, and initiating the payment flow.

	        // For this example, we'll simply return a success response.
	        return ResponseEntity.ok("Checkout successful! Session ID: " + sessionId);
	    }
	
}
