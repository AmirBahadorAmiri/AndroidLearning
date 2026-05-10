package com.amirbahadoramiri.androidlearning.views.sensor;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;

import com.amirbahadoramiri.androidlearning.R;
import com.amirbahadoramiri.androidlearning.bases.BaseActivity;

import java.util.ArrayList;
import java.util.List;

public class SensorListActivity extends BaseActivity {

    SensorManager sensorManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        edgeEnabled();
        setContentView(R.layout.activity_sensor_list);
        setViewCompat();

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        ListView listViewSensor = findViewById(R.id.listViewSensor);
        List<Sensor> list = sensorManager.getSensorList(Sensor.TYPE_ALL);
        List<String> names = new ArrayList<>();
        for (Sensor sensor :
                list) {
            names.add(sensor.getName() + "\nvendor: " + sensor.getVendor() + "\npower: " + sensor.getPower() + "\nversion: " + sensor.getVersion() + "\nmax delay: " + sensor.getMaxDelay() + "\nmax range: " + sensor.getMaximumRange() + "\nresoulution: " + sensor.getResolution() + "\ntype: " + sensor.getType() + "\ntype string: " + sensor.getStringType());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, names);
        listViewSensor.setAdapter(adapter);

    }
}
