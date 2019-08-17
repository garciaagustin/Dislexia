package dislexia.app.Modelo;

import com.google.firebase.database.DatabaseReference;

import java.util.UUID;

public class Persona {

    String idPersona;
    String nombre;
    String apellido;
    String dni;
    String edad;
    boolean sexo;
    boolean especialista_nino;
    String especialidad;
    String matricula;


    public String getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(String idPersona) {
        this.idPersona = idPersona;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public boolean isSexo() {
        return sexo;
    }

    public void setSexo(boolean sexo) {
        this.sexo = sexo;
    }

    public boolean isEspecialista_nino() {
        return especialista_nino;
    }

    public void setEspecialista_nino(boolean especialista_nino) {
        this.especialista_nino = especialista_nino;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }


    public void registrarPersona(String idPersona, String nombre, String apellido, String dni, String edad, boolean sexo, boolean especialista_nino, String especialidad, String matricula, DatabaseReference databaseReference){

        Persona persona = new Persona();
        persona.setIdPersona(idPersona);
        persona.setNombre(nombre);
        persona.setApellido(apellido);
        persona.setEdad(edad);
        persona.setEspecialidad(especialidad);
        persona.setEspecialista_nino(especialista_nino);
        persona.setMatricula(matricula);
        persona.setSexo(sexo);
        persona.setDni(dni);

        databaseReference.child("Persona").child(persona.getIdPersona()).setValue(persona);

    }
}
