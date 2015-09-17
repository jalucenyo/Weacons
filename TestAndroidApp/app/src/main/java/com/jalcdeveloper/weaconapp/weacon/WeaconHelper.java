package com.jalcdeveloper.weaconapp.weacon;

import android.net.Uri;

import com.jalcdeveloper.weaconapp.R;
import com.jalcdeveloper.weaconapp.database.Weacon;

public class WeaconHelper {

    public static final String TYPE_AMBIENT = "ambient";
    public static final String TYPE_CONTROL = "control";

    public static final String ATTR_STRIP_VAlUE_BRIGHTNESS = "brightness";
    public static final String ATTR_AMBIENT_SENSOR_TEMPERATURE = "temp";
    public static final String ATTR_AMBIENT_SENSOR_HUMIDITY = "humidity";

    public static int getImage(Weacon weacon) {
        int image;
        if (weacon.get_tipo().equals(WeaconHelper.TYPE_CONTROL)) {
            image = R.drawable.light_off;
        } else if (weacon.get_tipo().equals(WeaconHelper.TYPE_AMBIENT)) {
            image = R.drawable.temp_cold;
        } else {
            image = R.drawable.sensor;
        }

        return image;
    }

}
