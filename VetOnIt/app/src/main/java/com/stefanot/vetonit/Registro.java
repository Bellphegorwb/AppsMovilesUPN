package com.stefanot.vetonit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Registro extends AppCompatActivity {

    Button btnVolverIniciarSesion, btnRegistrar;
    EditText txtCorreo, txtClave, txtNombre;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        asignarReferencias();
    }
    private void asignarReferencias() {
        btnVolverIniciarSesion = findViewById(R.id.btnVolverIniciarSesion);
        btnRegistrar = findViewById(R.id.btnRegistrar);

        txtCorreo = findViewById(R.id.txtCorreo);
        txtClave = findViewById(R.id.txtClave);
        txtNombre = findViewById(R.id.txtNombre);

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String correo, clave, nombre;
                correo = txtCorreo.getText().toString();
                clave = txtClave.getText().toString();
                nombre = txtNombre.getText().toString();
                Intent intent = new Intent(Registro.this, MainActivity.class);
                if (regex(correo, "^[^@]+@[^@]+\\.[a-zA-Z]{2,}$")){
                    Toast.makeText(Registro.this, "Registro Exitoso", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(Registro.this, "Correo invalido", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnVolverIniciarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Registro.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
    public boolean regex(String texto, String patron) {
        Pattern pattern = Pattern.compile(patron, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(texto);
        boolean matchFound = matcher.find();
        return matchFound;
    }
}