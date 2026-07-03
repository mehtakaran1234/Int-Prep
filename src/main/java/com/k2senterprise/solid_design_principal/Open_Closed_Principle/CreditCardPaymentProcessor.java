package com.k2senterprise.solid_design_principal.Open_Closed_Principle;
// Credit card payment processor
class CreditCardPaymentProcessor extends PaymentProcessor {
    @Override
    public void processPayment(double amount) {
        System.out.println("Processing credit card payment of $" + amount);
    }
}