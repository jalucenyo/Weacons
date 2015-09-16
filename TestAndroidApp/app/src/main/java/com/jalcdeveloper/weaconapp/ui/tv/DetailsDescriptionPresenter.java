package com.jalcdeveloper.weaconapp.ui.tv;

import android.support.v17.leanback.widget.AbstractDetailsDescriptionPresenter;
import android.util.Log;

import com.jalcdeveloper.weaconapp.database.Sensor;

/**
 * Created by carlosmasip on 16/09/15.
 */
public class DetailsDescriptionPresenter extends AbstractDetailsDescriptionPresenter {
    @Override
    protected void onBindDescription(ViewHolder viewHolder, Object o) {
        Sensor weacon = (Sensor) o;

        if (weacon != null) {
            Log.d("Presenter", String.format("%s, %s, %s", weacon.get_nombre(), weacon.get_descripcion()));
            viewHolder.getTitle().setText(weacon.get_nombre());
            viewHolder.getSubtitle().setText("Tipo " + weacon.get_tipo());
            viewHolder.getBody().setText(weacon.get_descripcion());
        }
    }
}
