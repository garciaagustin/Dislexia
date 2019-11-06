package dislexia.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.LinkedList;

import dislexia.app.Modelo.Actividad;
import dislexia.app.Modelo.Nivel;

public class Main extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        ArrayList<Actividad> actividades = new ArrayList<>();

        Nivel nive11 = new Nivel("1", Nivel_1_ReconocimientoGrafias.class);
        Nivel nivel2 = new Nivel("2", Nivel_2_ReconocimientoGrafias.class);
        Actividad actividadRg = new Actividad( "Reconocimiento de Grafias");

        actividadRg.agregarNivel(nive11);
        actividadRg.agregarNivel(nivel2);

        Nivel nivel21 = new Nivel("1",Nivel1_IdPalabrasyPseudopalabras.class);
        Actividad actividadIdP = new Actividad("Identificacion de Palabras y Pseudopalabras");
        actividadIdP.agregarNivel(nivel21);

        Nivel nivel1Familia = new Nivel("1",Nivel1_FamiliaPalabras.class);
        Actividad actividadFp= new Actividad("Familia de Palabras");
        actividadFp.agregarNivel(nivel1Familia);

        actividades.add(actividadRg); // Agrega actividad a lista para pasarlo a la prox
        actividades.add(actividadIdP);
        actividades.add(actividadFp);



        Log.e("",""+actividades.size());
       Intent i = new Intent(this, Login.class);
        i.putExtra("actividad",actividades);
        startActivity(i);

    }
}
