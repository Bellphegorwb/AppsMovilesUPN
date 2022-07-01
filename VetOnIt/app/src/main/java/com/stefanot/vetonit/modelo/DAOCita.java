package com.stefanot.vetonit.modelo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.stefanot.vetonit.entidad.Cita;
import com.stefanot.vetonit.util.CitaBD;

import java.util.ArrayList;
import java.util.List;

public class DAOCita {
    CitaBD citaBD;
    SQLiteDatabase db;
    Context context;
    public DAOCita(Context con){
        this.context=con;
        citaBD=new CitaBD(con);
    }

    public void abrirDB(){
        db=citaBD.getWritableDatabase();
    }

    public String registrarCita(Cita objCita){
        String respuesta="";
        try {
            ContentValues values= new ContentValues();
            values.put("especie",objCita.getEspecie());
            values.put("raza",objCita.getRaza());
            values.put("nombre",objCita.getNombre());
            values.put("servicio",objCita.getServicio());
            values.put("fecha",objCita.getFecha());
            values.put("hora",objCita.getHora());

            long rpta=db.insert("CITA",null, values);
            if (rpta==-1){
                respuesta="Error al registrar";
            }
            else {
                respuesta="Se registró correctamente";
            }

        }catch (Exception exception){
            Log.d("===>",exception.getMessage());

        }
        return respuesta;
    }
    public String modificarCita(Cita objCita){
        String respuesta="";
        try {
            ContentValues values= new ContentValues();
            values.put("especie",objCita.getEspecie());
            values.put("raza",objCita.getRaza());
            values.put("nombre",objCita.getNombre());
            values.put("servicio",objCita.getServicio());
            values.put("fecha",objCita.getFecha());
            values.put("hora",objCita.getHora());

            long rpta=db.update("CITA",values,"id="+objCita.getId(),null);
            if (rpta==-1){
                respuesta="Error al editar";
            }
            else {
                respuesta="Se editó correctamente";
            }

        }catch (Exception exception){
            Log.d("===>",exception.getMessage());

        }
        return respuesta;
    }

    public String eliminarCita(int id){
        String respuesta="";
        try {
            long rpta=db.delete("CITA","id="+id,null);
            if (rpta==-1){
                respuesta="Error al eliminar";
            }
            else {
                respuesta="Se eliminó correctamente";
            }

        }catch (Exception exception){
            Log.d("===>",exception.getMessage());

        }
        return respuesta;
    }
    public List<Cita> cargarCita(){
        List<Cita> listaCita=new ArrayList<>();
        try {
            Cursor c = db.rawQuery("SELECT * FROM CITA", null);
            while(c.moveToNext()){
                listaCita.add(new Cita(c.getInt(0),c.getString(1),c.getString(2),c.getString(3),c.getString(4),c.getString(5),c.getString(6)));
            }
        }catch (Exception exception){
            Log.d("===>",exception.getMessage());

        }
        return listaCita;
    }
}
