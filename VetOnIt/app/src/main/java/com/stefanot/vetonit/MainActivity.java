package com.stefanot.vetonit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button btnIniciarSesion, btnRegistrate;
    EditText txtCorreo, txtClave;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        asignarReferencias();
    }

    private void asignarReferencias() {
        btnIniciarSesion=findViewById(R.id.btnIniciarSesion);
        btnRegistrate=findViewById(R.id.btnRegistrate);

        txtCorreo=findViewById(R.id.txtCorreo);
        txtClave=findViewById(R.id.txtClave);

        btnIniciarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String correo, clave;
                correo=txtCorreo.getText().toString();
                clave=txtClave.getText().toString();
                Intent intent=new Intent(MainActivity.this,Home.class);
                if (correo.equals("admin")&&clave.equals("admin"))
                    startActivity(intent);
                else
                    Toast.makeText(MainActivity.this, "correo y/o clave incorrectos", Toast.LENGTH_LONG).show();
            }
        });
        btnRegistrate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,Registro.class);
                startActivity(intent);
            }
        });
    }
}