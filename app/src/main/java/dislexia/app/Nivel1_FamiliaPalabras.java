package dislexia.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.RelativeLayout;
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

public class Nivel1_FamiliaPalabras extends AppCompatActivity {

    RelativeLayout calificado,util,domador,abrigar;
    Button iniciar, correcto1fp,correcto2fp,correcto3fp,correcto4fp,incorrecto1fp,incorrecto2fp,incorrecto3fp,
            correcto21fp,correcto22fp,correcto23fp,correcto24fp,incorrecto21fp,incorrecto22fp,incorrecto23fp,
            correcto31fp,correcto32fp,correcto33fp,correcto34fp,incorrecto31fp,incorrecto32fp,incorrecto33fp,
            correcto41fp,correcto42fp,correcto43fp,correcto44fp,incorrecto41fp,incorrecto42fp,incorrecto43fp;

    int cantidadCompletada = 0;
    int cantidadFallas = 0;

    Chronometer cronometro;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    FirebaseUser user;
    Usuario u;
    String idPersonaConectada,nombreActividad;
    LinkedList<String> personaRecuperada = new LinkedList<String>();
    ArrayList<Nivel> listaNiveles;
    private MediaPlayer mp_great, mp_bad;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nivel1__familia_palabras2);

        nombreActividad = getIntent().getStringExtra("nombreActividad");
        listaNiveles = (ArrayList<Nivel>) getIntent().getSerializableExtra("niveles");

        calificado = findViewById(R.id.calificado);
        util = findViewById(R.id.util);
        domador = findViewById(R.id.domador);
        abrigar = findViewById(R.id.abrigar);
        iniciar = findViewById(R.id.buttonFP);
        iniciar.setOnClickListener(listener);

        correcto1fp =findViewById(R.id.correcto1);
        correcto2fp = findViewById(R.id.correcto2);
        correcto3fp = findViewById(R.id.correcto3);
        correcto4fp = findViewById(R.id.correcto4);
        incorrecto1fp = findViewById(R.id.incorrecto1fp);
        incorrecto2fp = findViewById(R.id.incorrecto2);
        incorrecto3fp = findViewById(R.id.incorrecto3);

        correcto21fp = findViewById(R.id.correcto21fp);
        correcto22fp = findViewById(R.id.correcto22fp);
        correcto23fp = findViewById(R.id.correcto23fp);
        correcto24fp = findViewById(R.id.correcto24fp);
        incorrecto21fp = findViewById(R.id.incorrecto21fp);
        incorrecto22fp = findViewById(R.id.incorrecto22fp);
        incorrecto23fp = findViewById(R.id.incorrecto23fp);

        correcto31fp = findViewById(R.id.correcto31fp);
        correcto32fp = findViewById(R.id.correcto32fp);
        correcto33fp = findViewById(R.id.correcto33fp);
        correcto34fp = findViewById(R.id.correcto34fp);
        incorrecto31fp = findViewById(R.id.incorrecto31fp);
        incorrecto32fp = findViewById(R.id.incorrecto32fp);
        incorrecto33fp = findViewById(R.id.incorrecto33fp);

        correcto41fp = findViewById(R.id.correcto41fp);
        correcto42fp = findViewById(R.id.correcto42fp);
        correcto43fp = findViewById(R.id.correcto43fp);
        correcto44fp = findViewById(R.id.correcto44fp);
        incorrecto41fp = findViewById(R.id.incorrecto41fp);
        incorrecto42fp = findViewById(R.id.incorrecto42fp);
        incorrecto43fp = findViewById(R.id.incorrecto43fp);

        correcto1fp.setOnClickListener(listener);
        correcto2fp.setOnClickListener(listener);
        correcto3fp.setOnClickListener(listener);
        correcto4fp.setOnClickListener(listener);
        incorrecto1fp.setOnClickListener(listener);
        incorrecto2fp.setOnClickListener(listener);
        incorrecto3fp.setOnClickListener(listener);

        correcto21fp.setOnClickListener(listener);
        correcto22fp.setOnClickListener(listener);
        correcto23fp.setOnClickListener(listener);
        correcto24fp.setOnClickListener(listener);
        incorrecto21fp.setOnClickListener(listener);
        incorrecto22fp.setOnClickListener(listener);
        incorrecto23fp.setOnClickListener(listener);

        correcto31fp.setOnClickListener(listener);
        correcto32fp.setOnClickListener(listener);
        correcto33fp.setOnClickListener(listener);
        correcto34fp.setOnClickListener(listener);
        incorrecto31fp.setOnClickListener(listener);
        incorrecto32fp.setOnClickListener(listener);
        incorrecto33fp.setOnClickListener(listener);

        correcto41fp.setOnClickListener(listener);
        correcto42fp.setOnClickListener(listener);
        correcto43fp.setOnClickListener(listener);
        correcto44fp.setOnClickListener(listener);
        incorrecto41fp.setOnClickListener(listener);
        incorrecto42fp.setOnClickListener(listener);
        incorrecto43fp.setOnClickListener(listener);

        calificado.setVisibility(View.INVISIBLE);
        util.setVisibility(View.INVISIBLE);
        domador.setVisibility(View.INVISIBLE);
        abrigar.setVisibility(View.INVISIBLE);

        mp_great = MediaPlayer.create(this, R.raw.wonderful);
        mp_bad = MediaPlayer.create(this, R.raw.bad);

        inicializarFirebase();

        u = new Usuario();

        user = FirebaseAuth.getInstance().getCurrentUser();


    }

    private void inicializarFirebase() {
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();


    }

    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            if (iniciar.isPressed()) {
                abrigar.setVisibility(View.VISIBLE);
                iniciar.setVisibility(View.INVISIBLE);

                cronometro = new Chronometer(Nivel1_FamiliaPalabras.this);
                cronometro.setBase(SystemClock.elapsedRealtime());
                cronometro.setFormat(null);
                cronometro.start();

            }

            final Resultado resultado = new Resultado();
                Log.e("", "" + cantidadCompletada);
            //primer familia
                if (correcto1fp.isPressed()) {
                    mp_great.start();
                    cantidadCompletada++;
                    Log.e("", "cantidad com" + cantidadCompletada);
                    Toast.makeText(Nivel1_FamiliaPalabras.this, "Correcto", Toast.LENGTH_LONG).show();
                    correcto1fp.setBackgroundColor(Color.GREEN);

                    correcto1fp.setEnabled(false);


                    if (cantidadCompletada == 4) {
                        cantidadCompletada=0;
                        abrigar.setVisibility(View.INVISIBLE);
                        domador.setVisibility(View.VISIBLE);
                    }


                }
                if (correcto2fp.isPressed()) {
                    mp_great.start();
                    cantidadCompletada++;
                    Log.e("","cantidad com"+cantidadCompletada);
                    Toast.makeText(Nivel1_FamiliaPalabras.this, "Correcto", Toast.LENGTH_LONG).show();
                    correcto2fp.setBackgroundColor(Color.GREEN);

                    correcto2fp.setEnabled(false);
                    if(cantidadCompletada ==4){
                        cantidadCompletada=0;
                        abrigar.setVisibility(View.INVISIBLE);
                        domador.setVisibility(View.VISIBLE);
                    }


                }

                if (correcto3fp.isPressed()) {
                    mp_great.start();
                    cantidadCompletada++;
                    Log.e("","cantidad com"+cantidadCompletada);
                    Toast.makeText(Nivel1_FamiliaPalabras.this, "Correcto", Toast.LENGTH_LONG).show();
                    correcto3fp.setBackgroundColor(Color.GREEN);

                    correcto3fp.setEnabled(false);
                    if(cantidadCompletada ==4){
                        cantidadCompletada=0;
                        abrigar.setVisibility(View.INVISIBLE);
                        domador.setVisibility(View.VISIBLE);
                    }


                }

                if (correcto4fp.isPressed()) {
                    mp_great.start();
                    cantidadCompletada++;
                    Log.e("","cantidad com"+cantidadCompletada);
                    Toast.makeText(Nivel1_FamiliaPalabras.this, "Correcto", Toast.LENGTH_LONG).show();
                    correcto4fp.setBackgroundColor(Color.GREEN);

                    correcto4fp.setEnabled(false);
                    if(cantidadCompletada ==4){
                        cantidadCompletada=0;
                        abrigar.setVisibility(View.INVISIBLE);
                        domador.setVisibility(View.VISIBLE);
                    }


                }
                if (incorrecto1fp.isPressed()) {
                    mp_bad.start();
                    cantidadFallas++;
                    Log.e("","fallas"+cantidadFallas);
                    Toast.makeText(Nivel1_FamiliaPalabras.this,"Incorrecto",Toast.LENGTH_LONG).show();
                    incorrecto1fp.setBackgroundColor(Color.RED);
                    incorrecto1fp.setEnabled(false);

                }else if (incorrecto2fp.isPressed()) {
                    mp_bad.start();
                    cantidadFallas++;
                    Log.e("","fallas"+cantidadFallas);
                    Toast.makeText(Nivel1_FamiliaPalabras.this,"Incorrecto",Toast.LENGTH_LONG).show();
                    incorrecto2fp.setBackgroundColor(Color.RED);
                    incorrecto2fp.setEnabled(false);


                }else if (incorrecto3fp.isPressed()) {
                    mp_bad.start();
                    cantidadFallas++;
                    Log.e("","fallas"+cantidadFallas);
                    Toast.makeText(Nivel1_FamiliaPalabras.this,"Incorrecto",Toast.LENGTH_LONG).show();
                    incorrecto3fp.setBackgroundColor(Color.RED);
                    incorrecto3fp.setEnabled(false);

                }



                // 2 familia

            if (correcto21fp.isPressed()) {
                mp_great.start();
                cantidadCompletada++;
                Log.e("", "cantidad com" + cantidadCompletada);
                Toast.makeText(Nivel1_FamiliaPalabras.this, "Correcto", Toast.LENGTH_LONG).show();
                correcto21fp.setBackgroundColor(Color.GREEN);

                correcto21fp.setEnabled(false);


                if (cantidadCompletada == 4) {
                    cantidadCompletada=0;
                    domador.setVisibility(View.INVISIBLE);
                    util.setVisibility(View.VISIBLE);
                }


            }
            if (correcto22fp.isPressed()) {
                mp_great.start();
                cantidadCompletada++;
                Log.e("","cantidad com"+cantidadCompletada);
                Toast.makeText(Nivel1_FamiliaPalabras.this, "Correcto", Toast.LENGTH_LONG).show();
                correcto22fp.setBackgroundColor(Color.GREEN);

                correcto22fp.setEnabled(false);
                if(cantidadCompletada ==4){
                    cantidadCompletada=0;
                    domador.setVisibility(View.INVISIBLE);
                    util.setVisibility(View.VISIBLE);
                }


            }

            if (correcto23fp.isPressed()) {
                mp_great.start();
                cantidadCompletada++;
                Log.e("","cantidad com"+cantidadCompletada);
                Toast.makeText(Nivel1_FamiliaPalabras.this, "Correcto", Toast.LENGTH_LONG).show();
                correcto23fp.setBackgroundColor(Color.GREEN);

                correcto23fp.setEnabled(false);
                if(cantidadCompletada ==4){
                    cantidadCompletada=0;
                    domador.setVisibility(View.INVISIBLE);
                    util.setVisibility(View.VISIBLE);
                }


            }

            if (correcto24fp.isPressed()) {
                mp_great.start();
                cantidadCompletada++;
                Log.e("","cantidad com"+cantidadCompletada);
                Toast.makeText(Nivel1_FamiliaPalabras.this, "Correcto", Toast.LENGTH_LONG).show();
                correcto24fp.setBackgroundColor(Color.GREEN);

                correcto24fp.setEnabled(false);
                if(cantidadCompletada ==4){
                    cantidadCompletada=0;
                    domador.setVisibility(View.INVISIBLE);
                    util.setVisibility(View.VISIBLE);
                }


            }
            if (incorrecto21fp.isPressed()) {
                mp_bad.start();
                cantidadFallas++;
                Log.e("","fallas"+cantidadFallas);
                Toast.makeText(Nivel1_FamiliaPalabras.this,"Incorrecto",Toast.LENGTH_LONG).show();
                incorrecto21fp.setBackgroundColor(Color.RED);
                incorrecto21fp.setEnabled(false);

            }else if (incorrecto22fp.isPressed()) {
                mp_bad.start();
                cantidadFallas++;
                Log.e("","fallas"+cantidadFallas);
                Toast.makeText(Nivel1_FamiliaPalabras.this,"Incorrecto",Toast.LENGTH_LONG).show();
                incorrecto22fp.setBackgroundColor(Color.RED);
                incorrecto22fp.setEnabled(false);


            }else if (incorrecto23fp.isPressed()) {
                mp_bad.start();
                cantidadFallas++;
                Log.e("","fallas"+cantidadFallas);
                Toast.makeText(Nivel1_FamiliaPalabras.this,"Incorrecto",Toast.LENGTH_LONG).show();
                incorrecto23fp.setBackgroundColor(Color.RED);
                incorrecto23fp.setEnabled(false);

            }

            // 3 familia

            if (correcto31fp.isPressed()) {
                mp_great.start();
                cantidadCompletada++;
                Log.e("", "cantidad com" + cantidadCompletada);
                Toast.makeText(Nivel1_FamiliaPalabras.this, "Correcto", Toast.LENGTH_LONG).show();
                correcto31fp.setBackgroundColor(Color.GREEN);

                correcto31fp.setEnabled(false);


                if (cantidadCompletada == 4) {
                    cantidadCompletada=0;
                    util.setVisibility(View.INVISIBLE);
                    calificado.setVisibility(View.VISIBLE);
                }


            }
            if (correcto32fp.isPressed()) {
                mp_great.start();
                cantidadCompletada++;
                Log.e("","cantidad com"+cantidadCompletada);
                Toast.makeText(Nivel1_FamiliaPalabras.this, "Correcto", Toast.LENGTH_LONG).show();
                correcto32fp.setBackgroundColor(Color.GREEN);

                correcto32fp.setEnabled(false);
                if(cantidadCompletada ==4){
                    cantidadCompletada=0;
                    util.setVisibility(View.INVISIBLE);
                    calificado.setVisibility(View.VISIBLE);
                }


            }

            if (correcto33fp.isPressed()) {
                mp_great.start();
                cantidadCompletada++;
                Log.e("","cantidad com"+cantidadCompletada);
                Toast.makeText(Nivel1_FamiliaPalabras.this, "Correcto", Toast.LENGTH_LONG).show();
                correcto33fp.setBackgroundColor(Color.GREEN);

                correcto33fp.setEnabled(false);
                if(cantidadCompletada ==4){
                    cantidadCompletada=0;
                    util.setVisibility(View.INVISIBLE);
                    calificado.setVisibility(View.VISIBLE);
                }


            }

            if (correcto34fp.isPressed()) {
                mp_great.start();
                cantidadCompletada++;
                Log.e("","cantidad com"+cantidadCompletada);
                Toast.makeText(Nivel1_FamiliaPalabras.this, "Correcto", Toast.LENGTH_LONG).show();
                correcto34fp.setBackgroundColor(Color.GREEN);

                correcto34fp.setEnabled(false);
                if(cantidadCompletada ==4){
                    cantidadCompletada=0;
                    util.setVisibility(View.INVISIBLE);
                    calificado.setVisibility(View.VISIBLE);
                }


            }
            if (incorrecto31fp.isPressed()) {
                mp_bad.start();
                cantidadFallas++;
                Log.e("","fallas"+cantidadFallas);
                Toast.makeText(Nivel1_FamiliaPalabras.this,"Incorrecto",Toast.LENGTH_LONG).show();
                incorrecto31fp.setBackgroundColor(Color.RED);
                incorrecto31fp.setEnabled(false);

            }else if (incorrecto32fp.isPressed()) {
                mp_bad.start();
                cantidadFallas++;
                Log.e("","fallas"+cantidadFallas);
                Toast.makeText(Nivel1_FamiliaPalabras.this,"Incorrecto",Toast.LENGTH_LONG).show();
                incorrecto32fp.setBackgroundColor(Color.RED);
                incorrecto32fp.setEnabled(false);


            }else if (incorrecto33fp.isPressed()) {
                mp_bad.start();
                cantidadFallas++;
                Log.e("","fallas"+cantidadFallas);
                Toast.makeText(Nivel1_FamiliaPalabras.this,"Incorrecto",Toast.LENGTH_LONG).show();
                incorrecto33fp.setBackgroundColor(Color.RED);
                incorrecto33fp.setEnabled(false);

            }


            //4 familia

            if (correcto41fp.isPressed()) {
                mp_great.start();
                cantidadCompletada++;
                Log.e("", "cantidad com" + cantidadCompletada);
                Toast.makeText(Nivel1_FamiliaPalabras.this, "Correcto", Toast.LENGTH_LONG).show();
                correcto41fp.setBackgroundColor(Color.GREEN);

                correcto41fp.setEnabled(false);


                if (cantidadCompletada == 4) {
                    Toast.makeText(Nivel1_FamiliaPalabras.this, "Felicitaciones Nivel Completado", Toast.LENGTH_LONG).show();
                    cantidadCompletada=0;
                    calificado.setVisibility(View.INVISIBLE);

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
                    Intent i = new Intent(Nivel1_FamiliaPalabras.this,Niveles.class);
                    i.putExtra("niveles",listaNiveles);
                    startActivity(i);
                    finish();

                }


            }
            if (correcto42fp.isPressed()) {
                mp_great.start();
                cantidadCompletada++;
                Log.e("","cantidad com"+cantidadCompletada);
                Toast.makeText(Nivel1_FamiliaPalabras.this, "Correcto", Toast.LENGTH_LONG).show();
                correcto42fp.setBackgroundColor(Color.GREEN);

                correcto42fp.setEnabled(false);
                if(cantidadCompletada ==4){
                    Toast.makeText(Nivel1_FamiliaPalabras.this, "Felicitaciones Nivel Completado", Toast.LENGTH_LONG).show();
                    cantidadCompletada=0;
                    calificado.setVisibility(View.INVISIBLE);
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
                    Intent i = new Intent(Nivel1_FamiliaPalabras.this,Niveles.class);
                    i.putExtra("niveles",listaNiveles);
                    startActivity(i);
                    finish();



                }


            }

            if (correcto43fp.isPressed()) {
                mp_great.start();
                cantidadCompletada++;
                Log.e("","cantidad com"+cantidadCompletada);
                Toast.makeText(Nivel1_FamiliaPalabras.this, "Correcto", Toast.LENGTH_LONG).show();
                correcto43fp.setBackgroundColor(Color.GREEN);

                correcto43fp.setEnabled(false);
                if(cantidadCompletada ==4){
                    Toast.makeText(Nivel1_FamiliaPalabras.this, "Felicitaciones Nivel Completado", Toast.LENGTH_LONG).show();
                    cantidadCompletada=0;
                    calificado.setVisibility(View.INVISIBLE);
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
                    Intent i = new Intent(Nivel1_FamiliaPalabras.this,Niveles.class);
                    i.putExtra("niveles",listaNiveles);
                    startActivity(i);
                    finish();



                }


            }

            if (correcto44fp.isPressed()) {
                mp_great.start();
                cantidadCompletada++;
                Log.e("","cantidad com"+cantidadCompletada);
                Toast.makeText(Nivel1_FamiliaPalabras.this, "Correcto", Toast.LENGTH_LONG).show();
                correcto44fp.setBackgroundColor(Color.GREEN);

                correcto44fp.setEnabled(false);
                if(cantidadCompletada ==4){
                    Toast.makeText(Nivel1_FamiliaPalabras.this, "Felicitaciones Nivel Completado", Toast.LENGTH_LONG).show();
                    cantidadCompletada=0;
                    calificado.setVisibility(View.INVISIBLE);
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
                    Intent i = new Intent(Nivel1_FamiliaPalabras.this,Niveles.class);
                    i.putExtra("niveles",listaNiveles);
                    startActivity(i);
                    finish();



                }


            }
            if (incorrecto41fp.isPressed()) {
                mp_bad.start();
                cantidadFallas++;
                Log.e("","fallas"+cantidadFallas);
                Toast.makeText(Nivel1_FamiliaPalabras.this,"Incorrecto",Toast.LENGTH_LONG).show();
                incorrecto41fp.setBackgroundColor(Color.RED);
                incorrecto41fp.setEnabled(false);

            }else if (incorrecto42fp.isPressed()) {
                mp_bad.start();
                cantidadFallas++;
                Log.e("","fallas"+cantidadFallas);
                Toast.makeText(Nivel1_FamiliaPalabras.this,"Incorrecto",Toast.LENGTH_LONG).show();
                incorrecto42fp.setBackgroundColor(Color.RED);
                incorrecto42fp.setEnabled(false);


            }else if (incorrecto43fp.isPressed()) {
                mp_bad.start();
                cantidadFallas++;
                Log.e("","fallas"+cantidadFallas);
                Toast.makeText(Nivel1_FamiliaPalabras.this,"Incorrecto",Toast.LENGTH_LONG).show();
                incorrecto43fp.setBackgroundColor(Color.RED);
                incorrecto43fp.setEnabled(false);

            }




        }


    };















}
