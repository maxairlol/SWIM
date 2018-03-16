package com.example.artem.bmi;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class BmiForKgMTest {
    @Test
    public void for_valid_data_bmi_isCorrect(){
        BMI bmiCounter = new BmiForKgM(60,1.70);
        double bmi = bmiCounter.calculateBMI();
        assertEquals(20.761,bmi,0.001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void for_zero_bmi_throw_exception(){
        BMI bmiCounter = new BmiForKgM(0,0);
        double bmi = bmiCounter.calculateBMI();
    }

    @Test(expected = IllegalArgumentException.class)
    public void for_wrong_data_throw_exception(){
        BMI bmiCounter = new BmiForKgM(70,38);
        double bmi = bmiCounter.calculateBMI();
    }

}