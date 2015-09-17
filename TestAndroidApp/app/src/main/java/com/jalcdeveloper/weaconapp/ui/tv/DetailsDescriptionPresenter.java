package com.jalcdeveloper.weaconapp.ui.tv;

import android.content.Context;
import android.support.v17.leanback.widget.AbstractDetailsDescriptionPresenter;
import android.util.Log;

import com.jalcdeveloper.weaconapp.database.Weacon;
import com.jalcdeveloper.weaconapp.presenter.WeaconPresenter;
import com.jalcdeveloper.weaconapp.weacon.WeaconHelper;
import com.jalcdeveloper.weaconapp.weacon.WeaconManager;
import com.jalcdeveloper.weaconapp.weacon.WeaconNode;

/**
 * Created by carlosmasip on 16/09/15.
 */
public class DetailsDescriptionPresenter extends AbstractDetailsDescriptionPresenter {
    private final Context mContext;
    private static final String TAG = WeaconPresenter.class.getSimpleName();

    private DetailsDescriptionPresenter() {
        super();
        this.mContext = null;
    }

    public DetailsDescriptionPresenter(Context ctx) {
        super();
        this.mContext = ctx;
    }

    @Override
    protected void onBindDescription(ViewHolder viewHolder, Object o) {
        Weacon weacon = (Weacon) o;

        if (weacon != null) {
            Log.d("Presenter", String.format("%s, %s", weacon.get_nombre(), weacon.get_descripcion()));
            viewHolder.getTitle().setText(weacon.get_nombre());
            viewHolder.getSubtitle().setText(weacon.get_tipo());
            WeaconNode node = WeaconManager.getWeaconNode(weacon.get_canal());
            if (node != null && node.getType().equals(WeaconHelper.TYPE_AMBIENT)) {
                viewHolder.getBody().setText(node.getDoubleAttribute(WeaconHelper.ATTR_AMBIENT_SENSOR_TEMPERATURE) + "C");
            } else {
                viewHolder.getBody().setText(weacon.get_descripcion());
            }
        }
    }
}
