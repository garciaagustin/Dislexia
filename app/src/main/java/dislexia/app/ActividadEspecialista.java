package dislexia.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import dislexia.app.Modelo.Actividad;
import dislexia.app.Modelo.Persona;

public class ActividadEspecialista extends AppCompatActivity {

    EditText nombreNinio, apellidoNinio;
    ListView listaNinio;
    Button butonBuscar;
    ArrayList<Persona> personas= new ArrayList<Persona>();

    private ArrayList<Persona> listaPersonas = new ArrayList<Persona>();
    private ArrayList<Actividad> actividad;
    ArrayAdapter<Persona> personaArrayAdapter;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_especialista);

        actividad = (ArrayList<Actividad>) getIntent().getSerializableExtra("actividad");

        nombreNinio = (EditText) findViewById(R.id.nombreNinotxt);
        apellidoNinio = (EditText) findViewById(R.id.apellidoniniotxt);
        listaNinio = (ListView) findViewById(R.id.listaNinios);
        butonBuscar = (Button) findViewById(R.id.buttonBuscar);


        butonBuscar.setOnClickListener(clickListener);
        listaNinio.setOnItemClickListener(itemSelect);
        inicializarFirebase();

    }


    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            listaNinio.setAdapter(null);

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

    AdapterView.OnItemClickListener itemSelect = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
           Persona personaSeleccionada = (Persona) adapterView.getItemAtPosition(i);

          /* Persona p = new Persona();
           p.setIdPersona(personaSeleccionada.getIdPersona());
           p.setApellido(personaSeleccionada.getApellido());
           p.setEspecialidad(personaSeleccionada.getEspecialidad());
           p.setEspecialista_nino(personaSeleccionada.isEspecialista_nino());
           p.setMatricula(personaSeleccionada.getMatricula());
           p.setSexo(personaSeleccionada.isSexo());
           p.setDni(personaSeleccionada.getDni());
           p.setNombre(personaSeleccionada.getNombre());
           p.setEdad(personaSeleccionada.getEdad());
             personas.add(p);
                */
           Intent intent = new Intent(ActividadEspecialista.this,ActividadEspecialista_Grafico.class);




           intent.putExtra("nombreSeleccionado", personaSeleccionada.getNombre());
            intent.putExtra("apellidoSeleccionado", personaSeleccionada.getApellido());
            intent.putExtra("idPersona",personaSeleccionada.getIdPersona());
            intent.putExtra("actividad",actividad);
           startActivity(intent);
        }
    };
        private void inicializarFirebase() {
            FirebaseApp.initializeApp(this);
            firebaseDatabase = FirebaseDatabase.getInstance();
            databaseReference = firebaseDatabase.getReference();


        }


}
