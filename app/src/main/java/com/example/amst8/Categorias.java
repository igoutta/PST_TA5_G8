package com.example.amst8;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;


public class Categorias extends AppCompatActivity  {
    public static final String EXTRA_MESSAGE = "com.example.amst8.CATEGORIA";
    private LinearLayout container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categorias);
        container = findViewById(R.id.containerTags);

        crearCategorias();
    }

    public void crearCategorias() {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "bookstore", null, 1);
        final SQLiteDatabase db = admin.getReadableDatabase();
        final String query = "select * from categoria";
        new Thread(new Runnable(){

            @Override
            public void run() {
                Cursor fila = db.rawQuery(query, null);
                if (fila.moveToFirst()) {
                    do {
                        final LinearLayout item = new LinearLayout(getApplicationContext());
                        item.setOrientation(LinearLayout.VERTICAL);
                        item.setGravity(Gravity.CENTER_VERTICAL);

                        ImageView toShow = new ImageView(getApplicationContext());
                        String src = fila.getString(2);
                        toShow.setImageResource(getResources().getIdentifier(src,"mipmap",getPackageName()));
                        toShow.setMinimumHeight(168);
                        toShow.setMaxHeight(168);
                        toShow.setAdjustViewBounds(true);
                        toShow.setPadding(8,8,8,8);
                        item.addView(toShow);
                        final String value = Integer.toString(fila.getInt(0));

                        Button btn = new Button(getApplicationContext());
                        btn.setBackground(getResources().getDrawable(R.drawable.button_style));
                        btn.setText(fila.getString(1)+value);
                        item.addView(btn);

                        container.post(new Runnable() {
                            @Override
                            public void run() {
                                container.addView(item);

                                item.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(final View v) {
                                        db.close();
                                        Intent i = new Intent(getApplicationContext(), Main.class);
                                        i.putExtra(EXTRA_MESSAGE, value);
                                        startActivity(i);
                                        finish();
                                    }
                                });
                            }
                        });
                    }while (fila.moveToNext());
                }
                fila.close();
            }
        }).start();
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

}