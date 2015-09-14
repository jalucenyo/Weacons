package com.jalcdeveloper.weaconapp.database;

import com.jalcdeveloper.weaconapp.weacon.WeaconNode;

/**
 * Created by jalucenyo on 14/9/15.
 */
public class WeaconMapper {

    /**
     * Mapping object WeaconNode to Sensor
     * @param weacon
     * @return
     */
    public static Sensor toSensor(WeaconNode weacon){

        Sensor sensor = new Sensor();
        sensor.set_canal(weacon.getChannel());
        sensor.set_tipo(weacon.getType());
        sensor.set_nombre(weacon.getChannel());
        sensor.set_descripcion("Pending Configure");
        return sensor;

    }

}
