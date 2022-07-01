package com.stefanot.vetonit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class Sede2 extends FragmentActivity implements OnMapReadyCallback {

    GoogleMap gMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sede2);
        SupportMapFragment mapFragment= (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapa2);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        gMap=googleMap;
        gMap.getUiSettings().setZoomControlsEnabled(true);

        LatLng sede2= new LatLng(-12.141913490897906, -76.99201393458682);
        gMap.addMarker( new MarkerOptions().position(sede2).title("VetOnIt Sede Surco"));
        gMap.animateCamera(CameraUpdateFactory.newLatLngZoom(sede2,16));
    }
}