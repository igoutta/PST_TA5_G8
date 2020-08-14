package com.example.amst8;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Login extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.amst8.USUARIO";
    private EditText usn, pw;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        usn = findViewById(R.id.editTextName);
        pw = findViewById(R.id.editTextPassword);

        this.deleteDatabase("bookstore");
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "bookstore", null, 1);
        db = admin.getReadableDatabase();
    }

    public void ingresar(View view) {
        String u = usn.getText().toString().trim();
        String p = pw.getText().toString().trim();
        if (dbHasData(u, p)) {
            db.close();
            Intent i = new Intent(this, Main.class);
            i.putExtra(EXTRA_MESSAGE, u);
            startActivity(i);
            finish();
        }
    }

    public boolean dbHasData(String searchKey, String searchKey2) {
        String query = "Select * from userdata where username = ? and password = ?";
        return db.rawQuery(query, new String[]{searchKey, searchKey2}).moveToFirst();
    }

}