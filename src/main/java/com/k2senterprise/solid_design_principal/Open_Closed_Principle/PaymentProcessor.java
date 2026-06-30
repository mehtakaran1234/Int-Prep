package com.k2senterprise.solid_design_principal.Open_Closed_Principle;
// Base class for payment processing
abstract class PaymentProcessor {
    public abstract void processPayment(double amount); // Pure virtual function
}