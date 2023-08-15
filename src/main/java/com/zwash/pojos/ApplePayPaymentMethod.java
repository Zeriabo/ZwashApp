package com.zwash.pojos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
@DiscriminatorValue("apple_pay")
@JsonIgnoreProperties(ignoreUnknown = true)
public class ApplePayPaymentMethod extends PaymentMethod {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public ApplePayPaymentMethod() {
        // Default constructor required for JSON deserialization
    }

    public ApplePayPaymentMethod(String paymentMethodId) {
        super(paymentMethodId);
    }
}
