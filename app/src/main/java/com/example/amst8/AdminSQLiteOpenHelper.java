package com.example.amst8;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class AdminSQLiteOpenHelper extends SQLiteOpenHelper {

    public AdminSQLiteOpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table categoria(id int primary key autoincrement, descripcion text)");
        db.execSQL("insert into categoria(id, descripcion) values (1, 'Ficción')");

        db.execSQL("create table userdata(codigo varchar(3) not null primary key, username text unique not null, password text not null," +
                "name text not null, apellidos text not null, correo text not null," +
                "celular varchar(10), foreign key (favcategoria) references categoria(id))");
        db.execSQL("insert into userdata values ('001', 'admin', 'admin', 'Guillermo', 'Castillo'," +
                "'guancas@gmail.com','0999981247', 1)");

        db.execSQL("create table libro(isbn varchar(4) unique not null primary key, " +
                "titulo text not null, autor text not null, publicacion varchar(4), " +
                "descripcion text not null, imgsrc text not null, foreign key (favcategoria) references categoria(id))");
        db.execSQL("insert into libro values ('0001', 'Los Juegos de Ender', 'Orson Scott Card','1985'," +
                "'Aqui va la descripcion'," +
                "'juego_de_ender.jpg', 1)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
