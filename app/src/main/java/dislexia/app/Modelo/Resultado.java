package dislexia.app.Modelo;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.UUID;

public class Resultado implements java.io.Serializable {

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


    public void readData(final FirebaseCallBackResultado firebaseCallBack, final String idPersona, final String nombreActividad, final String nivel, DatabaseReference databaseReference, final ArrayList<Resultado> listaResultado){

        databaseReference.child("Resultado").orderByChild("idPersona").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){
                    Resultado r = new Resultado();

                    r = dataSnapshot1.getValue(Resultado.class);
                    String idPersonaRecuperada = dataSnapshot1.child("idPersona").getValue().toString();
                    String nombreActividadRecuperado =dataSnapshot1.child("nombreActividad").getValue().toString();
                    String nivelRecuperado = dataSnapshot1.child("nivel").getValue().toString();
                    Log.e("", "recuperada"+idPersonaRecuperada);

                    Log.e("","idpersona ="+ idPersona + "recuperada = "+idPersonaRecuperada);
                    if( idPersonaRecuperada.equals(idPersona) && nivelRecuperado.equals(nivel) && nombreActividadRecuperado.equals(nombreActividad)){
                        Log.e("","entro al if con " +r);

                        listaResultado.add(r);

                    }

                }
                firebaseCallBack.onCallback(listaResultado);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }




    public interface FirebaseCallBackResultado{

        void onCallback(ArrayList<Resultado> listaResultado);
    }








}
