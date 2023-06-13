package com.zwash.service;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;

public class PaymentService {
    static {
    	Stripe.apiKey = "sk_test_rfaBmNUu1lWB7VZ0MMSIsYjH";
    	
    }

    public String createCheckoutSession() throws StripeException {
    	 String DOMAIN = "http://localhost:7001";
        SessionCreateParams.Builder builder = new SessionCreateParams.Builder()
                .addPaymentMethodType(SessionCreateParams.PaymentMethodType.CARD)
                .setMode(SessionCreateParams.Mode.PAYMENT)
                .setSuccessUrl(DOMAIN+"/success")
                .setCancelUrl(DOMAIN+"/cancel");

        SessionCreateParams.LineItem item = new SessionCreateParams.LineItem.Builder()
                .setPriceData(new SessionCreateParams.LineItem.PriceData.Builder()
                        .setCurrency("usd")
                        .setProductData(new SessionCreateParams.LineItem.PriceData.ProductData.Builder()
                                .setName("Sample Product")
                                .build())
                        .setUnitAmount(1000L) // Amount in cents
                        .build())
                .setQuantity(1L)
                .build();

        builder.addLineItem(item);

        SessionCreateParams createParams = builder.build();

        Session session = Session.create(createParams);

        return session.getId();
    }
}