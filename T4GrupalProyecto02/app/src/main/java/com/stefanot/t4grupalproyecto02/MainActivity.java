package com.stefanot.t4grupalproyecto02;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView menuNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        asignarReferencias();
    }
    private void asignarReferencias(){
        menuNav = findViewById(R.id.menuNav);
        mostrarFragmento(new FragmentHome());
        menuNav.getMenu().getItem(0).setChecked(true);
        menuNav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId()==R.id.menu_1){
                    mostrarFragmento(new FragmentHome());
                    item.setChecked(true);
                }
                if(item.getItemId()==R.id.menu_2){
                    mostrarFragmento(new FragmentSuma());
                    item.setChecked(true);
                }
                if(item.getItemId()==R.id.menu_3){
                    mostrarFragmento(new FragmentMult());
                    item.setChecked(true);
                }
                return false;
            }
        });
    }
    private void mostrarFragmento(Fragment fragment){
        getSupportFragmentManager().beginTransaction().replace(R.id.contenedor, fragment).
                setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).commit();

    }
}