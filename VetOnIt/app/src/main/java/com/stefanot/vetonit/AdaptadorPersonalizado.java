package com.stefanot.vetonit;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.stefanot.vetonit.entidad.Cita;
import com.stefanot.vetonit.modelo.DAOCita;

import java.util.ArrayList;
import java.util.List;

public class AdaptadorPersonalizado extends RecyclerView.Adapter<AdaptadorPersonalizado.MiViewHolder> {
    private Context context;
    private List<Cita> listaCita=new ArrayList<>();
    private boolean completado=false;

    public AdaptadorPersonalizado(Context context, List<Cita> listaCita) {
        this.context = context;
        this.listaCita = listaCita;
    }

    @NonNull
    @Override
    public MiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.fila, parent,false);
        return new MiViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MiViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.nombre.setText(listaCita.get(position).getNombre()+"");
        holder.fyh.setText(listaCita.get(position).getFecha()+" "+listaCita.get(position).getHora());
        holder.editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, MantenimientoCitas.class);
                intent.putExtra("paramId",listaCita.get(position).getId()+"");
                intent.putExtra("paramEspecie",listaCita.get(position).getEspecie()+"");
                intent.putExtra("paramRaza",listaCita.get(position).getRaza()+"");
                intent.putExtra("paramNombrePet",listaCita.get(position).getNombre()+"");
                intent.putExtra("paramServicio",listaCita.get(position).getServicio()+"");
                intent.putExtra("paramFecha",listaCita.get(position).getFecha()+"");
                intent.putExtra("paramHora",listaCita.get(position).getHora()+"");
                context.startActivity(intent);
            }
        });
        holder.eliminar.setOnClickListener(new View.OnClickListener() {
            String mensaje;
            @Override
            public void onClick(View v) {
                AlertDialog.Builder ventana= new AlertDialog.Builder(context);
                ventana.setTitle("Confirmar eliminar");
                ventana.setMessage("Desea eliminar la cita para"+listaCita.get(position).getNombre()+"");
                ventana.setNegativeButton("No",null);
                ventana.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DAOCita daoLibro=new DAOCita(context);
                        daoLibro.abrirDB();
                        mensaje=daoLibro.eliminarCita(listaCita.get(position).getId());


                        AlertDialog.Builder ventana2= new AlertDialog.Builder(context);
                        ventana2.setTitle("Mensaje informativo");
                        ventana2.setMessage(mensaje);
                        ventana2.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(context, Home.class);
                                context.startActivity(intent);
                            }
                        });
                        ventana2.create().show();
                    }
                });
                ventana.create().show();

            }
        });
        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                completado=!completado;
                if (completado){
                    holder.checkBox.setBackgroundResource(R.drawable.ic_checked);
                }
                else {
                    holder.checkBox.setBackgroundResource(R.drawable.ic_unchecked);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return listaCita.size();
    }

    public class MiViewHolder extends RecyclerView.ViewHolder{
        TextView nombre, fyh;
        ImageButton eliminar;
        Button checkBox,editar;
        public MiViewHolder(@NonNull View itemView) {
            super(itemView);
            nombre = itemView.findViewById(R.id.nombre);
            fyh = itemView.findViewById(R.id.fyh);
            checkBox = itemView.findViewById(R.id.checkBox);
            eliminar = itemView.findViewById(R.id.eliminar);
            editar = itemView.findViewById(R.id.editar);


        }
    }
}
