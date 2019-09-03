package dislexia.app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import dislexia.app.Modelo.Persona;

public class ActividadEspecialista extends AppCompatActivity {

    EditText nombreNinio, apellidoNinio;
    ListView listaNinio;
    Button butonBuscar;

    private ArrayList<Persona> listaPersonas = new ArrayList<Persona>();
    ArrayAdapter<Persona> personaArrayAdapter;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_especialista);

        nombreNinio = (EditText) findViewById(R.id.nombreNinotxt);
        apellidoNinio = (EditText) findViewById(R.id.apellidoniniotxt);
        listaNinio = (ListView) findViewById(R.id.listaNinios);
        butonBuscar = (Button) findViewById(R.id.buttonBuscar);


        butonBuscar.setOnClickListener(clickListener);
        inicializarFirebase();

    }


    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            String nombreBuscado = nombreNinio.getText().toString();
            String apellidoBuscado = apellidoNinio.getText().toString();

            Persona p = new Persona();

            p.recuperarPersona(new Persona.FirebaseCallBackBusqueda() {
                @Override
                public void onCallback(ArrayList<Persona> listaPersonas) {
                    Log.e("",""+listaPersonas.size());
                    personaArrayAdapter = new ArrayAdapter<Persona>(ActividadEspecialista.this, android.R.layout.simple_list_item_1, listaPersonas);
                    listaNinio.setAdapter(personaArrayAdapter);
                }
            }, nombreBuscado, apellidoBuscado, databaseReference, listaPersonas);


        }};

        private void inicializarFirebase() {
            FirebaseApp.initializeApp(this);
            firebaseDatabase = FirebaseDatabase.getInstance();
            databaseReference = firebaseDatabase.getReference();


        }


    }
