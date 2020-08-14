package com.example.amst8;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class Categorias extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.amst8.CATEGORIA";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categorias);
    }
}