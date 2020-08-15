package com.example.amst8;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

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

        String inicial = select_query;
        Intent cond = getIntent();
        if(cond.hasExtra(Categorias.EXTRA_MESSAGE)) {
            inicial = select_query.concat(" where idcategoria = ").concat(cond.getStringExtra(Categorias.EXTRA_MESSAGE));
        }

        crearLista(inicial);
        buscar();
        //crearDialog();
    }

    public void crearLista(final String query) {
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
                        toShow.setMinimumHeight(168);
                        toShow.setMaxHeight(168);
                        toShow.setAdjustViewBounds(true);
                        toShow.setPadding(8,8,8,8);
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

                        final String desc = fila.getString(4);
                        container.post(new Runnable() {
                                           @Override
                                           public void run() {
                                               container.addView(row);
                                               row.setOnClickListener(new View.OnClickListener() {

                                                   @Override
                                                   public void onClick(final View v) {
                                                   }
                                               });
                                           }
                                       }

                        );
                    }while (fila.moveToNext());
                }
                fila.close();
            }
        }).start();
    }

    public void buscar() {
        et.addTextChangedListener(new TextWatcher(){

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                container.removeAllViews();
                crearLista(select_query.concat(" where titulo like '").concat(s.toString()).concat("%'"));
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
    }

    public void crearDialog() {
        View dialogView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.custom_desc, null);
        AlertDialog alertDialog = new AlertDialog.Builder(getApplicationContext(),R.style.Theme_AppCompat_NoActionBar).create();
        alertDialog.setView(dialogView);

        TextView text = dialogView.findViewById(R.id.text_dialog);
        text.setText("Inicio");
        alertDialog.show();
    }

    public void verPerfil(View view) {
        Intent i = new Intent(this, Perfil_Usuario.class);
        Bundle datos = this.getIntent().getExtras();
        String msg1 = datos.getString(Login.EXTRA_MESSAGE);
        i.putExtra(Login.EXTRA_MESSAGE, msg1);
        //i.putExtra(Login.EXTRA_MESSAGE, getIntent().getStringExtra(Login.EXTRA_MESSAGE));
        startActivity(i);
    }

    public void verCategorias(View view) {
        Intent i = new Intent(this, Categorias.class );
        Bundle datos = this.getIntent().getExtras();
        String msg1 = datos.getString(Login.EXTRA_MESSAGE);
        i.putExtra(Login.EXTRA_MESSAGE, msg1);
        startActivity(i);
    }

    public void inicio(View view) {
        Toast.makeText(this, "Se encuentra en la sección principal", Toast.LENGTH_SHORT).show();
    }
}