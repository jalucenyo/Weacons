package com.jalcdeveloper.weaconapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Carles on 14/09/15.
 */
public class WeaconsDbHelper extends SQLiteOpenHelper {
    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Weaconsapp.db";

    /**
     * Constructor
     * @param context Contexto
     */
    public WeaconsDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * Crear la BD
     * @param db Base de datos
     */
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(WeaconsContract.SQL_CREATE_SENSORS);
        db.execSQL(WeaconsContract.SQL_CREATE_EVENTS);
    }

    /**
     * Subir version de la BD
     * @param db         Base de datos
     * @param oldVersion Version antigua
     * @param newVersion Version nueva
     */
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    /**
     * Bajar version de la BD
     * @param db         Base de datos
     * @param oldVersion Version antigua
     * @param newVersion Version nueva
     */
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + WeaconsContract.Sensors.TABLE_NAME);
    }

    /**
     * Agregar weacon
     * @param weacon Weacon que queremos añadir
     */
    public void addSensor(Weacon weacon) {
        ContentValues values = new ContentValues();
        values.put(WeaconsContract.Sensors.COLUMN_NAME_TITLE, weacon.get_nombre());
        values.put(WeaconsContract.Sensors.COLUMN_NAME_DESC, weacon.get_descripcion());
        values.put(WeaconsContract.Sensors.COLUMN_NAME_CHANNEL, weacon.get_canal());
        values.put(WeaconsContract.Sensors.COLUMN_NAME_TYPE, weacon.get_tipo());

        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(WeaconsContract.Sensors.TABLE_NAME, null, values);
        db.close();
    }

    /**
     * Listado de todos los sensores
     * @return cursor
     */
    public Cursor getSensors() {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] projection = {
            WeaconsContract.Sensors._ID,
            WeaconsContract.Sensors.COLUMN_NAME_TITLE,
            WeaconsContract.Sensors.COLUMN_NAME_DESC,
            WeaconsContract.Sensors.COLUMN_NAME_CHANNEL,
            WeaconsContract.Sensors.COLUMN_NAME_TYPE
        };
        String sortOrder = WeaconsContract.Sensors.COLUMN_NAME_TITLE + " DESC";

        Cursor cur = db.query(
                WeaconsContract.Sensors.TABLE_NAME,  // The table to query
                projection,                          // The columns to return
                null,                                // The columns for the WHERE clause
                null,                                // The values for the WHERE clause
                null,                                // don't group the rows
                null,                                // don't filter by row groups
                sortOrder                            // The sort order
        );

        return cur;
    }

}
