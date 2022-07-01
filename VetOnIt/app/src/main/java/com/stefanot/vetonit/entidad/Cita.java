package com.stefanot.vetonit.entidad;

import java.util.Date;

public class Cita {
    private int id;
    private String especie;
    private String raza;
    private String nombre;
    private String servicio;
    private String fecha;
    private String hora;

    public Cita(String especie, String raza, String nombre, String servicio, String fecha, String hora) {
        this.especie = especie;
        this.raza = raza;
        this.nombre = nombre;
        this.servicio = servicio;
        this.fecha = fecha;
        this.hora = hora;
    }

    public Cita(int id, String especie, String raza, String nombre, String servicio, String fecha, String hora) {
        this.id = id;
        this.especie = especie;
        this.raza = raza;
        this.nombre = nombre;
        this.servicio = servicio;
        this.fecha = fecha;
        this.hora = hora;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getServicio() {
        return servicio;
    }

    public void setServicio(String servicio) {
        this.servicio = servicio;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }
}
