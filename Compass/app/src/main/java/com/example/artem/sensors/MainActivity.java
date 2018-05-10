package com.example.artem.sensors;


import android.content.DialogInterface;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    TextView textView;
    ImageView imageView;
    int mAzimuth;
    private SensorManager sensorManager;
    private Sensor mRotationV, mAccelerometer, mMagnetometer;
    boolean sensor1 = false;
    boolean sensor2 = false;
    float[] orientation = new float[3];
    float[] rMatrix = new float[9];
    private float[] mGravity = new float[3];
    private float[] mGeomagnetic = new float[3];
    private boolean mGravitySet = false;
    private boolean mGeomagneticSet = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        imageView = (ImageView) findViewById(R.id.compass);
        textView = (TextView) findViewById(R.id.azimuth);

        access_to_sensors();
    }

    public void access_to_sensors(){
        if (sensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR) == null) {
            if ((sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) == null) || (sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD) == null)) {
                noSensorsAlert();
            }
            else {
                mAccelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
                mMagnetometer = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
                sensor1 = sensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_UI);
                sensor2 = sensorManager.registerListener(this, mMagnetometer, SensorManager.SENSOR_DELAY_UI);
            }
        }
        else{
            mRotationV = sensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR);
            sensor1 = sensorManager.registerListener(this, mRotationV, SensorManager.SENSOR_DELAY_UI);
        }
    }

    public void noSensorsAlert(){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setMessage("Your device doesn't support the Compass!")
                .setCancelable(false)
                .setNegativeButton("Close",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        finish();
                    }
                });
        alertDialog.show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(sensor1 && sensor2){
            sensorManager.unregisterListener(this,mAccelerometer);
            sensorManager.unregisterListener(this,mMagnetometer);
        }
        else{
            if(sensor1)
                sensorManager.unregisterListener(this,mRotationV);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        access_to_sensors();
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if (sensorEvent.sensor.getType() == Sensor.TYPE_ROTATION_VECTOR) {
            SensorManager.getRotationMatrixFromVector(rMatrix, sensorEvent.values);
            mAzimuth = (int) (Math.toDegrees(SensorManager.getOrientation(rMatrix, orientation)[0]) + 360) % 360;
        }

        if (sensorEvent.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            System.arraycopy(sensorEvent.values, 0, mGravity, 0, sensorEvent.values.length);
            mGravitySet = true;
        } else if (sensorEvent.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) {
            System.arraycopy(sensorEvent.values, 0, mGeomagnetic, 0, sensorEvent.values.length);
            mGeomagneticSet = true;
        }
        if (mGravitySet && mGeomagneticSet) {
            SensorManager.getRotationMatrix(rMatrix, null, mGravity, mGeomagnetic);
            //SensorManager.getOrientation(rMatrix, orientation);
            mAzimuth = (int) (Math.toDegrees(SensorManager.getOrientation(rMatrix, orientation)[0]) + 360) % 360;
        }

        mAzimuth = Math.round(mAzimuth);
        imageView.setRotation(-mAzimuth);

        String direction = "NW";

        if (mAzimuth >= 350 || mAzimuth <= 10)
            direction = "N";
        if (mAzimuth < 350 && mAzimuth > 280)
            direction = "NW";
        if (mAzimuth <= 280 && mAzimuth > 260)
            direction = "W";
        if (mAzimuth <= 260 && mAzimuth > 190)
            direction = "SW";
        if (mAzimuth <= 190 && mAzimuth > 170)
            direction = "S";
        if (mAzimuth <= 170 && mAzimuth > 100)
            direction = "SE";
        if (mAzimuth <= 100 && mAzimuth > 80)
            direction = "E";
        if (mAzimuth <= 80 && mAzimuth > 10)
            direction = "NE";


        textView.setText(mAzimuth + "° " + direction);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}