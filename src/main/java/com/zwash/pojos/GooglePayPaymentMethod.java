package com.zwash.pojos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
@DiscriminatorValue("google_pay")
@JsonIgnoreProperties(ignoreUnknown = true)
public class GooglePayPaymentMethod extends PaymentMethod {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public GooglePayPaymentMethod() {
        // Default constructor required for JSON deserialization
    }

    public GooglePayPaymentMethod(String paymentMethodId) {
        super(paymentMethodId);
    }
}
