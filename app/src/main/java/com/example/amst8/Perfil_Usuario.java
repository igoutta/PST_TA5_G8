package com.example.amst8;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;

public class Perfil_Usuario extends AppCompatActivity {
    private TextView tvname, tvapellido, tvcorreo, tvcelular, tvfavorito;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil__usuario);
        /*AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,
                "bookstore", null, 1);
        db = admin.getReadableDatabase();
        tvname=(TextView)findViewById(R.id.tvname1);
        tvapellido=(TextView)findViewById(R.id.tvapellido1);
        tvcorreo=(TextView)findViewById(R.id.tvcorreo1);
        tvcelular=(TextView)findViewById(R.id.tvcelular1);
        tvfavorito=(TextView)findViewById(R.id.tvfavorito1);
        //String descri = et2.getText().toString();
        //String query = "Select * from userdata ";
        Cursor fila = db.rawQuery(
                "select name,apellidos,correo from userdata where codigo = 001", null);
        if (fila.moveToFirst()) {
            tvname.setText(fila.getString(0));
            tvapellido.setText(fila.getString(1));
            tvcorreo.setText(fila.getString(2));
            tvcelular.setText(fila.getString(3));
            Cursor fila1 = db.rawQuery(
                    "Select descripcion from categoria where id = '" + fila.getString(4) +"'",null );
            tvfavorito.setText(fila1.getString(0));
        }
        db.close();*/
    }
}