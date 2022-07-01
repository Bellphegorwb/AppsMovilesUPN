package com.stefanot.vetonit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class SedesActivity extends AppCompatActivity {

    Button btnSede1, btnSede2,btnSede3;
    ImageButton btnAtras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sedes);
        asignarReferencias();
    }
    private void asignarReferencias(){
        btnSede1=findViewById(R.id.btnSede1);
        btnSede2=findViewById(R.id.btnSede2);
        btnSede3=findViewById(R.id.btnSede3);
        btnAtras=findViewById(R.id.btnAtras);
        btnSede1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(SedesActivity.this, Sede1.class);
                startActivity(intent);
            }
        });
        btnSede2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(SedesActivity.this, Sede2.class);
                startActivity(intent);
            }
        });
        btnSede3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(SedesActivity.this, Sede3.class);
                startActivity(intent);
            }
        });
        btnAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}