package dislexia.app.Modelo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.ArrayList;


import dislexia.app.ActividadesPantalla;
import dislexia.app.Registrar;

public class Actividad {

    String nombreActividad;

    ArrayList<Nivel> niveles= new ArrayList<Nivel>();






    public Actividad(final Context context, LinearLayout linearLayout, final String nombreActividad, LinearLayout.LayoutParams lp, final Class activity, final String idPersona){

        this.nombreActividad = nombreActividad;

        ArrayList<Nivel> niveles = new ArrayList<Nivel>();
        this.niveles=niveles;


        Button button = new Button(context);
        button.setLayoutParams(lp);
        button.setText(nombreActividad);
        linearLayout.addView(button);
        //Agrego listener al boton para redireccionarlo a la actividad correspondiente
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view1) {
                Intent intent = new Intent(context,activity);
                intent.putExtra("idPersona",idPersona);
                intent.putExtra("nombreActividad",nombreActividad);
                intent.putExtra("ListaNiveles",getNiveles());
                view1.getContext().startActivity(intent);
            }
        });
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
