package dislexia.app.Modelo;

public class Resultado {

    String idResultado;
    String nombreActividad;
    String nivel;
    String cantidadFallas;
    String tiempo;
    String idPersona;

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

    public String getCantidadFallas() {
        return cantidadFallas;
    }

    public void setCantidadFallas(String cantidadFallas) {
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
}
