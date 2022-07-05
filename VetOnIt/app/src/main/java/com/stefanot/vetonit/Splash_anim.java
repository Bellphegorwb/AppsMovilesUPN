package com.stefanot.vetonit;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Splash_anim extends AppCompatActivity {
    private static int SPLASH_SCREEN=4000;
    Animation top_anim,bottom_anim;
    ImageView logo;
    TextView titulo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_anim);
        asignarReferencias();
    }
    private void asignarReferencias(){
        top_anim= AnimationUtils.loadAnimation(this, R.anim.top_anim);
        bottom_anim= AnimationUtils.loadAnimation(this, R.anim.bottom_anim);
        logo=findViewById(R.id.logo);
        titulo=findViewById(R.id.titulo);

        logo.setAnimation(top_anim);
        titulo.setAnimation(bottom_anim);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent= new Intent(Splash_anim.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        },SPLASH_SCREEN);
    }
}