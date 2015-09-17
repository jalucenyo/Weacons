package com.jalcdeveloper.weaconapp.ui.tv;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v17.leanback.app.BrowseFragment;
import android.support.v17.leanback.widget.ArrayObjectAdapter;
import android.support.v17.leanback.widget.HeaderItem;
import android.support.v17.leanback.widget.ListRow;
import android.support.v17.leanback.widget.ListRowPresenter;
import android.support.v17.leanback.widget.OnItemViewClickedListener;
import android.support.v17.leanback.widget.Presenter;
import android.support.v17.leanback.widget.Row;
import android.support.v17.leanback.widget.RowPresenter;
import android.view.View;

import com.jalcdeveloper.weaconapp.R;
import com.jalcdeveloper.weaconapp.database.Weacon;
import com.jalcdeveloper.weaconapp.database.WeaconMapper;
import com.jalcdeveloper.weaconapp.database.WeaconsDbHelper;
import com.jalcdeveloper.weaconapp.presenter.WeaconPresenter;
import com.jalcdeveloper.weaconapp.weacon.WeaconHelper;
import com.jalcdeveloper.weaconapp.weacon.WeaconManager;
import com.jalcdeveloper.weaconapp.weacon.WeaconNode;

import java.io.Serializable;

public class WeaconBrowserFragment extends BrowseFragment
        implements WeaconManager.WeaconListener {

    private ArrayObjectAdapter mRowsAdapter;
    private WeaconsDbHelper db;

    private static final String[] HEADERS = new String[]{
            "Luces", "Ambientales", "Consumo"
    };

    public void init(){

        //TODO: Icono de la aplicacion
        //Para reiniciar la BD
        //getActivity().getApplicationContext().deleteDatabase("Weaconsapp.db");
        db = new WeaconsDbHelper(getActivity().getApplicationContext());
        //Descomentar para agregar un sensor de prueba
        /**
        Weacon sensor = new Weacon();
        sensor.set_nombre("Prueba");
        sensor.set_descripcion("Canal de prueba");
        sensor.set_canal("ambient_sensor_04");
        sensor.set_tipo("Ambientales");
        db.addSensor(sensor);
        sensor.set_nombre("Prueba2");
        sensor.set_descripcion("Canal de prueba 2");
        sensor.set_canal("control_strip_04");
        sensor.set_tipo("Luces");
        db.addSensor(sensor);
        **/
        mRowsAdapter = new ArrayObjectAdapter(new ListRowPresenter());
        setAdapter(mRowsAdapter);

        setBrandColor(getResources().getColor(R.color.primary));
        setBadgeDrawable(getResources().getDrawable(R.drawable.ic_logo));
        Cursor sensorsList = db.getSensors();

        for(int position=0; position < HEADERS.length; position++){

            //ObjectAdapter rowContents = new CursorObjectAdapter((new SinglePresenterSelector(new WeaconPresenter())));
            HeaderItem headerItem = new HeaderItem(position, HEADERS[position]);

            ArrayObjectAdapter listRowAdapter = new ArrayObjectAdapter(new WeaconPresenter());
            if (sensorsList.moveToFirst()) {
                do {
                    Weacon aux = new Weacon(
                            Integer.parseInt(sensorsList.getString(0)),
                            sensorsList.getString(1),
                            sensorsList.getString(2),
                            sensorsList.getString(3),
                            sensorsList.getString(4)
                    );

                    if(position==0 && aux.get_tipo().equals(WeaconHelper.TYPE_CONTROL)){
                        listRowAdapter.add(aux);
                    }

                    if(position==1 && aux.get_tipo().equals(WeaconHelper.TYPE_AMBIENT)){
                        listRowAdapter.add(aux);
                    }

//                    if (aux.get_tipo().equals(HEADERS[position])) {
//                        listRowAdapter.add(aux);
//                    }
                } while (sensorsList.moveToNext());
            }

            mRowsAdapter.add(new ListRow(headerItem,listRowAdapter));
            //mRowsAdapter.add(new ListRow(headerItem,rowContents));

        }
        sensorsList.close();

        WeaconManager.startDiscovery(this);
        setOnItemViewClickedListener(getDefaultItemViewClickedListener());
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
    }

    @Override
    public void onNewWeacon(WeaconNode weacon) {


        mRowsAdapter.notifyArrayItemRangeChanged(0, mRowsAdapter.size());

        //Check this sensor exist in database.
        Cursor sensorsList = db.getSensors();
        if(sensorsList.moveToFirst()){
            do{
                // If exists sensor exit method
                if (sensorsList.getString(3).equals(weacon.getChannel())) return;
            } while (sensorsList.moveToNext());
        }

        db.addSensor(WeaconMapper.toSensor(weacon));

    }

    @Override
    public void onError(String message) {

    }

    private OnItemViewClickedListener getDefaultItemViewClickedListener() {
        return new OnItemViewClickedListener() {
            @Override
            public void onItemClicked(Presenter.ViewHolder viewHolder, Object o,
                                      RowPresenter.ViewHolder viewHolder2, Row row) {

                Intent intent = new Intent(getActivity(), WeaconDetailsActivity.class);
                intent.putExtra(Weacon.INTENT_EXTRA_WEACON, (Serializable)o);
                startActivity(intent);
            }
        };
    }

}
