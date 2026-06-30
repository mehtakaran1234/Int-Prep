package com.k2senterprise.solid_design_principal.Liskovs_Substitution_Principle;

// Derived class for squares
class Square extends Rectangle {
    public Square(double size) {
        super(size, size);
    }

    @Override
    public void setWidth(double w) {
        width = height = w;
    }

    @Override
    public void setHeight(double h) {
        width = height = h;
    }
}