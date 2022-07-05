package com.stefanot.vetonit;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.DialogFragment;

import com.stefanot.vetonit.entidad.Cita;
import com.stefanot.vetonit.modelo.DAOCita;

import java.util.Calendar;

public class MantenimientoCitas extends AppCompatActivity {

    EditText txtEspecie, txtRaza,txtNombrePet, txtFecha, txtHora, txtServicio;
    Button btnRegistrar;
    TextView lblRegistrar;
    Cita objCita;
    ConstraintLayout mant_cita;
    Animation const_anim;
    int id;

    MediaPlayer gatito1, check;

    boolean registra=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mantenimiento_citas);
        asignarReferencias();
        verficarModificar();
        seleccionarHora();
        gatito1 =MediaPlayer.create(this, R.raw.gatito1);
        check =MediaPlayer.create(this, R.raw.check);
    }

    private void asignarReferencias() {
        mant_cita=findViewById(R.id.mant_cita);
        const_anim= AnimationUtils.loadAnimation(this,R.anim.const_anim);
        mant_cita.setAnimation(const_anim);

        txtEspecie=findViewById(R.id.txtEspecie);
        txtRaza=findViewById(R.id.txtRaza);
        txtNombrePet=findViewById(R.id.txtNombrePet);
        txtFecha=(EditText) findViewById(R.id.txtFecha);
        txtHora=findViewById(R.id.txtHora);
        txtServicio=findViewById(R.id.txtServicio);

        btnRegistrar=findViewById(R.id.btnRegistrar);
        lblRegistrar=findViewById(R.id.lblRegistrar);

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(capturarDatos()){
                    String mensaje;

                    DAOCita daoCita = new DAOCita(MantenimientoCitas.this);
                    daoCita.abrirDB();
                    if (registra) mensaje=daoCita.registrarCita(objCita);
                    else mensaje=daoCita.modificarCita(objCita);
                    AlertDialog.Builder ventana= new AlertDialog.Builder(MantenimientoCitas.this);
                    ventana.setTitle("Mensaje informativo");
                    ventana.setMessage(mensaje);
                    ventana.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            gatito1.start();
                            Intent intent = new Intent(MantenimientoCitas.this, Home.class);
                            startActivity(intent);
                        }
                    });
                    ventana.create().show();
                }
            }
        });

        txtFecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check.start();
                showDatePickerDialog();
            }
        });
    }
    private boolean capturarDatos(){
        boolean valida=true;
        String especie=txtEspecie.getText().toString();
        String raza=txtRaza.getText().toString();
        String nombre=txtNombrePet.getText().toString();
        String fecha=txtFecha.getText().toString();
        String hora=txtHora.getText().toString();
        String servicio=txtServicio.getText().toString();
        if (especie.equals("")){
            txtEspecie.setError("Especie es obligatorio");
            valida=false;
        }
        if (raza.equals("")){
            txtRaza.setError("Raza es obligatorio");
            valida=false;
        }
        if (nombre.equals("")){
            txtNombrePet.setError("Nombre es obligatorio");
            valida=false;
        }
        if (fecha.equals("")){
            txtFecha.setError("Fecha es obligatorio");
            valida=false;
        }
        if (hora.equals("")){
            txtHora.setError("Hora es obligatorio");
            valida=false;
        }
        if (servicio.equals("")){
            txtServicio.setError("Servicio es obligatorio");
            valida=false;
        }

        if(valida) {
            if (registra) {
                objCita = new Cita(especie,raza, nombre, servicio,fecha,hora);
            } else {
                objCita = new Cita(id, especie,raza, nombre, servicio,fecha,hora);
            }
        }
        return valida;
    }
    private void verficarModificar(){
        if (getIntent().hasExtra("paramId")){
            registra=false;
            id=Integer.parseInt(getIntent().getStringExtra("paramId"));
            txtEspecie.setText(getIntent().getStringExtra("paramEspecie"));
            txtRaza.setText(getIntent().getStringExtra("paramRaza"));
            txtNombrePet.setText(getIntent().getStringExtra("paramNombrePet"));
            txtFecha.setText(getIntent().getStringExtra("paramFecha"));
            txtHora.setText(getIntent().getStringExtra("paramHora"));
            txtServicio.setText(getIntent().getStringExtra("paramServicio"));

            lblRegistrar.setText("Editar Cita");
            btnRegistrar.setText("Guardar Cita");
        }
    }

    public static class DatePickerFragment extends DialogFragment {

        private DatePickerDialog.OnDateSetListener listener;

        public static DatePickerFragment newInstance(DatePickerDialog.OnDateSetListener listener) {
            DatePickerFragment fragment = new DatePickerFragment();
            fragment.setListener(listener);
            return fragment;
        }

        public void setListener(DatePickerDialog.OnDateSetListener listener) {
            this.listener = listener;
        }

        @Override
        @NonNull
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            return new DatePickerDialog(getActivity(), listener, year, month, day);
        }

    }

    private void showDatePickerDialog() {
        DatePickerFragment newFragment = DatePickerFragment.newInstance(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month++;
                String d,m,y;
                d=day<10?"0"+day:""+day;
                m=month<10?"0"+month:""+month;
                y=year+"";

                final String selectedDate = d + "/" +m + "/" + y;
                txtFecha.setText(selectedDate);
            }
        });

        newFragment.show(getSupportFragmentManager(), "datePicker");
    }






    private void seleccionarHora(){
        txtHora=(EditText) findViewById(R.id.txtHora);
        txtHora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check.start();
                showTimePickerDialog();
            }
        });
    }
    public static class TimePickerFragment extends DialogFragment {

        private TimePickerDialog.OnTimeSetListener listener;

        public static TimePickerFragment newInstance(TimePickerDialog.OnTimeSetListener listener) {
            TimePickerFragment fragment = new TimePickerFragment();
            fragment.setListener(listener);
            return fragment;
        }

        public void setListener(TimePickerDialog.OnTimeSetListener listener) {
            this.listener = listener;
        }

        @Override
        @NonNull
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int min = c.get(Calendar.MINUTE);

            return new TimePickerDialog(getActivity(),listener,hour,min, true);
        }

    }

    private void showTimePickerDialog() {
        TimePickerFragment newFragment = TimePickerFragment.newInstance(new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hour, int min) {
                String h,m;
                h=hour<10?"0"+hour:""+hour;
                m=min<10?"0"+min:""+min;
                final String selectedTime = h + ":" + m;
                txtHora.setText(selectedTime);
            }
        });
        newFragment.show(getSupportFragmentManager(), "timePicker");
    }


}