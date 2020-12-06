package com.example.appinventario.EDIT;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.appinventario.MAIN_OPTIONS.vendedores;
import com.example.appinventario.R;
import com.example.appinventario.TABLAS.ConexionSQLiteHelper;
import com.example.appinventario.TABLAS.tab_vendedor;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class edit_vendedores extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    ImageView back,edit;
    EditText n,r,t,em,d;
    FloatingActionButton y;
    int iterator;
    String nombre,rif2,rifi,direccion,telefono,correo;
    int band_edit=0;
    int grade;
    ConexionSQLiteHelper conn=new ConexionSQLiteHelper(this, tab_vendedor.TABLA_VENDEDORES,null,1);
    @Override
    public void onBackPressed(){
        backf();
    }
    private void backf(){
        Intent intent = new Intent(getApplicationContext(), vendedores.class);
        intent.putExtra("grade",grade);
        startActivityForResult(intent,1);
        finish();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_vendedores);
        grade = Integer.parseInt(getIntent().getExtras().get("grade").toString());
        iterator = Integer.parseInt(getIntent().getExtras().get("iterator").toString());
        n= (EditText) findViewById(R.id.nombre);
        r= (EditText) findViewById(R.id.rif2);
        t= (EditText) findViewById(R.id.telefono);
        em= (EditText) findViewById(R.id.email);
        d= (EditText) findViewById(R.id.direccion);
        ReadData();
        n.setEnabled(false);
        r.setEnabled(false);
        t.setEnabled(false);
        em.setEnabled(false);
        d.setEnabled(false);
        n.setText(nombre);
        r.setText(rif2);
        t.setText(telefono);
        em.setText(correo);
        d.setText(direccion);
        d.setText(direccion);

        back = (ImageView) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                onBackPressed();
            }
        });
        y = (FloatingActionButton) findViewById(R.id.b_yes);
        edit = (ImageView) findViewById(R.id.edit);
        edit.setOnClickListener(new View.OnClickListener(){
            @SuppressLint("RestrictedApi")
            @Override
            public void onClick(View view){
                if(band_edit==0){
                    band_edit=1;
                    n.setEnabled(true);
                    r.setEnabled(true);
                    t.setEnabled(true);
                    em.setEnabled(true);
                    d.setEnabled(true);
                    y.setVisibility(View.VISIBLE);
                    edit.setImageResource(R.mipmap.ic_x);
                }else{
                    promptDialogedit();
                }
            }
        });
        y.setOnClickListener(new View.OnClickListener(){
            @SuppressLint("RestrictedApi")
            @Override
            public void onClick(View view){
                //editarrrrrrrrrrrrrrrrrrrrrr
                if (isStr(n.getText().toString().toUpperCase())){
                    if (isInt(r.getText().toString()) && r.getText().toString().length()==5){
                        if (isStr(d.getText().toString())){
                            promptDialogyes();
                            Toast.makeText(getApplicationContext(),"Registrado con exito", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(getApplicationContext(),"Direccion esta vacio", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(getApplicationContext(),"Error en Codigo", Toast.LENGTH_SHORT).show();
                    }

                }else{
                    Toast.makeText(getApplicationContext(),"Nombre esta vacio", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private boolean isInt(String numero){
        try {
            int num = Integer.parseInt(numero);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    private boolean isStr(String ed_text){
        if(ed_text.isEmpty() || ed_text.length() == 0 || ed_text.equals("") || ed_text == null)
        {
            return false;
        }
        else
        {
            return true;
        }
    }
    private  void ReadData() {
        SQLiteDatabase db=conn.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM "+ tab_vendedor.TABLA_VENDEDORES+" ORDER BY " + tab_vendedor.CAMPO_NOMBRE + " asc",null);
        c.moveToPosition(iterator);
        nombre = c.getString(1).toUpperCase();
        rif2 = c.getString(2).toString();
        direccion = c.getString(3);
        telefono=c.getString(4);
        correo=c.getString(5);
        db.close();
    }
    private void registrar() {
        ConexionSQLiteHelper conn=new ConexionSQLiteHelper(this, tab_vendedor.TABLA_VENDEDORES,null,1);
        SQLiteDatabase db=conn.getWritableDatabase();
        ContentValues values=new ContentValues();
        Cursor c = db.rawQuery("SELECT "+ tab_vendedor.ID_VENDEDORES+" FROM "+ tab_vendedor.TABLA_VENDEDORES+" ORDER BY " + tab_vendedor.ID_VENDEDORES+ " asc",null);
        String Row="0";
        if (checkdb(c)) {
            c.moveToLast();
            Row=""+(Integer.parseInt(c.getString(0))+1);
        }
        c.close();
        values.put(tab_vendedor.ID_VENDEDORES,Row);
        values.put(tab_vendedor.CAMPO_NOMBRE,n.getText().toString().toUpperCase());
        values.put(tab_vendedor.CAMPO_RIF,r.getText().toString());
        values.put(tab_vendedor.CAMPO_DIRECCION,d.getText().toString());
        values.put(tab_vendedor.CAMPO_TELEFONO,t.getText().toString());
        values.put(tab_vendedor.CAMPO_EMAIL,em.getText().toString());
        db.insert(tab_vendedor.TABLA_VENDEDORES, tab_vendedor.ID_VENDEDORES,values);
        c = db.rawQuery("SELECT * FROM "+ tab_vendedor.TABLA_VENDEDORES,null);
        c.moveToFirst();
        c.close();
        db.close();
    }
    public Boolean checkdb(Cursor c){
        Boolean rowExists;

        if (c.moveToFirst())
        {
            // DO SOMETHING WITH CURSOR
            rowExists = true;

        } else
        {
            // I AM EMPTY
            rowExists = false;
        }
        return rowExists;
    }
    private void promptDialogedit() {
        final EditText edtText = new EditText(this);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Eliminar");
        builder.setMessage("¿Eliminar de la lista de clientes?");
        builder.setCancelable(false);
        builder.setPositiveButton("Si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                SQLiteDatabase db=conn.getWritableDatabase();
                db.delete(tab_vendedor.TABLA_VENDEDORES, tab_vendedor.CAMPO_NOMBRE + "='" + nombre+"'", null);
                db.close();
                Toast.makeText(getApplicationContext(),n.getText().toString().toUpperCase() + " fue eliminado de los vendedores", Toast.LENGTH_SHORT).show();
                backf();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        builder.show();
    }
    private void promptDialogyes() {
        final EditText edtText = new EditText(this);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Modificar");
        builder.setMessage("¿Modificar el vendedor?");
        builder.setCancelable(false);
        builder.setPositiveButton("Si", new DialogInterface.OnClickListener() {
            @SuppressLint("RestrictedApi")
            @Override
            public void onClick(DialogInterface dialog, int which) {
                band_edit=0;
                SQLiteDatabase db=conn.getReadableDatabase();
                db.delete(tab_vendedor.TABLA_VENDEDORES, tab_vendedor.CAMPO_NOMBRE + "='" + nombre+"'", null);
                registrar();
                n.setEnabled(false);
                r.setEnabled(false);
                d.setEnabled(false);
                y.setVisibility(View.INVISIBLE);
                edit.setImageResource(R.mipmap.ic_edit);
                Toast.makeText(getApplicationContext(),"Cambio registrado con exito", Toast.LENGTH_SHORT).show();
                backf();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        builder.show();
    }
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }
    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
