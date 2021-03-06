package dislexia.app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.ComponentName;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.Toast;


import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedList;

import dislexia.app.Modelo.Nivel;
import dislexia.app.Modelo.Resultado;
import dislexia.app.Modelo.Usuario;

public class Nivel1_IdPalabrasyPseudopalabras extends AppCompatActivity {

    Button iniciarBoton;
    Chronometer cronometro;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    FirebaseUser user;
    Usuario u;
    String idPersonaConectada,nombreActividad;
    LinkedList<String> personaRecuperada = new LinkedList<String>();
    ConstraintLayout torta,puerta,pelota,manzana,corazon,television;
    Button correcto1,correcto2,correcto3,correcto4,correcto5,correcto6;
    Button incorrecto1,incorrecto2,incorrecto3,incorrecto4,incorrecto5,incorrecto6;
    int cantidadCorrecta=0;
    int cantidadFallas=0;
    ArrayList<Nivel> listaNiveles;
    private MediaPlayer mp_great, mp_bad;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nivel1__id_palabrasy_pseudopalabras2);

        nombreActividad = getIntent().getStringExtra("nombreActividad");
        listaNiveles = (ArrayList<Nivel>) getIntent().getSerializableExtra("niveles");

        iniciarBoton = (Button) findViewById(R.id.iniciarButon);

        torta = (ConstraintLayout) findViewById(R.id.torta);
        correcto1 = findViewById(R.id.correcto1);
        incorrecto1 = findViewById(R.id.incorrecta1);


        puerta= (ConstraintLayout) findViewById(R.id.puerta);
        correcto2 = findViewById(R.id.correcto2);
        incorrecto2 = findViewById(R.id.incorrecto2);


        pelota = (ConstraintLayout) findViewById(R.id.pelota);
        correcto3 = findViewById(R.id.correcto3);
        incorrecto3 = findViewById(R.id.incorrecto3);


        manzana = (ConstraintLayout) findViewById(R.id.manzana);
        correcto4 = findViewById(R.id.correcto4);
        incorrecto4 = findViewById(R.id.incorrecto4);


        corazon = (ConstraintLayout) findViewById(R.id.corazon);
        correcto5 = findViewById(R.id.correcto5);
        incorrecto5 = findViewById(R.id.incorrecto5);

        television = (ConstraintLayout) findViewById(R.id.television);
        correcto6 = findViewById(R.id.correcto6);
        incorrecto6= findViewById(R.id.incorrecto6);

        iniciarBoton.setOnClickListener(listener);
        correcto1.setOnClickListener(listener);
        incorrecto1.setOnClickListener(listener);

        correcto2.setOnClickListener(listener);
        incorrecto2.setOnClickListener(listener);

        correcto3.setOnClickListener(listener);
        incorrecto3.setOnClickListener(listener);

        correcto4.setOnClickListener(listener);
        incorrecto4.setOnClickListener(listener);

        correcto5.setOnClickListener(listener);
        incorrecto5.setOnClickListener(listener);

        correcto6.setOnClickListener(listener);
        incorrecto6.setOnClickListener(listener);

        puerta.setVisibility(View.INVISIBLE);
        pelota.setVisibility(View.INVISIBLE);
        manzana.setVisibility(View.INVISIBLE);
        corazon.setVisibility(View.INVISIBLE);
        television.setVisibility(View.INVISIBLE);


        mp_great = MediaPlayer.create(this, R.raw.wonderful);
        mp_bad = MediaPlayer.create(this, R.raw.bad);

        //animacionCorrecta = (LottieAnimationView) findViewById(R.id.gifCorrecto);


        inicializarFirebase();

        u = new Usuario();

        user = FirebaseAuth.getInstance().getCurrentUser();
        correcto1.setEnabled(false);
        incorrecto1.setEnabled(false);
    }

    private void inicializarFirebase() {
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();


    }

    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            Log.e("","activididad"+nombreActividad);

            final Resultado resultado = new Resultado();

            if (iniciarBoton.isPressed()) {
                correcto1.setEnabled(true);
                incorrecto1.setEnabled(true);

                cronometro = new Chronometer(Nivel1_IdPalabrasyPseudopalabras.this);
                cronometro.setBase(SystemClock.elapsedRealtime());
                cronometro.setFormat(null);
                cronometro.start();

            }
                Log.e("", "" + cantidadCorrecta);
                if (correcto1.isPressed()) {
                    mp_great.start();

                    Toast.makeText(Nivel1_IdPalabrasyPseudopalabras.this, "Correcto", Toast.LENGTH_LONG).show();
                    cantidadCorrecta++;
                    torta.setVisibility(View.INVISIBLE);
                    puerta.setVisibility(View.VISIBLE);
                    if (cantidadCorrecta == 6) {
                        Toast.makeText(Nivel1_IdPalabrasyPseudopalabras.this, "Nivel Completado", Toast.LENGTH_LONG).show();
                        Calendar c = Calendar.getInstance();
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        final String fecha = sdf.format(c.getTime());
                        final String tiempo = String.valueOf(SystemClock.elapsedRealtime() - cronometro.getBase());
                        cronometro.stop();
                        if (user != null) {
                            String userEmail = user.getEmail();


                            u.readData(new Usuario.FirebaseCallBackidPersona() {
                                @Override
                                public void onCallback(LinkedList<String> personaRecuperada) {
                                    idPersonaConectada = personaRecuperada.getFirst();
                                    resultado.registrarResultado(nombreActividad, "1", cantidadFallas, tiempo, idPersonaConectada, fecha, databaseReference);
                                }
                            }, userEmail, databaseReference, personaRecuperada);
                        } else {
                            // No user is signed in
                        }
                        Intent i = new Intent(Nivel1_IdPalabrasyPseudopalabras.this,Niveles.class);
                        i.putExtra("niveles",listaNiveles);
                        startActivity(i);
                        finish();
                    }

                } else if (incorrecto1.isPressed()) {
                    mp_bad.start();
                    Toast.makeText(Nivel1_IdPalabrasyPseudopalabras.this, "Incorrecto", Toast.LENGTH_LONG).show();
                    cantidadFallas++;
                }

                //Puerta

                if (correcto2.isPressed()) {
                    mp_great.start();
                    Toast.makeText(Nivel1_IdPalabrasyPseudopalabras.this, "Correcto", Toast.LENGTH_LONG).show();
                    cantidadCorrecta++;
                    puerta.setVisibility(View.INVISIBLE);
                    pelota.setVisibility(View.VISIBLE);
                    if (cantidadCorrecta == 6) {
                        Toast.makeText(Nivel1_IdPalabrasyPseudopalabras.this, "Nivel Completado", Toast.LENGTH_LONG).show();
                        Calendar c = Calendar.getInstance();
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        final String fecha = sdf.format(c.getTime());
                        final String tiempo = String.valueOf(SystemClock.elapsedRealtime() - cronometro.getBase());
                        cronometro.stop();
                        if (user != null) {
                            String userEmail = user.getEmail();


                            u.readData(new Usuario.FirebaseCallBackidPersona() {
                                @Override
                                public void onCallback(LinkedList<String> personaRecuperada) {
                                    idPersonaConectada = personaRecuperada.getFirst();
                                    resultado.registrarResultado(nombreActividad, "1", cantidadFallas, tiempo, idPersonaConectada, fecha, databaseReference);
                                }
                            }, userEmail, databaseReference, personaRecuperada);
                        } else {
                            // No user is signed in
                        }
                        Intent i = new Intent(Nivel1_IdPalabrasyPseudopalabras.this,Niveles.class);
                        i.putExtra("niveles",listaNiveles);
                        startActivity(i);
                        finish();
                    }

                } else if (incorrecto2.isPressed()) {
                    mp_bad.start();
                    Toast.makeText(Nivel1_IdPalabrasyPseudopalabras.this, "Incorrecto", Toast.LENGTH_LONG).show();
                    cantidadFallas++;
                }

                // Pelota

                if (correcto3.isPressed()) {
                    mp_great.start();
                    Toast.makeText(Nivel1_IdPalabrasyPseudopalabras.this, "Correcto", Toast.LENGTH_LONG).show();
                    cantidadCorrecta++;
                    pelota.setVisibility(View.INVISIBLE);
                    manzana.setVisibility(View.VISIBLE);
                    if (cantidadCorrecta == 6) {
                        Toast.makeText(Nivel1_IdPalabrasyPseudopalabras.this, "Nivel Completado", Toast.LENGTH_LONG).show();
                        Calendar c = Calendar.getInstance();
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        final String fecha = sdf.format(c.getTime());
                        final String tiempo = String.valueOf(SystemClock.elapsedRealtime() - cronometro.getBase());
                        cronometro.stop();
                        if (user != null) {
                            String userEmail = user.getEmail();


                            u.readData(new Usuario.FirebaseCallBackidPersona() {
                                @Override
                                public void onCallback(LinkedList<String> personaRecuperada) {
                                    idPersonaConectada = personaRecuperada.getFirst();
                                    resultado.registrarResultado(nombreActividad, "1", cantidadFallas, tiempo, idPersonaConectada, fecha, databaseReference);
                                }
                            }, userEmail, databaseReference, personaRecuperada);
                        } else {
                            // No user is signed in
                        }
                        Intent i = new Intent(Nivel1_IdPalabrasyPseudopalabras.this,Niveles.class);
                        i.putExtra("niveles",listaNiveles);
                        startActivity(i);
                        finish();
                    }

                } else if (incorrecto3.isPressed()) {
                    mp_bad.start();
                    Toast.makeText(Nivel1_IdPalabrasyPseudopalabras.this, "Incorrecto", Toast.LENGTH_LONG).show();
                    cantidadFallas++;
                }

                //Manzana

                if (correcto4.isPressed()) {
                    mp_great.start();
                    Toast.makeText(Nivel1_IdPalabrasyPseudopalabras.this, "Correcto", Toast.LENGTH_LONG).show();
                    cantidadCorrecta++;
                    manzana.setVisibility(View.INVISIBLE);
                    corazon.setVisibility(View.VISIBLE);
                    if (cantidadCorrecta == 6) {
                        Toast.makeText(Nivel1_IdPalabrasyPseudopalabras.this, "Nivel Completado", Toast.LENGTH_LONG).show();
                        Calendar c = Calendar.getInstance();
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        final String fecha = sdf.format(c.getTime());
                        final String tiempo = String.valueOf(SystemClock.elapsedRealtime() - cronometro.getBase());
                        cronometro.stop();
                        if (user != null) {
                            String userEmail = user.getEmail();


                            u.readData(new Usuario.FirebaseCallBackidPersona() {
                                @Override
                                public void onCallback(LinkedList<String> personaRecuperada) {
                                    idPersonaConectada = personaRecuperada.getFirst();
                                    resultado.registrarResultado(nombreActividad, "1", cantidadFallas, tiempo, idPersonaConectada, fecha, databaseReference);
                                }
                            }, userEmail, databaseReference, personaRecuperada);
                        } else {
                            // No user is signed in
                        }
                        Intent i = new Intent(Nivel1_IdPalabrasyPseudopalabras.this,Niveles.class);
                        i.putExtra("niveles",listaNiveles);
                        startActivity(i);
                        finish();
                    }

                } else if (incorrecto4.isPressed()) {
                    mp_bad.start();
                    Toast.makeText(Nivel1_IdPalabrasyPseudopalabras.this, "Incorrecto", Toast.LENGTH_LONG).show();
                    cantidadFallas++;
                }

                //Corazon

                if (correcto5.isPressed()) {
                    mp_great.start();
                    Toast.makeText(Nivel1_IdPalabrasyPseudopalabras.this, "Correcto", Toast.LENGTH_LONG).show();
                    cantidadCorrecta++;
                    corazon.setVisibility(View.INVISIBLE);
                    television.setVisibility(View.VISIBLE);

                    if (cantidadCorrecta == 6) {
                        Toast.makeText(Nivel1_IdPalabrasyPseudopalabras.this, "Nivel Completado", Toast.LENGTH_LONG).show();
                        Calendar c = Calendar.getInstance();
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        final String fecha = sdf.format(c.getTime());
                        final String tiempo = String.valueOf(SystemClock.elapsedRealtime() - cronometro.getBase());
                        cronometro.stop();
                        if (user != null) {
                            String userEmail = user.getEmail();


                            u.readData(new Usuario.FirebaseCallBackidPersona() {
                                @Override
                                public void onCallback(LinkedList<String> personaRecuperada) {
                                    idPersonaConectada = personaRecuperada.getFirst();
                                    resultado.registrarResultado(nombreActividad, "1", cantidadFallas, tiempo, idPersonaConectada, fecha, databaseReference);
                                }
                            }, userEmail, databaseReference, personaRecuperada);
                        } else {
                            // No user is signed in
                        }
                        Intent i = new Intent(Nivel1_IdPalabrasyPseudopalabras.this,Niveles.class);
                        i.putExtra("niveles",listaNiveles);
                        startActivity(i);
                        finish();
                    }

                } else if (incorrecto5.isPressed()) {
                    mp_bad.start();
                    Toast.makeText(Nivel1_IdPalabrasyPseudopalabras.this, "Incorrecto", Toast.LENGTH_LONG).show();
                    cantidadFallas++;
                }

                //Television

                if (correcto6.isPressed()) {
                    mp_great.start();
                    Toast.makeText(Nivel1_IdPalabrasyPseudopalabras.this, "Correcto", Toast.LENGTH_LONG).show();
                    cantidadCorrecta++;
                    television.setVisibility(View.INVISIBLE);

                    if (cantidadCorrecta == 6) {
                        Toast.makeText(Nivel1_IdPalabrasyPseudopalabras.this, "Nivel Completado", Toast.LENGTH_LONG).show();
                        Calendar c = Calendar.getInstance();
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        final String fecha = sdf.format(c.getTime());
                        final String tiempo = String.valueOf(SystemClock.elapsedRealtime() - cronometro.getBase());
                        cronometro.stop();
                        if (user != null) {
                            String userEmail = user.getEmail();


                            u.readData(new Usuario.FirebaseCallBackidPersona() {
                                @Override
                                public void onCallback(LinkedList<String> personaRecuperada) {
                                    idPersonaConectada = personaRecuperada.getFirst();
                                    resultado.registrarResultado(nombreActividad, "1", cantidadFallas, tiempo, idPersonaConectada, fecha, databaseReference);
                                }
                            }, userEmail, databaseReference, personaRecuperada);
                        } else {
                            // No user is signed in
                        }
                        Intent i = new Intent(Nivel1_IdPalabrasyPseudopalabras.this,Niveles.class);
                        i.putExtra("niveles",listaNiveles);
                        startActivity(i);
                        finish();
                    }

                } else if (incorrecto6.isPressed()) {
                    mp_bad.start();
                    Toast.makeText(Nivel1_IdPalabrasyPseudopalabras.this, "Incorrecto", Toast.LENGTH_LONG).show();
                    cantidadFallas++;
                }


            }




    };
}
