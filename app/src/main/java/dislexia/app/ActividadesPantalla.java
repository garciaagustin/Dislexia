package dislexia.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.LinearLayout;


import com.google.firebase.auth.FirebaseAuth;

import java.util.LinkedList;

import dislexia.app.Modelo.Actividad;
import dislexia.app.Modelo.Nivel;

public class ActividadesPantalla extends AppCompatActivity {

    private MediaPlayer sp;
    LinkedList<Nivel> listaNiveles = new LinkedList<Nivel>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividades_pantalla);

        LinearLayout botonera = (LinearLayout) findViewById(R.id.layoutbotones);
        LinearLayout.LayoutParams lp =new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT );



        Nivel nive11 = new Nivel("1", Nivel_1_ReconocimientoGrafias.class);
        Nivel nivel2 = new Nivel("2", Nivel_2_ReconocimientoGrafias.class);
        Actividad actividadRg = new Actividad(this.getContext(),botonera, "Reconocimiento de Grafias", lp,Niveles.class);

        actividadRg.agregarNivel(nive11);
        actividadRg.agregarNivel(nivel2);

        sp = MediaPlayer.create(this, R.raw.goats);
        sp.start();
        sp.setLooping(true);


    }


    public Context getContext(){
        return this;
    }



}
