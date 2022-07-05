package com.stefanot.vetonit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Registro extends AppCompatActivity {

    Button btnVolverIniciarSesion, btnRegistrar;
    EditText txtCorreo, txtClave, txtNombre,txtConfirmarContrena;

    MediaPlayer ladrido1;

    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        asignarReferencias();
        ladrido1 =MediaPlayer.create(this, R.raw.ladrido1);
    }
    private void asignarReferencias() {
        btnRegistrar = findViewById(R.id.btnRegistrar);

        txtCorreo = findViewById(R.id.txtCorreo);
        txtClave = findViewById(R.id.txtClave);
        txtConfirmarContrena = findViewById(R.id.txtPass);


        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ladrido1.start();
                validate();
            }
        });
        mAuth = FirebaseAuth.getInstance();
    }

    public  void validate(){
        String email = txtCorreo.getText().toString().trim();
        String password = txtClave.getText().toString().trim();
        String confirmPassword = txtConfirmarContrena.getText().toString().trim();

        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            txtCorreo.setError("Correo invalido");
            return;
        }else {
            txtCorreo.setError(null);
        }

        if (password.isEmpty() || password.length() < 8){
            txtClave.setError("Se necesitan mas de 8 caracteres");
            return;
        }else{
            txtClave.setError(null);
        }

        if (!confirmPassword.equals(password)){
            txtConfirmarContrena.setError("Deben ser iguales");
            return;
        }else{
            registrar(email,password);
        }

    }

    public void registrar(String email,String password){
        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Intent intent = new Intent(Registro.this,Home.class);
                            startActivity(intent);
                            finish();
                        }else{
                           String mensajeE = task.getException().getMessage();

                            Toast.makeText(Registro.this,"Fallo en registrarse"+mensajeE,Toast.LENGTH_LONG).show();
                        }
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