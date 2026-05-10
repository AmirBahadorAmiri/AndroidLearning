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

public class AccelerometerSensorActivity extends BaseActivity implements SensorEventListener {

    SensorManager sensorManager;
    AppCompatTextView textViewSensor;
    int minX = -1, minY = -1, minZ = -1;
    int maxX = 0, maxY = 0, maxZ = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        edgeEnabled();
        setContentView(R.layout.activity_sensor);
        setViewCompat();

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        textViewSensor = findViewById(R.id.textViewSensor);
        if ( sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) == null ) {
            Toast.makeText(this, "not suppoerted", Toast.LENGTH_SHORT).show();
        }
        sensorManager.registerListener(this,sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),SensorManager.SENSOR_STATUS_ACCURACY_HIGH);

//        ListView listViewSensor = findViewById(R.id.listViewSensor);
//        List<Sensor> list = sensorManager.getSensorList(Sensor.TYPE_ALL);
//        List names = new ArrayList();
//        for (Sensor sensor:
//             list) {
//            names.add(sensor.getName()+"\nvendor: "+sensor.getVendor()+"\npower: "+sensor.getPower()+"\nversion: "+sensor.getVersion()+"\nmax delay: "+sensor.getMaxDelay()+"\nmax range: "+sensor.getMaximumRange()+"\nresoulution: "+sensor.getResolution()+"\ntype: "+sensor.getType()+"\ntype string: "+sensor.getStringType());
//        }
//        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,names);
//        listViewSensor.setAdapter(adapter);

    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this,sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),SensorManager.SENSOR_STATUS_ACCURACY_HIGH);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {}

    @Override
    public void onSensorChanged(SensorEvent event) {
        int valueX = (int) event.values[0];
        int valueY = (int) event.values[1];
        int valueZ = (int) event.values[2];

        if ( minX == -1 ) minX = valueX;
        else if (valueX < minX) minX = valueX;
        if ( valueX > maxX ) maxX = valueX;

        if ( minY == -1 ) minY = valueY;
        else if (valueY < minY) minY = valueY;
        if ( valueY > maxY ) maxY = valueY;

        if ( minZ == -1 ) minZ = valueZ;
        else if (valueZ < minZ) minZ = valueZ;
        if ( valueZ > maxZ ) maxZ = valueZ;


        textViewSensor.setText("AccelerometerMeter\n\n"+"X: " + valueX + "\nmax: " + maxX+" , min: " + minX + "\nY: " + valueY + "\nmax: " + maxY + " , min: " + minY + "\nZ: " + valueZ + "\nmax: " + maxZ + " , min: " + minZ);
    }
}
