package com.example.appinventario.MAIN_OPTIONS;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.appinventario.ADD.add_vendedores;
import com.example.appinventario.EDIT.edit_vendedores;
import com.example.appinventario.R;
import com.example.appinventario.TABLAS.ConexionSQLiteHelper;
import com.example.appinventario.TABLAS.tab_vendedor;
import com.example.appinventario.menu;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class vendedores extends AppCompatActivity {
    ImageView back;
    FloatingActionButton add;
    ListView lista;
    ConexionSQLiteHelper conn=new ConexionSQLiteHelper(this, tab_vendedor.TABLA_VENDEDORES,null,1);
    ArrayAdapter<String> adapter;
    int grade;
    @Override
    public void onBackPressed(){
        backf();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendedores);
        grade = Integer.parseInt(getIntent().getExtras().get("grade").toString());
        lista= (ListView) findViewById(R.id.lista_contenido);
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(grade<3) {
                    Intent intent = new Intent(getApplicationContext(), edit_vendedores.class);
                    intent.putExtra("iterator", i);
                    intent.putExtra("grade", grade);
                    startActivityForResult(intent, 2);
                    finish();
                }else{
                    Toast.makeText(getApplicationContext(),"Nivel de usuario insuficiente", Toast.LENGTH_SHORT).show();
                }
            }
        });
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1);
        back = (ImageView) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                onBackPressed();
            }
        });
        add = (FloatingActionButton) findViewById(R.id.b_add);
        add.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if(grade<3) {
                    Intent intent = new Intent(getApplicationContext(), add_vendedores.class);
                    intent.putExtra("grade", grade);
                    startActivityForResult(intent, 2);
                    finish();
                }else{
                    Toast.makeText(getApplicationContext(),"Nivel de usuario insuficiente", Toast.LENGTH_SHORT).show();
                }
            }
        });
        if(grade==2){
            add.hide();
        }
        fillData();
    }
    private void backf(){
        Intent intent = new Intent(getApplicationContext(), menu.class);
        intent.putExtra("grade",grade);
        startActivityForResult(intent,0);
        finish();
    }
    private  void fillData() {
        SQLiteDatabase db=conn.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM "+ tab_vendedor.TABLA_VENDEDORES+" ORDER BY " + tab_vendedor.CAMPO_NOMBRE + " asc",null);
        if (checkdb(c)) {
            c.moveToFirst();
            do{
                adapter.add("\n"+c.getString(1) + "\nTelefono: "+ c.getString(4)+"\nCorreo: "+ c.getString(5)
                +"\nDireccion:"+c.getString(3)+"\n");
            }while(c.moveToNext());
            lista.setAdapter(adapter);
            db.close();
        }
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
}
