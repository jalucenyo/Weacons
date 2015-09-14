package com.jalcdeveloper.weaconapp.database;

/**
 * Created by Carles on 14/09/15.
 */
public class Sensor {

    private int _id;
    private String _nombre;
    private String _descripcion;
    private String _canal;
    private String _tipo;

    public Sensor() {}

    public Sensor(int id, String nombre, String descripcion, String canal, String tipo){
        this._id = id;
        this._nombre = nombre;
        this._descripcion = descripcion;
        this._canal = canal;
        this._tipo = tipo;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String get_nombre() {
        return _nombre;
    }

    public void set_nombre(String _nombre) {
        this._nombre = _nombre;
    }

    public String get_descripcion() {
        return _descripcion;
    }

    public void set_descripcion(String _descripcion) {
        this._descripcion = _descripcion;
    }

    public String get_canal() {
        return _canal;
    }

    public void set_canal(String _canal) {
        this._canal = _canal;
    }

    public String get_tipo() {
        return _tipo;
    }

    public void set_tipo(String _tipo) {
        this._tipo = _tipo;
    }
}
