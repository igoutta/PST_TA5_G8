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

    //falta funcion ver perfil del menú
}