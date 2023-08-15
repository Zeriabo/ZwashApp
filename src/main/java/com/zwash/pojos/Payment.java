package com.zwash.pojos;

public class Payment {
    private String paymentIntentId;
    private String paymentMethodId;

    public Payment(String paymentIntentId, String paymentMethodId) {
        this.paymentIntentId = paymentIntentId;
        this.paymentMethodId = paymentMethodId;
    }

    public String getPaymentIntentId() {
        return paymentIntentId;
    }

    public void setPaymentIntentId(String paymentIntentId) {
        this.paymentIntentId = paymentIntentId;
    }

    public String getPaymentMethodId() {
        return paymentMethodId;
    }

    public void setPaymentMethodId(String paymentMethodId) {
        this.paymentMethodId = paymentMethodId;
    }
}
