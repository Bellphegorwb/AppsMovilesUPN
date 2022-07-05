package com.stefanot.vetonit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.stefanot.vetonit.entidad.Cita;
import com.stefanot.vetonit.modelo.DAOCita;

import java.util.ArrayList;
import java.util.List;

public class Home extends AppCompatActivity {

    RecyclerView rvCitas;
    FloatingActionButton btnNuevo;
    ImageButton btnSedes,btnLogoOut;

    List<Cita> listaCita = new ArrayList<>();
    AdaptadorPersonalizado adaptadorPersonalizado;
    String mensaje;
    DAOCita daoLibro=new DAOCita(Home.this);
    int elimiminar=-1;

    MediaPlayer clic1;
    MediaPlayer salir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        asignarReferencias();
        mostarCita();
        clic1 =MediaPlayer.create(this, R.raw.clic2);
        salir =MediaPlayer.create(this, R.raw.salir);
    }
    private void asignarReferencias() {
        rvCitas = findViewById(R.id.rvCitas);
        btnNuevo = findViewById(R.id.btnNuevo);
        btnSedes=findViewById(R.id.btnSedes);
        btnLogoOut = findViewById(R.id.btnLogout);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        btnNuevo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this, MantenimientoCitas.class);
                salir.start();
                startActivity(intent);
            }
        });
        btnSedes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this, SedesActivity.class);
                salir.start();
                startActivity(intent);
            }
        });

        btnLogoOut.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                AlertDialog.Builder ventana= new AlertDialog.Builder(Home.this);
                ventana.setTitle("Mensaje informativo");
                ventana.setMessage("Desea cerrar sesión?");
                ventana.setNegativeButton("Cancelar",null);
                ventana.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        FirebaseAuth.getInstance().signOut();
                        Intent intent = new Intent(Home.this, MainActivity.class);
                        salir.start();
                        startActivity(intent);
                        finish();
                    }
                });
                ventana.create().show();
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
                        clic1.start();
                        elimiminar=0;
                        listaCita.add(pos,delCita);
                        daoLibro.registrarCita(delCita);
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