package com.zwash.controller;

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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.model.checkout.Session;
import com.stripe.param.PaymentIntentConfirmParams;
import com.stripe.param.PaymentIntentCreateParams;
import com.stripe.param.checkout.SessionCreateParams;
import com.zwash.pojos.ApplePayPaymentMethod;
import com.zwash.pojos.ConcreteCarWashingProgram;
import com.zwash.pojos.ConfirmPaymentRequest;
import com.zwash.pojos.CreditCardPaymentMethod;
import com.zwash.pojos.GooglePayPaymentMethod;
import com.zwash.pojos.PaymentRequest;
import com.zwash.service.UserService;

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
				.setPaymentMethodOptions(SessionCreateParams.PaymentMethodOptions.builder().build()).build();
		Session session = Session.create(params);
		String sessionUrl = DOMAIN + "/checkout/" + session.getId(); // Replace "/checkout" with your desired URL path

		// Create the ResponseEntity with the redirect URL and HTTP status code
		return ResponseEntity.status(303).header("Location", sessionUrl).body("");
	}

	@PostMapping(value = "/create-payment-intent", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> createPaymentIntent(@RequestBody PaymentRequest request)
			throws StripeException, JsonMappingException, JsonProcessingException, Exception {

		Stripe.apiKey = "sk_test_51NInIUC7hkCZnQICPVg265tvEEClxVcWdBmavlo8LBBtnCjc4VVCtPaegEyry1YJ7pAUCoBuPfmJ8yoQ068uERae001BvwzOiW";
		String paymentMethodType ="";
		  try {
	            long amountInCents = request.getProgram().getPrice() * 100; // Convert euros to cents

	            if (request.getPaymentMethod() instanceof ApplePayPaymentMethod) {
	                 paymentMethodType = "apple_pay";
	            }
	            if (request.getPaymentMethod() instanceof CreditCardPaymentMethod) {
	                 paymentMethodType = "Cards";
	            }

	            if (request.getPaymentMethod() instanceof GooglePayPaymentMethod) {
	                 paymentMethodType = "google_pay";
	            }
	            PaymentIntentCreateParams params = PaymentIntentCreateParams.builder()
	                    .setAmount(amountInCents)
	                    .setCurrency("eur")
	                    .setAutomaticPaymentMethods(
	                    	      PaymentIntentCreateParams.AutomaticPaymentMethods.builder().setEnabled(true).build()
	                    	    )

	                    .build();

	            PaymentIntent paymentIntent = PaymentIntent.create(params);
//	            Payment payment = new Payment(paymentIntent.getId(), paymentIntent.getPaymentMethod());

	            return ResponseEntity.status(200).body(paymentIntent.getClientSecret());
	        } catch (StripeException e) {
	            e.printStackTrace();
	            return ResponseEntity.status(500).body(null);
	        }
	    }


	@PostMapping(value = "/confirm-payment", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> confirmPayment(@RequestBody ConfirmPaymentRequest request) {
		try {
			// Set your Stripe API key
			Stripe.apiKey = "sk_test_51NInIUC7hkCZnQICPVg265tvEEClxVcWdBmavlo8LBBtnCjc4VVCtPaegEyry1YJ7pAUCoBuPfmJ8yoQ068uERae001BvwzOiW";

			String paymentIntentId = request.getPaymentIntentId();
			String paymentMethodId = request.getPaymentMethodId();

			// Retrieve the PaymentIntent
			PaymentIntent paymentIntent = PaymentIntent.retrieve(paymentIntentId);

			// Build the parameters for confirming the PaymentIntent
			PaymentIntentConfirmParams confirmParams = PaymentIntentConfirmParams.builder()
					.setPaymentMethod(paymentMethodId).build();

			// Confirm the PaymentIntent
			PaymentIntent updatedPaymentIntent = paymentIntent.confirm(confirmParams);

			// Return the client secret of the updated PaymentIntent
			return ResponseEntity.status(HttpStatus.OK).body(updatedPaymentIntent.getClientSecret());
		} catch (StripeException e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error confirming payment");
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

			PaymentIntentCreateParams params = PaymentIntentCreateParams.builder().setAmount(program.getPrice())
					.setCurrency("eur").addPaymentMethodType("card").build();

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
