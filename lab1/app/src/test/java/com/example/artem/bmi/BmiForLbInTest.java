package com.example.artem.bmi;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class BmiForLbInTest {
    @Test
    public void for_valid_data_bmi_isCorrect(){
        BMI bmiCounter = new BmiForLbsIn(37.25,41.5);
        double bmi = bmiCounter.calculateBMI();
        assertEquals(15.204,bmi,0.001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void for_zero_bmi_throw_exception(){
        BMI bmiCounter = new BmiForLbsIn(0,0);
        double bmi = bmiCounter.calculateBMI();
    }

    @Test(expected = IllegalArgumentException.class)
    public void for_wrong_data_throw_exception(){
        BMI bmiCounter = new BmiForKgM(19,1.50);
        double bmi = bmiCounter.calculateBMI();
    }
}