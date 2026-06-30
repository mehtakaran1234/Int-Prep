// PayPal payment processor
package com.k2senterprise.solid_design_principal.Open_Closed_Principle;
class PayPalPaymentProcessor extends PaymentProcessor {
    @Override
    public void processPayment(double amount) {
        System.out.println("Processing PayPal payment of $" + amount);
    }
}