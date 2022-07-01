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

public class Sede3 extends FragmentActivity implements OnMapReadyCallback {

    GoogleMap gMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sede3);
        SupportMapFragment mapFragment= (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapa3);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        gMap=googleMap;
        gMap.getUiSettings().setZoomControlsEnabled(true);

        LatLng sede3= new LatLng(-12.098557352648001, -77.03918089351255);
        gMap.addMarker( new MarkerOptions().position(sede3).title("VetOnIt Sede San Isidro"));
        gMap.animateCamera(CameraUpdateFactory.newLatLngZoom(sede3,16));
    }
}