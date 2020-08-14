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
        db.execSQL("create table categoria(id integer primary key autoincrement, descripcion text)");
        db.execSQL("insert into categoria(id, descripcion) values (null, 'Ficción')");

        //db.execSQL("create table categoria(id integer primary key autoincrement, descripcion text)");
        db.execSQL("insert into categoria(id, descripcion) values (null, 'Terror')");

        db.execSQL("create table userdata(codigo varchar(3) not null primary key, username text unique not null, password text not null," +
                "name text not null, apellidos text not null, correo text not null, celular varchar(10), " +
                "favcategoria integer not null, foreign key (favcategoria) references categoria(id))");
        db.execSQL("insert into userdata values ('001', 'admin', 'admin', 'Guillermo', 'Castillo'," +
                "'guancast@gmail.com','0999981247', 1)");

        db.execSQL("create table libro(isbn varchar(4) unique not null primary key," +
                "titulo text not null, autor text not null, publicacion varchar(4)," +
                "descripcion text not null, imgsrc text not null," +
                "idcategoria integer not null, foreign key (idcategoria) references categoria(id))");
        db.execSQL("insert into libro values ('0001', 'Los Juegos de Ender', 'Orson Scott Card','1985'," +
                "'Aqui va la descripcion'," +
                "'juego_de_ender', 1)");
        db.execSQL("insert into libro values ('0002', 'Harry Potter y la Orden del Fénix', 'J.K. Rowling','2003'," +
                "'En su quinto año en Hogwarts, Harry descubre que muchos integrantes de la comunidad de magos no conocen la verdad acerca de su encuentro con Lord Voldemort. Cornelius Fudge, ministro de magia, designa a Dolores Umbridge como maestra de defensa contra de las artes oscuras porque cree que el profesor Dumbledore planea apoderarse de su trabajo. Pero sus enseñanzas son inadecuadas, por lo que Harry prepara a los estudiantes para defender la escuela en contra del mal.'," +
                "'harry_potter', 1)");
        db.execSQL("insert into libro values ('0003', 'Percy Jackson y El Ladrón del Rayo', 'Rick Riordan','2005'," +
                "'El ladrón del rayo es un libro escrito por Rick Riordan. Es el primer libro de la serie Percy Jackson y los dioses del Olimpo, que cuenta las aventuras de un chico actual de doce años, Percy Jackson, cuando descubre que es un semidiós; el hijo de una mortal, Sally Jackson, y del dios griego, Poseidón.'," +
                "'percy_jackson', 1)");
        db.execSQL("insert into libro values ('0004', 'Los Juegos del Hambre: Sinsajo', 'Suzanne Collins','2010'," +
                "'Sinsajo es una novela de ciencia ficción distópica para jóvenes adultos de 2010 y el tercer libro de la trilogía de Los juegos del hambre, de la autora Suzanne Collins. Después de destruir los juegos para siempre, Katniss llega al Distrito 13 para salvar a Peeta y una nación conmovida por su coraje.'," +
                "'juegos_del_hambre', 1)");


        db.execSQL("create table libro(isbn varchar(4) unique not null primary key," +
                "titulo text not null, autor text not null, publicacion varchar(4)," +
                "descripcion text not null, imgsrc text not null," +
                "idcategoria integer not null, foreign key (idcategoria) references categoria(id))");
        db.execSQL("insert into libro values ('0005', 'IT', 'Stephen King','1986'," +
                "'Cuenta la historia de un grupo de siete niños que son aterrorizados por un malvado monstruo -al que llaman «Eso»- que es capaz de cambiar de forma, alimentándose del terror que produce en sus víctimas.'," +
                "'IT_image', 1)");
        db.execSQL("insert into libro values ('0006', 'Dracula', 'Bram Stoker','1897'," +
                "'Cuando Jonathan Harker viajó a Transilvania por asuntos de negocios, nunca imaginó el peligro que corría. Su cliente, el magnético conde Drácula, lo mantiene prisionero y Harker adivina sus oscuros propósitos.'," +
                "'Dracula_image', 1)");
        db.execSQL("insert into libro values ('0007', 'El gato negro', 'Edgar Allan Poe','1843'," +
                "'Un joven matrimonio lleva una vida hogareña apacible en compañía de varios animales domésticos, entre ellos un misterioso gato negro. Todo cambia cuando el marido empieza a dejarse arrastrar por la bebida, y en uno de sus accesos de furia acaba con la vida del animal.'," +
                "'GatoNegro_image', 1)");
        db.execSQL("insert into libro values ('0008', 'El traje del muerto', 'Joe Hill','2007'," +
                "'Jude Coyne es una estrella de rock, al estilo de Marilyn Manson, ya retirada. Rizando el rizo, decide pujar por un fantasma que se subasta por Internet y, días después, recibe una extraña caja en forma de corazón que contiene el traje del muerto.Jude Coyne es una estrella de rock, al estilo de Marilyn Manson, ya retirada. Rizando el rizo, decide pujar por un fantasma que se subasta por Internet y, días después, recibe una extraña caja en forma de corazón que contiene el traje del muerto.'," +
                "'TrajeMuerto_image', 1)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
