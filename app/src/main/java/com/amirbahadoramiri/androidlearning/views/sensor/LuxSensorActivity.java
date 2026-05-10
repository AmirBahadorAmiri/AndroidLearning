package com.amirbahadoramiri.androidlearning.views.sensor;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;

import com.amirbahadoramiri.androidlearning.R;
import com.amirbahadoramiri.androidlearning.bases.BaseActivity;

public class LuxSensorActivity extends BaseActivity implements SensorEventListener {

    SensorManager sensorManager;
    AppCompatTextView textViewSensor;
    int min = -1, max = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        edgeEnabled();
        setContentView(R.layout.activity_sensor);
        setViewCompat();

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        textViewSensor = findViewById(R.id.textViewSensor);
        if (sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT) == null) {
            Toast.makeText(this, "not suppoerted", Toast.LENGTH_SHORT).show();
        }
        sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT), SensorManager.SENSOR_STATUS_ACCURACY_HIGH);
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT), SensorManager.SENSOR_STATUS_ACCURACY_HIGH);
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
        int value = (int) event.values[0];

        if (min == -1) min = value;
        else if (value < min) min = value;
        if (value > max) max = value;

        textViewSensor.setText("LuxMeter\n\n"+"value: " + value + "\nmax: " + max + "\nmin: " + min);
    }
}
