package dislexia.app.Modelo;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.ArrayList;


import dislexia.app.ActividadesPantalla;
import dislexia.app.R;
import dislexia.app.Registrar;

public class Actividad {

    String nombreActividad;

    ArrayList<Nivel> niveles= new ArrayList<Nivel>();







    public Actividad(final Context context, LinearLayout linearLayout, final String nombreActividad, LinearLayout.LayoutParams lp, final Class activity){


        this.nombreActividad = nombreActividad;

        ArrayList<Nivel> niveles = new ArrayList<Nivel>();
        this.niveles=niveles;


        Button button = new Button(context);
        button.setLayoutParams(lp);
        button.setText(nombreActividad);
        button.setBackground(context.getResources().getDrawable(R.drawable.fondo_boton_redondeado)); // Cambiar fondo boton x archivo creado
        button.setTextColor(context.getResources().getColorStateList(R.drawable.txt_boton_redondeado)); //Cambia el color de texto del boton
        linearLayout.addView(button);
        //Agrego listener al boton para redireccionarlo a la actividad correspondiente
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view1) {
                Intent intent = new Intent(context,activity);

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
