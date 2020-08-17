package com.example.amst8;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Perfil_Usuario extends AppCompatActivity {
    private TextView tvname, tvapellido, tvcorreo, tvcelular, tvfavorito;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil__usuario);
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,
                "bookstore", null, 1);
        SQLiteDatabase db = admin.getReadableDatabase();


        tvname= findViewById(R.id.tvname1);
        tvapellido= findViewById(R.id.tvapellido1);
        tvcorreo= findViewById(R.id.tvcorreo1);
        tvcelular= findViewById(R.id.tvcelular1);
        tvfavorito= findViewById(R.id.tvfavorito1);
        Intent con = getIntent();
        String u = con.getStringExtra(Login.EXTRA_MESSAGE);
        Cursor fila = db.rawQuery(
                "select name,apellidos,correo,celular,favcategoria from userdata where username = '"+u+"'", null);
        if(fila.moveToFirst()) {
            tvname.setText(fila.getString(0));
            tvapellido.setText(fila.getString(1));
            tvcorreo.setText(fila.getString(2));
            tvcelular.setText(fila.getString(3));
            Cursor cat = db.rawQuery(
                    "Select descripcion from categoria where id = " + fila.getString(4),null );
            if(cat.moveToFirst()) {
                tvfavorito.setText(cat.getString(0));
            }
            cat.close();
            fila.close();
        }
        db.close();
    }

    public void salir(View view) {
        Intent i = new Intent(this, Login.class );
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(i);
    }

    public void inicio(View view) {
        Intent i = new Intent(this, Main.class );
        Bundle datos = this.getIntent().getExtras();
        String msg1 = datos.getString(Login.EXTRA_MESSAGE);
        i.putExtra(Login.EXTRA_MESSAGE, msg1);
        startActivity(i);
        finish();
    }

    public void verCategorias(View v) {
        Intent i = new Intent(this, Categorias.class);
        Bundle datos = this.getIntent().getExtras();
        String msg1 = datos.getString(Login.EXTRA_MESSAGE);
        i.putExtra(Login.EXTRA_MESSAGE, msg1);
        startActivity(i);
        finish();
    }

    public void verPerfil(View view) {
        Toast toast = Toast.makeText(this, "Se encuentra en la sección perfil", Toast.LENGTH_SHORT);
        toast.show();
    }


}