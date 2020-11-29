package com.example.appinventario.TABLAS;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class ConexionSQLiteHelper extends SQLiteOpenHelper {

    public ConexionSQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(tab_user.CREAR_TABLA_USUARIOS);
        db.execSQL(tab_client.CREAR_TABLA_CLIENTE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int versionAntigua, int versionNueva) {
        db.execSQL("DROP TABLE IF EXISTS "+ tab_client.TABLA_CLIENTE);
        onCreate(db);
        db.execSQL("DROP TABLE IF EXISTS "+ tab_user.TABLA_USUARIOS);
        onCreate(db);
    }

}