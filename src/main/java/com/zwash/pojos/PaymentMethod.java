package com.zwash.pojos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "paymentMethodType")
@JsonSubTypes({
    @JsonSubTypes.Type(value = CreditCardPaymentMethod.class, name = "credit_card"),
    @JsonSubTypes.Type(value = ApplePayPaymentMethod.class, name = "apple_pay"),
    @JsonSubTypes.Type(value = GooglePayPaymentMethod.class, name = "google_pay")
})
public abstract class PaymentMethod {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonProperty("payment_method_id")
    private String paymentMethodId;

    // Default constructor required for JSON deserialization
    public PaymentMethod() {

    }

    public PaymentMethod(String paymentMethodId) {
        this.paymentMethodId = paymentMethodId;
    }

    // Getters and setters for id and paymentMethodId

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPaymentMethodId() {
        return paymentMethodId;
    }

    public void setPaymentMethodId(String paymentMethodId) {
        this.paymentMethodId = paymentMethodId;
    }

}
