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

public class MainActivity extends AppCompatActivity {
    Button btnIniciarSesion, btnRegistrate;
    EditText txtCorreo, txtClave;

    MediaPlayer clic1, gatito1;



    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        asignarReferencias();
        clic1 =MediaPlayer.create(this, R.raw.clic2);
        gatito1=MediaPlayer.create(this, R.raw.gatito1);
    }

    private void asignarReferencias() {
        btnIniciarSesion=findViewById(R.id.btnIniciarSesion);
        btnRegistrate=findViewById(R.id.btnRegistrate);

        txtCorreo=findViewById(R.id.txtCorreo);
        txtClave=findViewById(R.id.txtClave);

        mAuth = FirebaseAuth.getInstance();

        btnIniciarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               validat();
               clic1.start();
            }
        });
        btnRegistrate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,Registro.class);
                startActivity(intent);
                gatito1.start();
            }
        });
    }

    public  void validat(){
        String email = txtCorreo.getText().toString().trim();
        String password = txtClave.getText().toString().trim();


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
            iniciarSesion(email,password);

    }

    private void iniciarSesion(String email, String password){
        mAuth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Intent intent = new Intent(MainActivity.this,Home.class);
                            startActivity(intent);
                            finish();
                        }else {
                            Toast.makeText(MainActivity.this,"Credenciales equivocadas, intentalo nuevamente",Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }




}