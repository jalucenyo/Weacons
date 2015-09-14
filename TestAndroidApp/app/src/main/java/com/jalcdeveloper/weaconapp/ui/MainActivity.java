/* This program is free software: you can redistribute it and/or modify
*  it under the terms of the GNU General Public License as published by
*  the Free Software Foundation, either version 3 of the License, or
*  (at your option) any later version.
*
*  This program is distributed in the hope that it will be useful,
*  but WITHOUT ANY WARRANTY; without even the implied warranty of
*  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
*  GNU General Public License for more details.
*
*  You should have received a copy of the GNU General Public License
*  along with this program.  If not, see <http://www.gnu.org/licenses/>.
*  
*  <jalucenyo@gmail.com> 13 of August, 2015
*  Jose A. Luceño
*/
package com.jalcdeveloper.weaconapp.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.jalcdeveloper.weaconapp.R;
import com.jalcdeveloper.weaconapp.weacon.WeaconHelper;
import com.jalcdeveloper.weaconapp.weacon.WeaconNode;
import com.jalcdeveloper.weaconapp.weacon.virtual.VirtualWeacon;

public class MainActivity extends AppCompatActivity{

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        // Start Virtual Sensors
        WeaconNode vWeacon02 = new WeaconNode();
        vWeacon02.setType(WeaconHelper.TYPE_AMBIENT);
        vWeacon02.setChannel("ambient_sensor_02");
        vWeacon02.setDoubleAttribute(WeaconHelper.ATTR_AMBIENT_SENSOR_TEMPERATURE, 20.1);
        vWeacon02.setDoubleAttribute(WeaconHelper.ATTR_AMBIENT_SENSOR_HUMIDITY, 53.2);
        VirtualWeacon mVirtualWeacon02 = new VirtualWeacon(vWeacon02, 2000);

        WeaconNode vWeacon03 = new WeaconNode();
        vWeacon03.setType(WeaconHelper.TYPE_AMBIENT);
        vWeacon03.setChannel("ambient_sensor_03");
        vWeacon03.setDoubleAttribute(WeaconHelper.ATTR_AMBIENT_SENSOR_TEMPERATURE, 20.1);
        vWeacon03.setDoubleAttribute(WeaconHelper.ATTR_AMBIENT_SENSOR_HUMIDITY, 53.2);
        VirtualWeacon mVirtualWeacon03= new VirtualWeacon(vWeacon03, 2000);

        WeaconNode vWeacon04 = new WeaconNode();
        vWeacon04.setType(WeaconHelper.TYPE_AMBIENT);
        vWeacon04.setChannel("ambient_sensor_04");
        vWeacon04.setDoubleAttribute(WeaconHelper.ATTR_AMBIENT_SENSOR_TEMPERATURE, 20.1);
        vWeacon04.setDoubleAttribute(WeaconHelper.ATTR_AMBIENT_SENSOR_HUMIDITY, 53.2);
        VirtualWeacon mVirtualWeacon04 = new VirtualWeacon(vWeacon04, 2000);

        WeaconNode vWeacon05 = new WeaconNode();
        vWeacon05.setType(WeaconHelper.TYPE_AMBIENT);
        vWeacon05.setChannel("ambient_sensor_05");
        vWeacon05.setDoubleAttribute(WeaconHelper.ATTR_AMBIENT_SENSOR_TEMPERATURE, 20.1);
        vWeacon05.setDoubleAttribute(WeaconHelper.ATTR_AMBIENT_SENSOR_HUMIDITY, 53.2);
        VirtualWeacon mVirtualWeacon05= new VirtualWeacon(vWeacon05, 2000);


        WeaconNode vWeacon06 = new WeaconNode();
        vWeacon06.setType(WeaconHelper.TYPE_CONTROL);
        vWeacon06.setChannel("control_strip_02");
        vWeacon06.setDoubleAttribute(WeaconHelper.ATTR_STRIP_VAlUE_BRIGHTNESS, 125);
        VirtualWeacon mVirtualWeacon06= new VirtualWeacon(vWeacon06, 2000);

        WeaconNode vWeacon07 = new WeaconNode();
        vWeacon07.setType(WeaconHelper.TYPE_CONTROL);
        vWeacon07.setChannel("control_strip_03");
        vWeacon07.setDoubleAttribute(WeaconHelper.ATTR_STRIP_VAlUE_BRIGHTNESS, 125);
        VirtualWeacon mVirtualWeacon07 = new VirtualWeacon(vWeacon07, 2000);

        WeaconNode vWeacon08 = new WeaconNode();
        vWeacon08.setType(WeaconHelper.TYPE_CONTROL);
        vWeacon08.setChannel("control_strip_04");
        vWeacon08.setDoubleAttribute(WeaconHelper.ATTR_STRIP_VAlUE_BRIGHTNESS, 125);
        VirtualWeacon mVirtualWeacon08= new VirtualWeacon(vWeacon08, 2000);

    }
}
