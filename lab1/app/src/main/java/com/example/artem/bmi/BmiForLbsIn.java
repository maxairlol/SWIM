package com.example.artem.bmi;

/**
 * Created by Artem on 2018-03-09.
 */

public class BmiForLbsIn extends BMI {
    private static final double MIN_MASS = 44.09;
    private static final double MAX_MASS = 661.38;
    private static final double MIN_HEIGHT = 39.37;
    private static final double MAX_HEIGHT = 98.42;
    private static final String INVALID_DATA = "Invalid data!";

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
            throw new IllegalArgumentException(INVALID_DATA);
        }
    }

    @Override
    boolean dataAreValid() {
        return getMass() >= MIN_MASS && getMass() <= MAX_MASS && getHeight() >= MIN_HEIGHT && getHeight() <= MAX_HEIGHT;
    }
}
