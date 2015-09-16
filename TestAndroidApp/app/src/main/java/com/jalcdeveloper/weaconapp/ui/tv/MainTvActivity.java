package com.jalcdeveloper.weaconapp.ui.tv;

import android.app.Activity;
import android.os.Bundle;
import android.view.MenuItem;

import com.jalcdeveloper.weaconapp.R;

public class MainTvActivity extends Activity{

    private static final String TAG = MainTvActivity.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_tv);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
