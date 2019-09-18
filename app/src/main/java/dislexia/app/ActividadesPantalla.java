package dislexia.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.LinearLayout;


import java.util.LinkedList;

import dislexia.app.Modelo.Actividad;
import dislexia.app.Modelo.Nivel;

public class ActividadesPantalla extends AppCompatActivity {
    LinkedList<Nivel> listaNiveles = new LinkedList<Nivel>();
    String idPersona;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividades_pantalla);

        LinearLayout botonera = (LinearLayout) findViewById(R.id.layoutbotones);
        LinearLayout.LayoutParams lp =new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT );


        idPersona = getIntent().getStringExtra("idPersona");
        Nivel nive11 = new Nivel("1", Nivel_1_ReconocimientoGrafias.class);
        Nivel nivel2 = new Nivel("2", Nivel_2_ReconocimientoGrafias.class);
        Actividad actividadRg = new Actividad(this.getContext(),botonera, "Reconocimiento de Grafias", lp,Niveles.class,idPersona);

        actividadRg.agregarNivel(nive11);
        actividadRg.agregarNivel(nivel2);




    }


    public Context getContext(){
        return this;
    }
}
