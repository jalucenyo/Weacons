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
package com.jalcdeveloper.testandroidhome.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.jalcdeveloper.testandroidhome.R;

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

    }


    // Implements OnSeekBarChangeListener
//    @Override
//    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {}
//
//    @Override
//    public void onStartTrackingTouch(SeekBar seekBar) { }
//
//    @Override
//    public void onStopTrackingTouch(SeekBar seekBar) {
//        Log.d(TAG, "OnStopTRackingTouch SeekBar : " + seekBar.getProgress());
//        try {
//            pubnub.publish(channel + "_value",
//                    new JSONObject("{\"value\":\"" + seekBar.getProgress() + "\"}"),
//                    new Callback() {
//                        @Override
//                        public void successCallback(String channel, Object message) {
//                            super.successCallback(channel, message);
//                            Log.d(TAG, "Ok! publish.");
//                        }
//
//                        @Override
//                        public void errorCallback(String channel, PubnubError error) {
//                            super.errorCallback(channel, error);
//                            Log.e(TAG, error.getErrorString());
//                        }
//                    });
//        }catch (Exception ex){
//            Log.e(TAG, ex.getMessage());
//        }
//    }
    // END Implements OnSeekBarChangeListener
}
