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
        //Para reiniciar la BD
        //getActivity().getApplicationContext().deleteDatabase("Weaconsapp.db");
        WeaconsDbHelper db = new WeaconsDbHelper(getActivity().getApplicationContext());
        //Descomentar para agregar un sensor de prueba
        /**
        Sensor sensor = new Sensor();
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

            // TODO: CMMATA - Asociar WeacomDetailPresenter.
            ArrayObjectAdapter listRowAdapter = new ArrayObjectAdapter(new WeaconPresenter());
            if (sensorsList.moveToFirst()) {
                do {
                    Sensor aux = new Sensor(
                            Integer.parseInt(sensorsList.getString(0)),
                            sensorsList.getString(1),
                            sensorsList.getString(2),
                            sensorsList.getString(3),
                            sensorsList.getString(4)
                    );
                    if (aux.get_tipo().equals(HEADERS[position])) {
                        listRowAdapter.add(aux);
                    }
                } while (sensorsList.moveToNext());
            }

            mRowsAdapter.add(new ListRow(headerItem,listRowAdapter));
            //mRowsAdapter.add(new ListRow(headerItem,rowContents));

        }
        sensorsList.close();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
    }



}
