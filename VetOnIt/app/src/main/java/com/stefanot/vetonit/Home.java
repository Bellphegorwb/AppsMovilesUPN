package com.stefanot.vetonit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.stefanot.vetonit.entidad.Cita;
import com.stefanot.vetonit.modelo.DAOCita;

import java.util.ArrayList;
import java.util.List;

public class Home extends AppCompatActivity {

    RecyclerView rvCitas;
    FloatingActionButton btnNuevo;
    ImageButton btnSedes;

    List<Cita> listaCita = new ArrayList<>();
    AdaptadorPersonalizado adaptadorPersonalizado;
    String mensaje;
    DAOCita daoLibro=new DAOCita(Home.this);
    int elimiminar=-1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        asignarReferencias();
        mostarCita();
    }
    private void asignarReferencias() {
        rvCitas = findViewById(R.id.rvCitas);
        btnNuevo = findViewById(R.id.btnNuevo);
        btnSedes=findViewById(R.id.btnSedes);
        btnNuevo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this, MantenimientoCitas.class);
                startActivity(intent);
            }
        });
        btnSedes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this, SedesActivity.class);
                startActivity(intent);
            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int pos= viewHolder.getAdapterPosition();
                Cita delCita=listaCita.get(pos);
                daoLibro.abrirDB();
                int id=listaCita.get(pos).getId();
                listaCita.remove(pos);
                daoLibro.eliminarCita(id);
                adaptadorPersonalizado.notifyDataSetChanged();
                Snackbar mySnackbar = Snackbar.make(rvCitas,
                        "La cita para "+delCita.getNombre()+" ha sido eliminada", Snackbar.LENGTH_LONG);
                mySnackbar.setAction("Deshacer", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        elimiminar=0;
                        listaCita.add(pos,delCita);
                        adaptadorPersonalizado.notifyDataSetChanged();
                    }
                });
                mySnackbar.show();
            }
        }).attachToRecyclerView(rvCitas);
    }
    private void mostarCita(){
        DAOCita daoLibro= new DAOCita(this);

        daoLibro.abrirDB();
        listaCita=daoLibro.cargarCita();
        adaptadorPersonalizado = new AdaptadorPersonalizado(this,listaCita);
        if (listaCita.size()==0) rvCitas.setBackgroundResource(R.drawable.saddog2);
        rvCitas.setAdapter(adaptadorPersonalizado);
        rvCitas.setLayoutManager(new LinearLayoutManager(Home.this));
    }
    private void esperar(int millis){
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}