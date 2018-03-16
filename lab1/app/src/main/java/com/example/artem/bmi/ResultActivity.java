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
     TextView tvResult;

    @SuppressLint({"RestrictedApi", "DefaultLocale"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);

        tvResult = findViewById(R.id.tvResult);
        double result = getResult();
        tvResult.setText(String.format("%.2f",result));
        setBGColor(result);
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
        return bundle != null ? bundle.getDouble("calculatingResult") : 0;
    }

    public void setBGColor(double value){
        ConstraintLayout cl = findViewById(R.id.ConstraintLayout);
        int color;
            if (value < 19) color = getResources().getColor(R.color.colorLightGreen);
            else if (value >= 19 && value < 25) color = getResources().getColor(R.color.colorGreen);
            else if (value >= 25 && value < 30) color = getResources().getColor(R.color.colorYellow);
            else if (value >= 30 && value < 40) color = getResources().getColor(R.color.colorOrange);
            else color = getResources().getColor(R.color.colorRed);
            cl.setBackgroundColor(color);
        }
    }
