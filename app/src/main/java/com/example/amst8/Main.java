package com.example.amst8;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Main extends AppCompatActivity {
    private LinearLayout container;
    private EditText et;
    private SQLiteDatabase db;
    private String select_query = "select * from libro";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et = findViewById(R.id.et);
        container = findViewById(R.id.containerBooks);

        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "bookstore", null, 1);
        db = admin.getReadableDatabase();

        Intent cond = getIntent();
        if(cond.hasExtra(Categorias.EXTRA_MESSAGE)) {
            select_query.concat(" where idcategoria = ").concat(cond.getStringExtra(Categorias.EXTRA_MESSAGE));
        }
        crearLista(select_query);
    }

    public void crearLista(final String query) {
        container.removeAllViewsInLayout();
        new Thread(new Runnable(){

            @Override
            public void run() {
                Cursor fila = db.rawQuery(query, null);
                if (fila.moveToFirst()) {
                    do {
                        final LinearLayout row = new LinearLayout(getApplicationContext());
                        row.setOrientation(LinearLayout.HORIZONTAL);
                        row.setLayoutDirection(LinearLayout.LAYOUT_DIRECTION_LTR);
                        row.setGravity(Gravity.CENTER_VERTICAL);

                        ImageView toShow = new ImageView(getApplicationContext());
                        String src = fila.getString(5);
                        toShow.setImageResource(getResources().getIdentifier(src,"mipmap",getPackageName()));
                        toShow.setMinimumWidth(168);
                        toShow.setMinimumHeight(168);
                        toShow.setPaddingRelative(8,8,8,8);
                        row.addView(toShow);

                        LinearLayout list = new LinearLayout(getApplicationContext());
                        list.setOrientation(LinearLayout.VERTICAL);

                        String[] args = new String[]{"TÍTULO: ", "AUTOR: ", "AÑO DE PUBLICACIÓN: "};
                        for (int x = 1; x<4; x++) {
                            TextView l = new TextView(getApplicationContext());
                            l.setTextSize(16);
                            l.setTextColor(Color.GRAY);
                            String data = fila.getString(x);
                            if(fila.getString(x).isEmpty()){
                                data = "Desconocido";
                            }
                            l.setText(args[x-1].concat(data));
                            list.addView(l);
                        }

                        row.addView(list);
                        container.post(new Runnable() {
                            @Override
                            public void run() {
                                container.addView(row);
                            }
                        }

                        );
                    }while (fila.moveToNext());
                }
                fila.close();
            }
        }).start();
    }

    public void buscar(View view) {
        String title = et.getText().toString().trim();
        et.setText("");
        if(!title.isEmpty()) {
            crearLista(select_query.concat(" where titulo = '").concat(title).concat("'"));
        }
    }

    public void verPerfil(View view) {
        db.close();
        Intent i = new Intent(this, Perfil_Usuario.class);
        i.putExtra(Login.EXTRA_MESSAGE, getIntent().getStringExtra(Login.EXTRA_MESSAGE));
        startActivity(i);
    }

}