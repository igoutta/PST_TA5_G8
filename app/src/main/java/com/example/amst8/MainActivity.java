package com.example.amst8;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //comentario Carlos//
        //comentario
        //finish();

    }
    public void categoria(View view){

        Intent i = new Intent(this, Ficcion_Categoria.class );
        //i.putExtra("INFO", consulta.getText().toString());
        //i.putExtra("direccion", et1.getText().toString());
        startActivity(i);
    }

}