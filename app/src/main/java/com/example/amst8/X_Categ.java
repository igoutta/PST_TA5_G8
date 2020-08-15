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
import android.widget.Toast;

public class X_Categ extends AppCompatActivity {
    private SQLiteDatabase db;
    private String query = "SELECT * from libro";
    private LinearLayout container;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_x__categ);

        container = findViewById(R.id.containerBooks);
        TextView label = (TextView) findViewById(R.id.textViewCateg);
        Bundle datos = this.getIntent().getExtras();
        int Xcateg = datos.getInt("CATEG");

        if(Xcateg==1){
            label.setText("Categoria Ficcion");
        }else if(Xcateg==2){
            label.setText("Categoria Terror");
        }else if(Xcateg==3){
            label.setText("Categoria Aventura");
        }else if(Xcateg==4){
            label.setText("Categoria Comics");
        }

        query.concat(" where idcategoria=").concat(""+Xcateg);
        //query.concat(" where titulo=").concat("Los Juegos de Ender");

        setContentView(R.layout.activity_x__categ);
        //et = findViewById(R.id.et);
        container = findViewById(R.id.containerBooks);

        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "bookstore", null, 1);
        db = admin.getReadableDatabase();

        Intent cond = getIntent();
        if(cond.hasExtra(Categorias.EXTRA_MESSAGE)) {
            query.concat(" where idcategoria = ").concat(cond.getStringExtra(Categorias.EXTRA_MESSAGE));
        }
        crearLista(query);




    }

    public void inicio(View view) {
        Intent i = new Intent(this, Main.class );
        startActivity(i);
        finish();
    }

    public void categoria(View v) {
        Intent i = new Intent(this, Categorias.class );
        startActivity(i);
        finish();
    }

    public void verPerfil(View view) {
        Intent i = new Intent(this, Perfil_Usuario.class);
        i.putExtra(Login.EXTRA_MESSAGE, getIntent().getStringExtra(Login.EXTRA_MESSAGE));
        startActivity(i);
    }

    public void crearLista(final String query) {
        container.removeAllViewsInLayout();
        new Thread(new Runnable(){

            @Override
            public void run() {
                Cursor fila = db.rawQuery(query, null);

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

                fila.close();
            }
        }).start();
    }


}