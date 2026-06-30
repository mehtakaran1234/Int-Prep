package com.k2senterprise.solid_design_principal.Liskovs_Substitution_Principle;

public class Main {
    public static void main(String[] args) {
        Square s = new Square(5);
        s.setWidth(10);
        System.out.println("Area: " + s.area());
    }
}
