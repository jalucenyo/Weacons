package com.jalcdeveloper.weaconapp.ui;

import android.app.Activity;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jalcdeveloper.weaconapp.R;
import com.jalcdeveloper.weaconapp.weacon.WeaconNode;
import com.jalcdeveloper.weaconapp.adapter.WeaconAdapter;
import com.jalcdeveloper.weaconapp.weacon.WeaconManager;

public class SensorsFragment extends Fragment implements WeaconManager.WeaconListener {

    private static final String TAG = SensorsFragment.class.getSimpleName();

    private WeaconAdapter mSensorAdapter;
    private RecyclerView mRecyclerView;
    private TextView mReclycleEmptyTextView;


    // TODO: Rename and change types and number of parameters
    public static SensorsFragment newInstance(String param1, String param2) {
        SensorsFragment fragment = new SensorsFragment();
        return fragment;
    }

    public SensorsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView =  inflater.inflate(R.layout.fragment_sensors, container, false);

        mReclycleEmptyTextView = (TextView) rootView.findViewById(R.id.recyclerview_sensors_empty);
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerview_sensors);

        mSensorAdapter = new WeaconAdapter(getActivity());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(mSensorAdapter);

        WeaconManager.startDiscovery(this);
        mSensorAdapter.setWeaconList(WeaconManager.getWeaconList());
        if(mSensorAdapter.getItemCount() > 0) mReclycleEmptyTextView.setVisibility(View.INVISIBLE);

        return rootView;
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    /**
     * Events Listener WeaconManager.WeaconListener
     */
    @Override
    public void onNewWeacon(WeaconNode weacon) {

        WeaconManager.suscribeWeacon(weacon, new WeaconNode.WeaconNodeListener() {
            @Override
            public void onUpdate(final WeaconNode weacon) {
                Log.d(TAG, "onUpdate Weacon");

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mSensorAdapter.notifyItemChanged(
                                WeaconManager.getWeaconList().indexOf(weacon));
                    }
                });
            }
        });

        /** Notify changes in RecycleView **/
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (mSensorAdapter.getItemCount() > 0)
                    mReclycleEmptyTextView.setVisibility(View.INVISIBLE);
                mSensorAdapter.notifyDataSetChanged();
            }
        });

    }

    /**
     * Events Listener WeaconManager.WeaconListener
     */
    @Override
    public void onError(String message) {
        Log.e(TAG, "ERROR:" + message);
    }

}
