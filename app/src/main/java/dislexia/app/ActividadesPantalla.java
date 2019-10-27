package dislexia.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;


import com.google.firebase.auth.FirebaseAuth;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;

import dislexia.app.Modelo.Actividad;
import dislexia.app.Modelo.Nivel;

public class ActividadesPantalla extends AppCompatActivity {
    LinkedList<Nivel> listaNiveles = new LinkedList<Nivel>();
    ArrayList<Actividad> actividad = new ArrayList<>();
    MediaPlayer sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividades_pantalla);
        sp = MediaPlayer.create(this, R.raw.goats);
        sp.start();
        sp.setLooping(true);

        actividad = (ArrayList<Actividad>) getIntent().getSerializableExtra("actividad");


        LinearLayout botonera = (LinearLayout) findViewById(R.id.layoutbotones);
        LinearLayout.LayoutParams lp =new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT );

        for (int i=0;i<actividad.size();i++) {
            final ArrayList<Nivel> nivelesActividad = new ArrayList<>();

            for (int j=0;j<actividad.get(i).getNiveles().size();j++){

                nivelesActividad.add(actividad.get(i).getNiveles().get(j));

            }
            final String nombreActividad = actividad.get(i).getNombreActividad();
            Button button = new Button(this);
            button.setLayoutParams(lp);
            button.setText(actividad.get(i).getNombreActividad());
            button.setBackground(this.getResources().getDrawable(R.drawable.fondo_boton_redondeado)); // Cambiar fondo boton x archivo creado
           // button.setTextColor(this.getResources().getColorStateList(R.drawable.txt_boton_redondeado)); //Cambia el color de texto del boton
            botonera.addView(button);
            //Agrego listener al boton para redireccionarlo a la actividad correspondiente
            button.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view1) {
                    Intent intent = new Intent(ActividadesPantalla.this,Niveles.class);
                        intent.putExtra("niveles",nivelesActividad);
                        intent.putExtra("nombreActividad",nombreActividad);
                    view1.getContext().startActivity(intent);
                }
            });

        }



    }


    public Context getContext(){
        return this;
    }



}
