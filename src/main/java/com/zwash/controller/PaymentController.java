package com.zwash.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import com.zwash.exceptions.UserIsNotActiveException;
import com.zwash.pojos.BasicCarWashingProgram;
import com.zwash.pojos.CarWashingProgram;
import com.zwash.pojos.ConcreteCarWashingProgram;
import com.zwash.pojos.LoggedUser;
import com.zwash.pojos.SignInfo;
import com.zwash.pojos.Wash;
import com.zwash.service.UserService;

import io.jsonwebtoken.io.IOException;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.stripe.param.PaymentIntentCreateParams;
import com.stripe.model.PaymentIntent;


@RestController
@RequestMapping("/v1/payment")
public class PaymentController {

	
	@Autowired
	private UserService userService;
	
	Logger logger = LoggerFactory.getLogger(PaymentController.class);
	
	@PostMapping("/create-checkout-session")
	public ResponseEntity<String> createCheckoutSession(@RequestParam("stripe_token") String stripeToken)
			throws StripeException {
		Stripe.apiKey = "sk_test_51NInIUC7hkCZnQICPVg265tvEEClxVcWdBmavlo8LBBtnCjc4VVCtPaegEyry1YJ7pAUCoBuPfmJ8yoQ068uERae001BvwzOiW";

		String DOMAIN = "http://localhost:7001";
		SessionCreateParams params = SessionCreateParams.builder().setMode(SessionCreateParams.Mode.PAYMENT)
				.setSuccessUrl(DOMAIN + "?success=true").setCancelUrl(DOMAIN + "?canceled=true")
				.addLineItem(SessionCreateParams.LineItem.builder().setQuantity(1L)
				.setPrice("price_1NIp6hC7hkCZnQIC0jzV82ZK").build())
				.setPaymentMethodOptions(SessionCreateParams.PaymentMethodOptions.builder()
				.build())
				.build();
		Session session = Session.create(params);
		String sessionUrl = DOMAIN + "/checkout/" + session.getId(); // Replace "/checkout" with your desired URL path

		// Create the ResponseEntity with the redirect URL and HTTP status code
		return ResponseEntity.status(303).header("Location", sessionUrl).body("");
	}
	
	
	
	@PostMapping(value ="/create-payment-intent",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)  
	public ResponseEntity<String> createPaymentIntent(@RequestBody ConcreteCarWashingProgram item)
			throws StripeException, JsonMappingException, JsonProcessingException,Exception{
		
		
		Stripe.apiKey = "sk_test_51NInIUC7hkCZnQICPVg265tvEEClxVcWdBmavlo8LBBtnCjc4VVCtPaegEyry1YJ7pAUCoBuPfmJ8yoQ068uERae001BvwzOiW";
		 try {
		    
      long amountInCents = (long) (item.getPrice() * 100); // Convert euros to cents

		PaymentIntentCreateParams params =
				  PaymentIntentCreateParams.builder()
				    .setAmount(amountInCents)
				    .setCurrency("eur")
				    .addPaymentMethodType("card")
				    .build();

				PaymentIntent paymentIntent = PaymentIntent.create(params);
				  // Manually serialize the PaymentIntent object to JSON
		        String clientSecret = paymentIntent.getClientSecret();

		// Create the ResponseEntity with the redirect URL and HTTP status code
		return ResponseEntity.status(200).body(clientSecret);
		
		 }catch (IOException e) {
		        e.printStackTrace();
		        return ResponseEntity.status(400).body("Invalid request body");
		    }
	}
	
	@PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> createPaymentIntentt(@RequestBody ConcreteCarWashingProgram program)
	        throws StripeException {	
        Stripe.apiKey = "sk_test_51NInIUC7hkCZnQICPVg265tvEEClxVcWdBmavlo8LBBtnCjc4VVCtPaegEyry1YJ7pAUCoBuPfmJ8yoQ068uERae001BvwzOiW";
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String programJson = objectMapper.writeValueAsString(program);

            System.out.println(programJson);

            PaymentIntentCreateParams params = PaymentIntentCreateParams.builder()
                    .setAmount((long) program.getPrice())
                    .setCurrency("eur")
                    .addPaymentMethodType("card")
                    .build();

            PaymentIntent paymentIntent = PaymentIntent.create(params);
            String paymentIntentJson = paymentIntent.getClientSecret();

            return ResponseEntity.status(200).body(paymentIntentJson);
        } catch (StripeException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error creating payment intent");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(400).body("Invalid request body");
        }
    }
	@GetMapping("/checkout/{sessionId}")
	public ResponseEntity<String> handleCheckout(@PathVariable String sessionId) {
		// Here you can perform any necessary logic for handling the checkout
		// process, such as retrieving session details from the database, validating
		// the session, and initiating the payment flow.

		// For this example, we'll simply return a success response.
		return ResponseEntity.ok("Checkout successful! Session ID: " + sessionId);
	}
}
