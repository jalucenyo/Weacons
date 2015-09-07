package com.jalcdeveloper.testandroidhome.ui.tv;

import android.support.v17.leanback.app.DetailsFragment;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jalcdeveloper.testandroidhome.R;
import com.jalcdeveloper.testandroidhome.weacon.WeaconNode;

/**
 * A placeholder fragment containing a simple view.
 */
public class WeaconDetailsFragment extends DetailsFragment {


    private WeaconNode selectedWeacon;
    private static final int DETAIL_THUMB_WIDTH = 274;
    private static final int DETAIL_THUMB_HEIGHT = 274;
    private static final int ACTION_PLAY = 1;
    private static final int ACTION_WATCH_LATER = 2;

    public WeaconDetailsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_weacon_details, container, false);
    }
}
