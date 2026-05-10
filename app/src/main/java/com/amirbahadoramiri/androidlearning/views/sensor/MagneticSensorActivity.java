package com.amirbahadoramiri.androidlearning.views.sensor;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.amirbahadoramiri.androidlearning.R;
import com.amirbahadoramiri.androidlearning.bases.BaseActivity;

public class MagneticSensorActivity extends BaseActivity implements SensorEventListener {

    SensorManager sensorManager;
    AppCompatTextView textViewSensor;

    Sensor magnetic,accelerometer;
    private float[] accelerometerData = null;
    private float[] magneticFieldData = null;

    ConstraintLayout cons;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        edgeEnabled();
        setContentView(R.layout.activity_sensor);
        setViewCompat();

        cons = findViewById(R.id.cons);
        textViewSensor = findViewById(R.id.textViewSensor);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        magnetic = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        if ( magnetic == null) {
            Toast.makeText(this, "magnetic not suppoerted", Toast.LENGTH_SHORT).show();
        }
        if ( accelerometer == null) {
            Toast.makeText(this, "accelerometer not suppoerted", Toast.LENGTH_SHORT).show();
        }
        sensorManager.registerListener(this, magnetic, SensorManager.SENSOR_STATUS_ACCURACY_LOW);
        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_STATUS_ACCURACY_LOW);
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, magnetic, SensorManager.SENSOR_STATUS_ACCURACY_LOW);
        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_STATUS_ACCURACY_LOW);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            accelerometerData = event.values.clone();
        } else if (event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) {
            magneticFieldData = event.values.clone();
        }

        if (accelerometerData != null && magneticFieldData != null) {
            calculateOrientation();
        }
        
    }

    private void calculateOrientation() {
        float[] rotationMatrix = new float[9];
        boolean rotationMatrixCalculated = SensorManager.getRotationMatrix(
                rotationMatrix,
                null, // این پارامتر برای فیلتر کردن ممکنه استفاده بشه
                accelerometerData,
                magneticFieldData
        );

        if (rotationMatrixCalculated) {
            float[] orientation = new float[3]; // این آرایه شامل Azimuth, Pitch, Roll میشه
            float[] orientationCalculated = SensorManager.getOrientation(rotationMatrix, orientation);

            float azimuth = orientation[0]; // این همون زاویه شمال مغناطیسی هست (بر حسب رادیان)

            // اینجا می‌تونی مقدار azimuth رو تبدیل به درجه کنی و استفاده کنی
            int azimuthDegrees = (int) Math.toDegrees(azimuth);

            // azimuthDegrees حالا زاویه شمال مغناطیسی رو نشون میده
            // مقدارش بین -180 تا +180 درجه است.
            // 0 درجه یعنی شمال، 90 درجه یعنی شرق، 180 درجه یعنی جنوب، -90 درجه یعنی غرب.
//            0 north
//            90 east
//            -90 west
//            180 south

            textViewSensor.setText(String.valueOf(getDegrees(azimuthDegrees)));
            cons.setRotation((azimuthDegrees*-1));
        }
    }

    public int getDegrees(int degrees) {
        if (degrees < 0)
            degrees = degrees * (-1);
        return degrees;
    }

}
