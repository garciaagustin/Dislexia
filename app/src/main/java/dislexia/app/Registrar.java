package dislexia.app;

import androidx.appcompat.app.AppCompatActivity;
import dislexia.app.Modelo.*;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.UUID;

public class Registrar extends AppCompatActivity {

    private EditText nombreTxt,apellidoTxt,dniTxt,edadTxt,emailTxt,password,especialidadTxt,matriculaTxt;
    private RadioButton masculinoRb,femeninoRb;
    private CheckBox especialistaCb;
    private Button botonRegistrar;
    private View viewEspecialista;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    FirebaseAuth mAuth;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registrar);

        nombreTxt = (EditText) findViewById(R.id.nombreTxt);
        apellidoTxt = (EditText) findViewById(R.id.apellidoTxt);
        dniTxt = (EditText) findViewById(R.id.dniTxt);
        edadTxt= (EditText) findViewById(R.id.edadTxt);
        emailTxt = (EditText) findViewById(R.id.emailTxt);
        password = (EditText) findViewById(R.id.pass);
        especialidadTxt = (EditText) findViewById(R.id.especialidadTxt);
        matriculaTxt = (EditText) findViewById(R.id.matriculaTxt);
        masculinoRb = (RadioButton) findViewById(R.id.masculinorb);
        femeninoRb = (RadioButton) findViewById(R.id.femeninorb);
        especialistaCb = (CheckBox) findViewById(R.id.especialistaCb);
        viewEspecialista =(View) findViewById(R.id.viewEspecialista) ;
        viewEspecialista.setVisibility(View.GONE);

        especialistaCb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override

            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if(compoundButton.isChecked()){
                    viewEspecialista.setVisibility(View.VISIBLE);
                }
                else{
                    viewEspecialista.setVisibility(View.INVISIBLE);
                }
            }
        });
        botonRegistrar = (Button) findViewById(R.id.botonRegistrar);
        femeninoRb.setChecked(true);




        inicializarFirebase();
    }

    private void inicializarFirebase() {
        FirebaseApp.initializeApp(this);
        firebaseDatabase= FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference();
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
    }



    public void registrarBoton(View view){

        Persona persona = new Persona();
        Usuario usuario = new Usuario();

        String idPersona = UUID.randomUUID().toString();
        String email = emailTxt.getText().toString();
        String pass = password.getText().toString();
        String nombre = nombreTxt.getText().toString();
        String apellido = apellidoTxt.getText().toString();
        String dni = dniTxt.getText().toString();
        String edad = edadTxt.getText().toString();

        boolean especialista_nino;
        String especialidad;
        String matricula;
        boolean sexo=false;

        if(masculinoRb.isChecked()){
            sexo=true;
        }else if(femeninoRb.isChecked()){
            sexo=false;
        }

        if(viewEspecialista.getVisibility() == View.VISIBLE){

            especialista_nino=true; // es especialista
            matricula = matriculaTxt.getText().toString();
            especialidad = especialidadTxt.getText().toString();
            if(TextUtils.isEmpty(matricula)){
                matriculaTxt.setError("");
                Toast.makeText(this,"Ingrese matricula",Toast.LENGTH_LONG).show();
            }
            else if(TextUtils.isEmpty(especialidad)) {
                especialidadTxt.setError("");
                Toast.makeText(this, "Ingrese Especialidad", Toast.LENGTH_SHORT).show();
            }

        }
        else{
            especialista_nino= false; // es ninio
            matricula= null;
            especialidad= null;
        }
         if(TextUtils.isEmpty(nombre)) {
            nombreTxt.setError("");
            Toast.makeText(this, "Ingrese nombre", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(apellido)) {
            apellidoTxt.setError("");
            Toast.makeText(this, "Ingrese apellido", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(edad)) {
            edadTxt.setError("");
            Toast.makeText(this, "Ingrese edad", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(dni)) {
            dniTxt.setError("");
            Toast.makeText(this, "Ingrese Dni", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(email)){
            emailTxt.setError("");
            Toast.makeText(this,"Ingrese email ",Toast.LENGTH_LONG).show();

        }
        else if(TextUtils.isEmpty(pass)) {
            password.setError("");
            Toast.makeText(this, "Ingrese contrasena", Toast.LENGTH_SHORT).show();
        }


        else if(TextUtils.isEmpty(pass)) {
            password.setError("");
            Toast.makeText(this, "Ingrese contrasena", Toast.LENGTH_SHORT).show();
        }
        else {
            persona.setNombre(nombre);
            persona.setApellido(apellido);
            persona.setIdPersona(idPersona);
            persona.setDni(dni);
            persona.setSexo(sexo);
            persona.setMatricula(matricula);
            persona.setEspecialista_nino(especialista_nino);
            persona.setEspecialidad(especialidad);
            persona.setEdad(edad);
            usuario.registrarUsuario(email, pass, persona, mAuth, databaseReference, this);

        }



    }








}
