package com.zwash.pojos;

public class PaymentRequest {
    private ConcreteCarWashingProgram programType;
    private PaymentMethod paymentMethod;

    // Default constructor required for JSON deserialization
    public PaymentRequest() {

    }

    public PaymentRequest(ConcreteCarWashingProgram programType, PaymentMethod paymentMethod) {
        this.programType = programType;
        this.paymentMethod = paymentMethod;
    }

    // Getters and setters for program and paymentMethod

    public ConcreteCarWashingProgram getProgram() {
        return programType;
    }

    public void setProgram(ConcreteCarWashingProgram programType) {
        this.programType = programType;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
}
