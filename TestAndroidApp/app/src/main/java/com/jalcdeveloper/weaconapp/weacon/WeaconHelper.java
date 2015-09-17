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

    public static String getImage(Weacon weacon) {
        Uri fileUri;
        if (weacon.get_tipo().equals(WeaconHelper.TYPE_CONTROL)) {
            fileUri = Uri.parse("android.resource://com.jalcdeveloper.weaconapp/" + R.drawable.temp_cold);
            //imageUri = "drawable://" + R.drawable.temp_cold;
        } else if (weacon.get_tipo().equals(WeaconHelper.TYPE_AMBIENT)) {
            fileUri = Uri.parse("android.resource://com.jalcdeveloper.weaconapp/" + R.drawable.light_on);
            //imageUri = "drawable://" + R.drawable.light_on;
        } else {
            fileUri = Uri.parse("android.resource://com.jalcdeveloper.weaconapp/" + R.drawable.sensor);
            //imageUri = "drawable://" + R.drawable.sensor;
        }

        return fileUri.getPath();
    }

}
