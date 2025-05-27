package com.zwash.pojos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
@DiscriminatorValue("credit_card")
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreditCardPaymentMethod extends PaymentMethod {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public CreditCardPaymentMethod() {
        // Default constructor required for JSON deserialization
    }

    public CreditCardPaymentMethod(String paymentMethodId) {
        super(paymentMethodId);
    }
}
