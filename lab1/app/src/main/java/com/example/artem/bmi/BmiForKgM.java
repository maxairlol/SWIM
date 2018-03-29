package com.example.artem.bmi;

/**
 * Created by Artem on 2018-03-09.
 */

public class BmiForKgM extends BMI {
    private static final int MIN_MASS = 20;
    private static final int MAX_MASS = 300;
    private static final int MIN_HEIGHT = 1;
    private static final double MAX_HEIGHT = 2.50;
    private static final String INVALID_DATA = "Invalid data!";

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
            throw new IllegalArgumentException(INVALID_DATA);
        }
    }

    @Override
    boolean dataAreValid() {
        return getMass() >= MIN_MASS && getMass() <= MAX_MASS && getHeight() >= MIN_HEIGHT && getHeight() <= MAX_HEIGHT;
    }
}
