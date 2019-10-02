package dislexia.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;

import dislexia.app.Modelo.Actividad;
import dislexia.app.Modelo.Persona;
import dislexia.app.Modelo.Resultado;

public class ActividadEspecialista_Grafico extends AppCompatActivity {

    private TextView nombreTxt,apellidoTxt;
    private Spinner actividadNombreSpinner,nivelesSpinner;
    private Button buttonGraficoFallas, buttonGraficoTiempo;
    private String actividadSeleccionada,nivelSeleccionado,idPersona;
    private ArrayList<Actividad> actividad;


    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_especialista__grafico);
       idPersona = (String) getIntent().getSerializableExtra("idPersona");
        actividad = (ArrayList<Actividad>) getIntent().getSerializableExtra("actividad");
       String nombre = (String) getIntent().getSerializableExtra("nombreSeleccionado");
       String apellido = (String) getIntent().getSerializableExtra("apellidoSeleccionado");


        nombreTxt = findViewById(R.id.nombreTxtGrafico);
        apellidoTxt = findViewById(R.id.apellidoTxtGrafico);
        actividadNombreSpinner = (Spinner) findViewById(R.id.actividadNombreSpinner);
        nivelesSpinner = (Spinner) findViewById(R.id.nivelesSpinner);
        buttonGraficoFallas = (Button) findViewById(R.id.buttonGraficoFallas);
        buttonGraficoTiempo = (Button) findViewById(R.id.buttonGraficoTiempo);

        nombreTxt.setText(nombre);
        apellidoTxt.setText(apellido);


        ArrayList<String> arrayActividad = new ArrayList<>();

        for(int i=0;i<actividad.size();i++){
            arrayActividad.add(actividad.get(i).getNombreActividad());
        }




        ArrayAdapter<String> arrayAdapterActividad = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, arrayActividad);

        actividadNombreSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                int actividadnumero =  adapterView.getSelectedItemPosition();
                ArrayList<String> arrayNiveles = new ArrayList<>();
                Array
                Log.e("",""+actividadnumero);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayAdapter<String> arrayAdapterNivel = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, arrayNiveles);

        actividadNombreSpinner.setAdapter(arrayAdapterActividad);
        nivelesSpinner.setAdapter(arrayAdapterNivel);



        //actividadNombreSpinner.setOnItemClickListener(itemselectedActividad);

        //nivelesSpinner.setOnItemClickListener(itemSelectedNivel);

        buttonGraficoTiempo.setOnClickListener(clickBotonGraficoTiempo);
        buttonGraficoFallas.setOnClickListener(clickBotonGraficoFallas);

        inicializarFirebase();





    }

  /*  AdapterView.OnItemClickListener itemselectedActividad = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
             actividadSeleccionada = adapterView.getItemAtPosition(i).toString();
        }
    };
    AdapterView.OnItemClickListener itemSelectedNivel = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            nivelSeleccionado = adapterView.getItemAtPosition(i).toString();

        }
    };
*/
    View.OnClickListener clickBotonGraficoTiempo = new View.OnClickListener() {

        @Override
        public void onClick(View view) {
            ArrayList<Resultado> listaResultado = new ArrayList<Resultado>();
            actividadSeleccionada="Reconocimiento de Grafias";
            nivelSeleccionado= "1";
            Resultado r = new Resultado();

            r.readData(new Resultado.FirebaseCallBackResultado() {
                @Override
                public void onCallback(ArrayList<Resultado> listaResultado) {
                    Intent i = new Intent(ActividadEspecialista_Grafico.this,GraficoTiempo.class);
                    i.putExtra("idPersona",idPersona);
                    i.putExtra("nombreActividad",actividadSeleccionada);
                    i.putExtra("nivelSeleccionado",nivelSeleccionado);
                    i.putExtra("resultado",listaResultado);
                    startActivity(i);
                }
            },idPersona,actividadSeleccionada,nivelSeleccionado,databaseReference,listaResultado);



        }
    };


    View.OnClickListener clickBotonGraficoFallas = new View.OnClickListener() {

        @Override
        public void onClick(View view) {
            ArrayList<Resultado> listaResultado1 = new ArrayList<Resultado>();
            actividadSeleccionada="Reconocimiento de Grafias";
            nivelSeleccionado= "1";

            Resultado r = new Resultado();
            Log.e("",""+idPersona);
            r.readData(new Resultado.FirebaseCallBackResultado() {
                @Override
                public void onCallback(ArrayList<Resultado> listaResultado1) {
                    Intent i = new Intent(ActividadEspecialista_Grafico.this,GraficoFallas.class);
                    i.putExtra("idPersona",idPersona);
                    i.putExtra("nombreActividad",actividadSeleccionada);
                    i.putExtra("nivelSeleccionado",nivelSeleccionado);
                    i.putExtra("resultado",listaResultado1);
                    startActivity(i);

                }
            },idPersona,actividadSeleccionada,nivelSeleccionado,databaseReference,listaResultado1);


        }
    };







    private void inicializarFirebase() {
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();


    }


}
