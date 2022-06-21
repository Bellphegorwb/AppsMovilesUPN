package com.stefanot.t4grupalproyecto01;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        asignarReferencias();
        manejoOpciones();
    }

    public void asignarReferencias() {
        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.navigation_menu);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.titulo);
        setSupportActionBar(toolbar);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.app_name, R.string.app_name);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
    }
    private void mostrarFragmento(Fragment fragment){
        getSupportFragmentManager().beginTransaction().replace(R.id.contenedor, fragment).
                setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).commit();
    }
    private void manejoOpciones(){
        mostrarFragmento(new FragmentHome());
        navigationView.getMenu().getItem(0).setChecked(true);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                limpiarSeleccion();
                switch (item.getItemId())
                {
                    case  R.id.nav_home:
                        mostrarFragmento(new FragmentHome());
                        drawerLayout.closeDrawers();
                        item.setChecked(true);
                        break;
                    case R.id.suma:
                        mostrarFragmento(new FragmentSuma());
                        drawerLayout.closeDrawers();
                        item.setChecked(true);
                        break;
                    case R.id.multi:
                        mostrarFragmento(new FragmentMult());
                        drawerLayout.closeDrawers();
                        item.setChecked(true);
                        break;
                }
                return false;
            }
        });
    }
    void limpiarSeleccion(){
        for (int i=0;i<navigationView.getMenu().size();i++){
            navigationView.getMenu().getItem(i).setChecked(false);
        }
    }
}