package com.jalcdeveloper.weaconapp.ui.tv;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.jalcdeveloper.weaconapp.R;
import com.jalcdeveloper.weaconapp.database.WeaconMapper;
import com.jalcdeveloper.weaconapp.database.WeaconsDbHelper;
import com.jalcdeveloper.weaconapp.weacon.WeaconManager;
import com.jalcdeveloper.weaconapp.weacon.WeaconNode;

public class MainTvActivity extends Activity
        implements WeaconManager.WeaconListener {

    private static final String TAG = MainTvActivity.class.getSimpleName();
    private WeaconsDbHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_tv);

        db = new WeaconsDbHelper(getApplicationContext());

        // Start discovery weacons connected in wifi.
        WeaconManager.startDiscovery(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onNewWeacon(WeaconNode weacon) {
        Log.d(TAG, "onNewWeacon " + weacon.getChannel());
        db.addSensor(WeaconMapper.toSensor(weacon));
    }

    @Override
    public void onError(String message) {

    }
}
