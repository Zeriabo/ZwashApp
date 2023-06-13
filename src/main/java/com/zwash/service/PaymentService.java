package com.zwash.service;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;

public class PaymentService {
    static {
        Stripe.apiKey = "YOUR_STRIPE_SECRET_KEY";
    }

    public String createCheckoutSession() throws StripeException {
        SessionCreateParams.Builder builder = new SessionCreateParams.Builder()
                .addPaymentMethodType(SessionCreateParams.PaymentMethodType.CARD)
                .setMode(SessionCreateParams.Mode.PAYMENT)
                .setSuccessUrl("https://your-website.com/success")
                .setCancelUrl("https://your-website.com/cancel");

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