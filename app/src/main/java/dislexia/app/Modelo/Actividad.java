package dislexia.app.Modelo;

import android.view.View;

import java.util.LinkedList;

public class Actividad {

    String nombreActividad;

    LinkedList<Nivel> niveles= new LinkedList<Nivel>();

    View ventanaActividad;


    public String getNombreActividad() {
        return nombreActividad;
    }

    public void setNombreActividad(String nombreActividad) {
        this.nombreActividad = nombreActividad;
    }

    public LinkedList<Nivel> getNiveles() {
        return niveles;
    }

    public void agregarNiveles(LinkedList<Nivel> niveles, Nivel nivel) {

        niveles.add(nivel);

    }

    public View getVentanaActividad() {
        return ventanaActividad;
    }

    public void setVentanaActividad(View ventanaActividad) {
        this.ventanaActividad = ventanaActividad;
    }






}
