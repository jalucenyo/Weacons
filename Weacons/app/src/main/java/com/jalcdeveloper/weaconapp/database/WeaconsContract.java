package com.jalcdeveloper.weaconapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

/**
 * Created by Carles on 12/09/15.
 */
public final class WeaconsContract {
    /**
     * Constructor
     */
    public WeaconsContract(){}

    public static abstract class Sensors implements BaseColumns {
        public static final String TABLE_NAME = "sensores";
        public static final String COLUMN_NAME_SENSOR_ID = "idsensor";
        public static final String COLUMN_NAME_TITLE = "nombre";
        public static final String COLUMN_NAME_DESC = "descripcion";
        public static final String COLUMN_NAME_CHANNEL = "canal";
        public static final String COLUMN_NAME_TYPE = "tipo";
    }

    public static abstract class Events implements BaseColumns {
        public static final String TABLE_NAME = "eventos";
        public static final String COLUMN_NAME_EVENT_ID = "idevento";
        public static final String COLUMN_NAME_SENSOR = "sensor";
        public static final String COLUMN_NAME_MENSAJE = "mensaje";
        public static final String COLUMN_NAME_FECHA = "fecha";
    }

    private static final String TEXT_TYPE = " TEXT";
    private static final String DATE_TYPE = " DATE";
    private static final String COMMA_SEP = ",";
    public static final String SQL_CREATE_SENSORS =
        "CREATE TABLE " + Sensors.TABLE_NAME + " (" +
        Sensors._ID + " INTEGER PRIMARY KEY," +
        Sensors.COLUMN_NAME_SENSOR_ID + TEXT_TYPE + COMMA_SEP +
        Sensors.COLUMN_NAME_TITLE + TEXT_TYPE + COMMA_SEP +
        Sensors.COLUMN_NAME_DESC + TEXT_TYPE + COMMA_SEP +
        Sensors.COLUMN_NAME_CHANNEL + TEXT_TYPE + COMMA_SEP +
        Sensors.COLUMN_NAME_TYPE + TEXT_TYPE +
        " )";
    public static final String SQL_CREATE_EVENTS =
        "CREATE TABLE " + Events.TABLE_NAME + " (" +
        Events._ID + " INTEGER PRIMARY KEY," +
        Events.COLUMN_NAME_EVENT_ID + TEXT_TYPE + COMMA_SEP +
        Events.COLUMN_NAME_SENSOR + TEXT_TYPE + COMMA_SEP +
        Events.COLUMN_NAME_MENSAJE + TEXT_TYPE + COMMA_SEP +
        Events.COLUMN_NAME_FECHA + DATE_TYPE +
        " )";
    public static final String SQL_DELETE_SENSORS = "DROP TABLE IF EXISTS " + Sensors.TABLE_NAME;
    public static final String SQL_DELETE_EVENTS = "DROP TABLE IF EXISTS " + Events.TABLE_NAME;
}
