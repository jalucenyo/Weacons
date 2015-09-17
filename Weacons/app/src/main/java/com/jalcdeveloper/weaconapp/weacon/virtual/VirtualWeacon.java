package com.jalcdeveloper.weaconapp.weacon.virtual;

import android.os.Handler;

import com.jalcdeveloper.weaconapp.weacon.WeaconHelper;
import com.jalcdeveloper.weaconapp.weacon.WeaconManager;
import com.jalcdeveloper.weaconapp.weacon.WeaconNode;

import java.util.Random;


public class VirtualWeacon {

    private WeaconNode weaconNode;
    private long interval;
    private final Handler discoverHandler = new Handler();
    private final Handler updateHandler = new Handler();

    private final Runnable discoverRunnable = new Runnable() {
        @Override
        public void run() {
            discover();
        }
    };

    private final Runnable updateRunnable = new Runnable() {
        @Override
        public void run() {
            update();
        }
    };

    public VirtualWeacon(WeaconNode weacon, long intervalms){
        this.weaconNode = weacon;
        this.interval = intervalms;
        discover();
        if(weaconNode.getType().equals(WeaconHelper.TYPE_AMBIENT)) {
            update();
        }
    }


    private void discover(){
        discoverHandler.removeCallbacks(discoverRunnable);
        WeaconManager.publishDiscoverWeacon(this.weaconNode);
        discoverHandler.postDelayed(discoverRunnable, interval);

    }

    private void update(){
        discoverHandler.removeCallbacks(updateRunnable);
        Random r = new Random();
        double tempRandomValue = 19 + (38 - 19) * r.nextDouble();
        double humidityRandomValue = 30 + (90 - 30) * r.nextDouble();
        this.weaconNode.setDoubleAttribute(WeaconHelper.ATTR_AMBIENT_SENSOR_TEMPERATURE, tempRandomValue);
        this.weaconNode.setDoubleAttribute(WeaconHelper.ATTR_AMBIENT_SENSOR_HUMIDITY, humidityRandomValue);
        WeaconManager.publishWeacon(this.weaconNode);
        discoverHandler.postDelayed(updateRunnable, interval);
    }

}
