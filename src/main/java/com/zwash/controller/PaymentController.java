package com.zwash.controller;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/payment")
public class PaymentController {

    @PostMapping("/create-checkout-session")
    public ResponseEntity<String> createCheckoutSession() throws StripeException {
        Stripe.apiKey = "sk_test_51NInIUC7hkCZnQICPVg265tvEEClxVcWdBmavlo8LBBtnCjc4VVCtPaegEyry1YJ7pAUCoBuPfmJ8yoQ068uERae001BvwzOiW";

        String DOMAIN = "http://localhost:7001";
        SessionCreateParams params = SessionCreateParams.builder()
                .setMode(SessionCreateParams.Mode.PAYMENT)
                .setSuccessUrl(DOMAIN + "?success=true")
                .setCancelUrl(DOMAIN + "?canceled=true")
                .addLineItem(
                        SessionCreateParams.LineItem.builder()
                                .setQuantity(1L)
                                .setPrice("price_1NIp6hC7hkCZnQIC0jzV82ZK")
                                .build()
                )
                .build();
        Session session = Session.create(params);
        String sessionUrl = DOMAIN + "/checkout/" + session.getId(); // Replace "/checkout" with your desired URL path

        // Create the ResponseEntity with the redirect URL and HTTP status code
        return ResponseEntity.status(303)
                .header("Location", sessionUrl)
                .body("");
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
