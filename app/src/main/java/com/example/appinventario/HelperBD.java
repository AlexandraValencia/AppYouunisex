package com.example.appinventario;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Clase encargada de conectar la BD SQLite y habilitar la BD para ser Modificada y/o Leída
 * extiende de la clase SQLiteOpenHelper
 */
public class HelperBD extends SQLiteOpenHelper {
    //Declaración de constantes que contienen información de la BD a la que se realiza la conexión
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Market.db";

    /**
     * Contructor por defecto
     * @param context Parametro por defecto
     */
    public HelperBD(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * Método encargado de crear la base de datos llamando una constante de la clase EstructuraBD
     * @param db Objeto de la clase SQLiteDatabase
     */
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(EstructuraBD.SQL_CREATE_ENTRIES);
    }//Fin onCreate()

    /**
     * Método encargado de eliminar la tabla especificada en la constante de la clase EstructuraBD a modo de actualización
     * @param db Objeto de la clase SQLiteDatabase
     * @param oldVersion Id de la versión de la BD anterior
     * @param newVersion Id de la versión de la BD nueva
     */
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(EstructuraBD.SQL_DELETE_ENTRIES);
        onCreate(db);
    }//Fin onUpgrade()

    /**
     * Método encargado de eliminar la tabla especificada en la constante de la clase EstructuraBD a modo de desactualización
     * @param db Objeto de la clase SQLiteDatabase
     * @param oldVersion Id de la versión de la BD anterior
     * @param newVersion Id de la versión de la BD nueva
     */
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }//Fin onDowngrade()
}//Fin Class
