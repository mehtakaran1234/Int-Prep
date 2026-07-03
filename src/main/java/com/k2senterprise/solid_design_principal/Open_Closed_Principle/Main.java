// Function to process payment
/*public static void processPayment(PaymentProcessor processor, double amount) {
    processor.processPayment(amount);
}*/
package com.k2senterprise.solid_design_principal.Open_Closed_Principle;

public class Main {
    public static void main(String[] args) {
        CreditCardPaymentProcessor creditCardProcessor = new CreditCardPaymentProcessor();
        PayPalPaymentProcessor payPalProcessor = new PayPalPaymentProcessor();

        processPayment(creditCardProcessor, 100.00); // Processing credit card payment
        processPayment(payPalProcessor, 150.00);     // Processing PayPal payment
    }

    // Function to process payment
    public static void processPayment(PaymentProcessor processor, double amount) {
        processor.processPayment(amount);
    }
}