package com.example.practica2;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

public class LightManager implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor temperatureSensor;

    float light;

    //Para detectar la cantidad de luz ambiental
    LightManager(Context ctx){

        sensorManager = (SensorManager) ctx.getSystemService(Context.SENSOR_SERVICE);
        if (sensorManager != null) {
            temperatureSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
            if (temperatureSensor != null) {
                sensorManager.registerListener(this, temperatureSensor, SensorManager.SENSOR_DELAY_NORMAL);
            } else {
                System.out.print("GYRO sensor not available\n");
            }
        }
    }
    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if (sensorEvent.sensor.getType() == Sensor.TYPE_LIGHT) {
            light = sensorEvent.values[0];
        }
    }

    float getLight(){return  light;}
    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

}
