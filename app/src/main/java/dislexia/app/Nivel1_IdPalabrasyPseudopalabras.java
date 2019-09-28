package dislexia.app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Nivel1_IdPalabrasyPseudopalabras extends AppCompatActivity {

    ConstraintLayout torta,puerta;
    Button correcto1,correcto2;
    Button incorrecto1,incorrecto2;
    int cantidadCorrecta=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nivel1__id_palabrasy_pseudopalabras2);

        torta = (ConstraintLayout) findViewById(R.id.torta);
        correcto1 = findViewById(R.id.correcto1);
        incorrecto1 = findViewById(R.id.incorrecta1);

        puerta= (ConstraintLayout) findViewById(R.id.puerta);
        correcto2 = findViewById(R.id.correcto2);
        incorrecto2 = findViewById(R.id.incorrecto2);


        correcto1.setOnClickListener(listener);
        incorrecto1.setOnClickListener(listener);

        correcto2.setOnClickListener(listener);
        incorrecto2.setOnClickListener(listener);



        puerta.setVisibility(View.INVISIBLE);
    }


    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

                Log.e("",""+cantidadCorrecta);
                if (correcto1.isPressed()) {

                    Toast.makeText(Nivel1_IdPalabrasyPseudopalabras.this, "Correcto", Toast.LENGTH_LONG).show();
                    cantidadCorrecta++;
                    torta.setVisibility(View.INVISIBLE);
                    puerta.setVisibility(View.VISIBLE);
                    if(cantidadCorrecta == 6){
                        Toast.makeText(Nivel1_IdPalabrasyPseudopalabras.this,"Nivel Completado",Toast.LENGTH_LONG).show();
                    }

                } else if (incorrecto1.isPressed()) {
                    Toast.makeText(Nivel1_IdPalabrasyPseudopalabras.this, "Incorrecto", Toast.LENGTH_LONG).show();
                }

                //Puerta

                if (correcto2.isPressed()) {

                    Toast.makeText(Nivel1_IdPalabrasyPseudopalabras.this, "Correcto", Toast.LENGTH_LONG).show();
                    cantidadCorrecta++;
                    puerta.setVisibility(View.INVISIBLE);
                    if(cantidadCorrecta == 6){
                        Toast.makeText(Nivel1_IdPalabrasyPseudopalabras.this,"Nivel Completado",Toast.LENGTH_LONG).show();
                    }

                } else if (incorrecto2.isPressed()) {
                    Toast.makeText(Nivel1_IdPalabrasyPseudopalabras.this, "Incorrecto", Toast.LENGTH_LONG).show();
                }


            }

    };
}
