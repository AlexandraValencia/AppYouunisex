package com.example.appinventario.TABLAS;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class ConexionSQLiteHelper extends SQLiteOpenHelper {

    static String tblAmigos = "CREATE TABLE amigos(idAmigo integer primary key autoincrement, nombre text, telefono text, direccion text, email text, url text)";

    public ConexionSQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(tblAmigos);
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

    public Cursor mantenimientoAmigos(String accion, String[] data){
        SQLiteDatabase sqLiteDatabaseReadable=getReadableDatabase();
        SQLiteDatabase sqLiteDatabaseWritable=getWritableDatabase();
        Cursor cursor = null;
        switch (accion){
            case "consultar":
                cursor=sqLiteDatabaseReadable.rawQuery("SELECT * FROM amigos ORDER BY nombre ASC", null);
                break;
            case "nuevo":
                sqLiteDatabaseWritable.execSQL("INSERT INTO amigos (nombre,telefono,direccion,email,url) VALUES('"+ data[1] +"','"+data[2]+"','"+data[3]+"','"+data[4]+"','"+data[5]+"')");
                break;
            case "modificar":
                sqLiteDatabaseWritable.execSQL("UPDATE amigos SET nombre='"+ data[1] +"',telefono='"+data[2]+"',direccion='"+data[3]+"',email='"+data[4]+"', url='"+data[5]+"' WHERE idAmigo='"+data[0]+"'");
                break;
            case "eliminar":
                sqLiteDatabaseWritable.execSQL("DELETE FROM amigos WHERE idAmigo='"+ data[0] +"'");
                break;
        }
        return cursor;
    }
}