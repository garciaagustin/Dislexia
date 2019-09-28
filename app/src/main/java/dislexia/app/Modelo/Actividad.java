package dislexia.app.Modelo;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import java.io.Serializable;
import java.util.ArrayList;


import dislexia.app.ActividadesPantalla;
import dislexia.app.R;
import dislexia.app.Registrar;

public class Actividad implements Serializable {

    String nombreActividad;

    ArrayList<Nivel> niveles= new ArrayList<Nivel>();







    public Actividad(String nombreActividad){


        this.nombreActividad = nombreActividad;

        ArrayList<Nivel> niveles = new ArrayList<Nivel>();
        this.niveles=niveles;




    }


    public String getNombreActividad() {
        return nombreActividad;
    }

    public void setNombreActividad(String nombreActividad) {
        this.nombreActividad = nombreActividad;
    }

    public ArrayList<Nivel> getNiveles() {
        return niveles;
    }

    public void agregarNivel( Nivel nivel) {

        niveles.add(nivel);

    }








}
