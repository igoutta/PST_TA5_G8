package com.example.amst8;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private EditText usn, pw;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        usn = findViewById(R.id.editTextName);
        pw = findViewById(R.id.editTextPassword);
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,
                "bookstore", null, 1);
        db = admin.getReadableDatabase();
    }

    public void categorias(View view) {
        Intent i = new Intent(this, Categorias.class);
        startActivity(i);
        finish();
    }

    public void categoriaFiccion(View view) {
        if (dbHasData(usn.getText().toString(), pw.getText().toString())) {
            Intent i = new Intent(this, Ficcion_Categoria.class);
            //i.putExtra("INFO", consulta.getText().toString());
            //i.putExtra("direccion", et1.getText().toString());
            startActivity(i);
            finish();
        }
    }

    public boolean dbHasData(String searchKey, String searchKey2) {
        String query = "Select * from userdata where username = ? and password = ?";
        return db.rawQuery(query, new String[]{searchKey, searchKey2}).moveToFirst();
    }

}