package com.example.amst8;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class Categorias extends AppCompatActivity  {
    public static final String EXTRA_MESSAGE = "com.example.amst8.CATEGORIA";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categorias);
        Toast toast = Toast.makeText(this, "Se encuentra en la sección categoría", Toast.LENGTH_SHORT);
        toast.show();
    }

    public void inicio(View view) {
        Intent i = new Intent(this, Main.class );
        startActivity(i);
        finish();
    }

    public void categoria(View v) {
        Toast toast = Toast.makeText(this, "Se encuentra en la sección categoría", Toast.LENGTH_SHORT);
        toast.show();
    }

    public void ficcionbtn(View view) {
        Intent i = new Intent(this, X_Categ.class );
        i.putExtra("CATEG",1);
        startActivity(i);
        finish();
    }

    public void terrorbtn(View view) {
        Intent i = new Intent(this, X_Categ.class );
        i.putExtra("CATEG",2);
        startActivity(i);
        finish();
    }

    public void aventurabtn(View view) {
        Intent i = new Intent(this, X_Categ.class );
        i.putExtra("CATEG",3);
        startActivity(i);
        finish();
    }

    public void comicbtn(View view) {
        Intent i = new Intent(this, X_Categ.class );
        i.putExtra("CATEG",4);
        startActivity(i);
        finish();
    }

    //falta funcion ver perfil del menú
}