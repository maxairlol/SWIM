package com.example.artem.bmi;

/**
 * Created by Artem on 2018-03-09.
 */

public abstract class BMI {
    private double mass;
    private double height;

    public BMI(double mass, double height) {
        this.mass = mass;
        this.height = height;
    }

    public double getMass() {
        return mass;
    }

    public double getHeight() {
        return height;
    }

    abstract public double calculateBMI();
    abstract boolean dataAreValid();
}
