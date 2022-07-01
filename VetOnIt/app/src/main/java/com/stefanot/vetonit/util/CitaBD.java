package com.stefanot.vetonit.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CitaBD extends SQLiteOpenHelper {

    public CitaBD(Context context){
        super(context,"vetonit.db", null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String query=
                "CREATE TABLE CITA" +
                        " (id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "especie TEXT NOT NULL, " +
                        "raza TEXT NOT NULL, " +
                        "nombre TEXT NOT NULL, " +
                        "servicio TEXT NOT NULL, " +
                        "fecha TEXT NOT NULL, " +
                        "hora TEXT NOT NULL);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
