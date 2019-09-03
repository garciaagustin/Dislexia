package dislexia.app;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.LinkedList;

import dislexia.app.Modelo.Actividad;
import dislexia.app.Modelo.Nivel;

public class Niveles extends AppCompatActivity {

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_niveles);

        LinearLayout botonera = (LinearLayout) findViewById(R.id.layaoutNiveles);
        LinearLayout.LayoutParams lp =new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT );
        final String nombreActividad = getIntent().getStringExtra("nombreActividad");
        final String idPersona = getIntent().getStringExtra("idPersona");
        final ArrayList<Nivel> listaNiveles = (ArrayList<Nivel>) getIntent().getSerializableExtra("ListaNiveles");

        for(int i=0;i<listaNiveles.size();i++){

            String numero= listaNiveles.get(i).getNumero();
            final Class ventanaNivel= listaNiveles.get(i).getVentanaNivel();

            Button button = new Button(this);
            button.setLayoutParams(lp);
            button.setText(numero);
            button.setBackground(this.getResources().getDrawable(R.drawable.fondo_boton_redondeado)); // Cambiar fondo boton x archivo creado
            button.setTextColor(this.getResources().getColorStateList(R.drawable.txt_boton_redondeado)); //Cambia el color de texto del boton
            botonera.addView(button);
            //Agrego listener al boton para redireccionarlo a la actividad correspondiente
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view1) {
                    Intent intent = new Intent(Niveles.this, ventanaNivel);
                    intent.putExtra("idPersona",idPersona);
                    intent.putExtra("nombreActividad",nombreActividad);
                    view1.getContext().startActivity(intent);
                }
            });
        }
    }
}
