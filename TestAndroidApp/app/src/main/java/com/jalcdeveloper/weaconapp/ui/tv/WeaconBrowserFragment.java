package com.jalcdeveloper.weaconapp.ui.tv;

import android.os.Bundle;
import android.support.v17.leanback.app.BrowseFragment;
import android.support.v17.leanback.widget.ArrayObjectAdapter;
import android.support.v17.leanback.widget.HeaderItem;
import android.support.v17.leanback.widget.ListRow;
import android.support.v17.leanback.widget.ListRowPresenter;
import android.view.View;

import com.jalcdeveloper.weaconapp.R;
import com.jalcdeveloper.weaconapp.presenter.WeaconPresenter;

public class WeaconBrowserFragment extends BrowseFragment {

    private ArrayObjectAdapter mRowsAdapter;

    private static final String[] HEADERS = new String[]{
            "Luces", "Ambientales", "Consumo"
    };

    private static final String[] WEACONS = new String[]{
            "WEACON_001", "WEACON_002", "WEACON_003"
    };


    public void init(){
        mRowsAdapter = new ArrayObjectAdapter(new ListRowPresenter());
        setAdapter(mRowsAdapter);

        setBrandColor(getResources().getColor(R.color.primary));
        setBadgeDrawable(getResources().getDrawable(R.drawable.ic_logo));


        for(int position=0; position < HEADERS.length; position++){

            //ObjectAdapter rowContents = new CursorObjectAdapter((new SinglePresenterSelector(new WeaconPresenter())));
            HeaderItem headerItem = new HeaderItem(position, HEADERS[position]);


            ArrayObjectAdapter listRowAdapter = new ArrayObjectAdapter(new WeaconPresenter());
            for(int index=0; index < WEACONS.length; index++){
                listRowAdapter.add(WEACONS[position]);
            }

            mRowsAdapter.add(new ListRow(headerItem,listRowAdapter));
            //mRowsAdapter.add(new ListRow(headerItem,rowContents));

        }

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
    }



}
