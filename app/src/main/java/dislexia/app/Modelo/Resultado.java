package dislexia.app.Modelo;

import com.google.firebase.database.DatabaseReference;

import java.util.Date;
import java.util.UUID;

public class Resultado {

    String idResultado;
    String nombreActividad;
    String nivel;
    int cantidadFallas;
    String tiempo;
    String idPersona;
    String fecha;

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getIdResultado() {
        return idResultado;
    }

    public void setIdResultado(String idResultado) {
        this.idResultado = idResultado;
    }

    public String getNombreActividad() {
        return nombreActividad;
    }

    public void setNombreActividad(String nombreActividad) {
        this.nombreActividad = nombreActividad;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public int getCantidadFallas() {
        return cantidadFallas;
    }

    public void setCantidadFallas(int cantidadFallas) {
        this.cantidadFallas = cantidadFallas;
    }

    public String getTiempo() {
        return tiempo;
    }

    public void setTiempo(String tiempo) {
        this.tiempo = tiempo;
    }

    public String getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(String idPersona) {
        this.idPersona = idPersona;
    }


    public void registrarResultado(String nombreActividad, String nivel, int cantidadFallas, String tiempo, String idPersona, String fecha, DatabaseReference databaseReference){

        Resultado resultado = new Resultado();
        resultado.setIdPersona(idPersona);
        resultado.setCantidadFallas(cantidadFallas);
        resultado.setFecha(fecha);
        String idResultado=UUID.randomUUID().toString();
        resultado.setIdResultado(idResultado);
        resultado.setNivel(nivel);
        resultado.setTiempo(tiempo);
        resultado.setNombreActividad(nombreActividad);

        databaseReference.child("Resultado").child(idResultado).setValue(resultado);





    }



}
