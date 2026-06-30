package com.k2senterprise.solid_design_principal.Liskovs_Substitution_Principle;

import java.util.*;

// Base class for shapes
class Rectangle {
    protected double width;
    protected double height;

    public Rectangle(double w, double h) {
        width = w;
        height = h;
    }

    public double area() {
        return width * height;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public void setWidth(double w) {
        width = w;
    }

    public void setHeight(double h) {
        height = h;
    }
}