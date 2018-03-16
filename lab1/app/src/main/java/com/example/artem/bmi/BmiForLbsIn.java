package com.example.artem.bmi;

/**
 * Created by Artem on 2018-03-09.
 */

public class BmiForLbsIn extends BMI {
    public BmiForLbsIn(double mass, double height) {
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
            return ((getMass() / (getHeight()*getHeight())) * 703);
        } else{
            throw new IllegalArgumentException("Invalid data");
        }
    }

    @Override
    boolean dataAreValid() {
        return getMass() >= 44.09 && getMass() <= 661.38 && getHeight() >= 39.37 && getHeight() <= 98.42;
    }
}
