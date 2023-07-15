package com.zwash.pojos;

public class PaymentRequest {
    private ConcreteCarWashingProgram program;
    private PaymentMethod paymentMethod;

    // Default constructor required for JSON deserialization
    public PaymentRequest() {
       
    }

    public PaymentRequest(ConcreteCarWashingProgram program, PaymentMethod paymentMethod) {
        this.program = program;
        this.paymentMethod = paymentMethod;
    }

    // Getters and setters for program and paymentMethod

    public ConcreteCarWashingProgram getProgram() {
        return program;
    }

    public void setProgram(ConcreteCarWashingProgram program) {
        this.program = program;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
}
