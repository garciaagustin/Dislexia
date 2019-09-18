package dislexia.app.Modelo;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.LinkedList;

import javax.security.auth.callback.Callback;

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


    public void readData(final FirebaseCallBackEspecialista firebaseCallBack, final String idPersona, final DatabaseReference databaseReference, final LinkedList<Boolean> listaEspecialista){

        databaseReference.child("Persona").orderByChild("idPersona").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){
                    Persona p = new Persona();
                    String idPersonaRecuperada = dataSnapshot1.child("idPersona").getValue().toString();
                    Log.e("", "recuperada"+idPersonaRecuperada);
                    Boolean espninio = (Boolean) dataSnapshot1.child("especialista_nino").getValue();

                    if( idPersonaRecuperada.equals(idPersona)){
                        Log.e("","entro al if");
                        listaEspecialista.add(espninio);

                    }

                }
                firebaseCallBack.onCallback(listaEspecialista);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


    public void recuperarPersona(final FirebaseCallBackBusqueda firebaseCallBack, final String nombre, final String apellido, final DatabaseReference databaseReference, final ArrayList<Persona> listaPersona){

        databaseReference.child("Persona").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){
                    Persona p = new Persona();
                    p = dataSnapshot1.getValue(Persona.class);


                    Log.e("",""+p.getNombre());
                    if( p.getNombre().equalsIgnoreCase(nombre) && p.getApellido().equalsIgnoreCase(apellido)){
                        Log.e("","entro al if ");
                        listaPersona.add(p);

                    }

                }
                firebaseCallBack.onCallback(listaPersona);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    public interface FirebaseCallBackEspecialista{

        void onCallback(LinkedList<Boolean> listaEspecialista);


    }



    public interface FirebaseCallBackBusqueda{

        void onCallback(ArrayList<Persona> listaPersonas);
    }



    public String toString(){
        return  " "+nombre+" "+apellido+"                   "+dni;
    }
    }