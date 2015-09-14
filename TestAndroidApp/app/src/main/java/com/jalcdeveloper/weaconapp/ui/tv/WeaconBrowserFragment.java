package com.jalcdeveloper.weaconapp.ui.tv;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v17.leanback.app.BrowseFragment;
import android.support.v17.leanback.widget.ArrayObjectAdapter;
import android.support.v17.leanback.widget.HeaderItem;
import android.support.v17.leanback.widget.ListRow;
import android.support.v17.leanback.widget.ListRowPresenter;
import android.view.View;

import com.jalcdeveloper.weaconapp.R;
import com.jalcdeveloper.weaconapp.database.Sensor;
import com.jalcdeveloper.weaconapp.database.WeaconsContract;
import com.jalcdeveloper.weaconapp.database.WeaconsDbHelper;
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

        //TODO: Icono de la aplicacion
        //TODO: JALC - Descubrir sensores e guardar en base de datos.
        //TODO: JALC - Sensores virtuales !!!
        WeaconsDbHelper db = new WeaconsDbHelper(getActivity().getApplicationContext());
        //Descomentar para agregar un sensor de prueba
        /*
        Sensor sensor = new Sensor();
        sensor.set_nombre("Prueba");
        sensor.set_descripcion("Canal de prueba");
        sensor.set_canal("ambient_sensor_04");
        sensor.set_tipo("Ambient");
        db.addSensor(sensor);
        */

        mRowsAdapter = new ArrayObjectAdapter(new ListRowPresenter());
        setAdapter(mRowsAdapter);

        setBrandColor(getResources().getColor(R.color.primary));
        setBadgeDrawable(getResources().getDrawable(R.drawable.ic_logo));


        for(int position=0; position < HEADERS.length; position++){

            //ObjectAdapter rowContents = new CursorObjectAdapter((new SinglePresenterSelector(new WeaconPresenter())));
            HeaderItem headerItem = new HeaderItem(position, HEADERS[position]);

            // TODO: CMMATA - Asociar WeacomDetailPresenter.
            ArrayObjectAdapter listRowAdapter = new ArrayObjectAdapter(new WeaconPresenter());
            Cursor sensorsList = db.getSensors();
            sensorsList.moveToFirst();
            while (sensorsList.moveToNext()) {
                listRowAdapter.add(sensorsList.getColumnIndex(WeaconsContract.Sensors.COLUMN_NAME_TITLE));
            }
            sensorsList.close();

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
