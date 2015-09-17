package com.jalcdeveloper.weaconapp.weacon;

import com.jalcdeveloper.weaconapp.R;
import com.jalcdeveloper.weaconapp.database.Weacon;

public class WeaconHelper {

    public static final String TYPE_AMBIENT = "ambient";
    public static final String TYPE_CONTROL = "control";

    public static final String ATTR_STRIP_VAlUE_BRIGHTNESS = "brightness";
    public static final String ATTR_AMBIENT_SENSOR_TEMPERATURE = "temp";
    public static final String ATTR_AMBIENT_SENSOR_HUMIDITY = "humidity";

    public static String getImage(Weacon weacon) {
        String imageUri;
        if (weacon.get_tipo().equals(WeaconHelper.TYPE_CONTROL)) {
            imageUri = "drawable://" + R.drawable.temp_cold;
        } else if (weacon.get_tipo().equals(WeaconHelper.TYPE_AMBIENT)) {
            imageUri = "drawable://" + R.drawable.light_on;
        } else {
            imageUri = "drawable://" + R.drawable.sensor;
        }

        return imageUri;
    }

}
