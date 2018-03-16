package com.example.artem.bmi;

/**
 * Created by Artem on 2018-03-09.
 */

public class BmiForKgM extends BMI {
    public BmiForKgM(double mass, double height) {
        super(mass, height);
    }

    @Override
    public double getMass() {
        return super.getMass();
    }

    @Override
    public double getHeight() {
        return super.getHeight();
    }

    @Override
    public double calculateBMI() {
        if(dataAreValid()){
            return getMass() / (getHeight()*getHeight());
        } else{
            throw new IllegalArgumentException("Invalid data");
        }
    }

    @Override
    boolean dataAreValid() {
        return getMass() >= 20 && getMass() <= 300 && getHeight() >= 1 && getHeight() <= 2.50;
    }
}
