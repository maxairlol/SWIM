package com.example.artem.bmi;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public static final String USER_DATA = "userData" ;
    public static final String MASS_VALUE = "massValueKey";
    public static final String HEIGHT_VALUE = "heightValueKey";
    public static final String MASS_TV = "massTextKey";
    public static final String HEIGHT_TV = "heightTextKey";
    public static final String IS_CHECKED = "isCheckedKey";
    public static final String IS_DATA_SAVED = "isDataSaved";
    public static final String CALCULATING_RESULT = "calculatingResult";
    public static final String INVALID_DATA = "Invalid data!";
    public static final String DATA_IS_SAVED = "Data has been saved";
    public static final String ENTER_DATA = "Please, enter data!";

    EditText editMass,editHeight;
    TextView tvMass,tvHeight;
    Switch switchSystem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setViews();
        setListeners();

        if(isDataSaved()) retrieveData();
        refreshData();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.menu_about:
                openAboutActivity();
                return true;
            case R.id.menu_save:
                saveData();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void setViews(){
        switchSystem = findViewById(R.id.switchSystem);
        editMass = findViewById(R.id.editMass);
        editHeight = findViewById(R.id.editHeight);
        tvMass = findViewById(R.id.tvMass);
        tvHeight = findViewById(R.id.tvHeight);
    }

     public void setListeners(){
         switchSystem.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
             @Override
             public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                 if(!isChecked){
                     tvMass.setText(R.string.massKg);
                     tvHeight.setText(R.string.heightM);
                 } else{
                     tvMass.setText(R.string.massLb);
                     tvHeight.setText(R.string.heightIn);
                 }
             }
         });
     }

    //Save the users data info
    public void saveData(){
        SharedPreferences sharedPref = getSharedPreferences(USER_DATA, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(MASS_VALUE, editMass.getText().toString());
        editor.putString(HEIGHT_VALUE, editHeight.getText().toString());
        editor.putString(HEIGHT_TV,tvHeight.getText().toString());
        editor.putString(MASS_TV,tvMass.getText().toString());
        editor.putBoolean(IS_CHECKED,switchSystem.isChecked());
        editor.putBoolean(IS_DATA_SAVED,true);
        editor.apply();
        showToast(DATA_IS_SAVED);
    }

    //Retrieve the users data info
    public void retrieveData() {
        SharedPreferences sharedPref = getSharedPreferences(USER_DATA, Context.MODE_PRIVATE);
        switchSystem.setChecked(sharedPref.getBoolean(IS_CHECKED,true));
        editMass.setText(sharedPref.getString(MASS_VALUE, ""));
        editHeight.setText(sharedPref.getString(HEIGHT_VALUE, ""));
        tvMass.setText(sharedPref.getString(MASS_TV,""));
        tvHeight.setText(sharedPref.getString(HEIGHT_TV,""));
    }

    public void refreshData(){
        SharedPreferences sharedPref = getSharedPreferences(USER_DATA, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean(IS_DATA_SAVED,false);
        editor.apply();
    }

    public boolean isDataSaved(){
        boolean isSaved;
        SharedPreferences sharedPref = getSharedPreferences(USER_DATA, Context.MODE_PRIVATE);
        isSaved = sharedPref.getBoolean(IS_DATA_SAVED,false);
        return isSaved;
    }

    public void openAboutActivity(){
        final Intent intent = new Intent(this,AboutActivity.class);
        startActivity(intent);
    }

    public void openResultActivity(Bundle bundle){
        final Intent intent = new Intent(this,ResultActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void showToast(String msg){
        Toast toast = Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.BOTTOM,0,0);
        toast.show();
    }

    public void calculateBMI(View v){
        String getMass = editMass.getText().toString();
        String getHeight = editHeight.getText().toString();
        if(getMass.isEmpty() || getHeight.isEmpty()) {
            showToast(ENTER_DATA);
        }
        else if (getMass.equals(".") || getHeight.equals(".")){
            showToast(INVALID_DATA);
        } else{
            double mass = Double.parseDouble(getMass);
            double height = Double.parseDouble(getHeight);
            double result;
            BMI bmi;
            if(switchSystem.isChecked()){
                bmi = new BmiForLbsIn(mass,height);
            } else bmi = new BmiForKgM(mass,height);
            try {
                result = bmi.calculateBMI();
                Bundle bundle = new Bundle();
                bundle.putDouble(CALCULATING_RESULT,result);
                openResultActivity(bundle);
            }catch (IllegalArgumentException e){
                showToast(e.getMessage());
            }
        }
    }
}
