package com.example.artem.bmi;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {
    public static final int NORMAL_WEIGHT_MIN = 19;
    public static final int NORMAL_WEIGHT_MAX = 25;
    public static final int OVER_WEIGHT = 30;
    public static final int OBESE_WEIGHT = 40;
    public static final String CALCULATING_RESULT = "calculatingResult";

     TextView tvResult;

    @SuppressLint({"RestrictedApi", "DefaultLocale"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);

        setResult();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                final Intent intent = new Intent(this,MainActivity.class);
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void goBack(View v){
        super.onBackPressed();
    }

    public double getResult(){
        Bundle bundle = getIntent().getExtras();
        return bundle != null ? bundle.getDouble(CALCULATING_RESULT) : 0;
    }

    public void setResult(){
        tvResult = findViewById(R.id.tvResult);
        double result = getResult();
        tvResult.setText(String.format("%.2f",result));
        setBGColor(result);
    }

    public void setBGColor(double value){
        ConstraintLayout cl = findViewById(R.id.ConstraintLayout);
        int color;
        if (value < NORMAL_WEIGHT_MIN)
            color = getResources().getColor(R.color.colorLightGreen);
        else if (value >= NORMAL_WEIGHT_MIN && value < NORMAL_WEIGHT_MAX)
            color = getResources().getColor(R.color.colorGreen);
        else if (value >= NORMAL_WEIGHT_MAX && value < OVER_WEIGHT)
            color = getResources().getColor(R.color.colorYellow);
        else if (value >= OVER_WEIGHT && value < OBESE_WEIGHT)
            color = getResources().getColor(R.color.colorOrange);
        else color = getResources().getColor(R.color.colorRed);
        cl.setBackgroundColor(color);
        }

    }
