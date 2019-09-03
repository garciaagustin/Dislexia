package dislexia.app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.ClipData;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.Layout;
import android.text.format.Time;
import android.util.Log;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

import dislexia.app.Modelo.Nivel;
import dislexia.app.Modelo.Resultado;

public class Nivel_1_ReconocimientoGrafias extends AppCompatActivity implements View.OnTouchListener, View.OnDragListener {
    private ConstraintLayout constraintLayout;
    private Button botonIniciar;
    private TextView letrap,letrab,letrad,letraq;
    private LinearLayout letraPView1,letraPView2,letraqView,letraBView,letraDView,letraQView2;
    private ImageView letrapvacia1,letrapCompleta1,letraDvacia1,letraDCompleta1,letraQVacia1,letraQCompleta1,letraQVacia2,letraQCompleta2,letrabVacia1,letrabCompleta1,letraPVacia2,letraPCompleta2;

    int cantidadFallas=0;
    int cantidadCompletada=0;
    String idPersona;
    String nombreActividad;
    Chronometer cronometro;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    int cantidad=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_nivel_1__reconocimiento_grafias);
             idPersona = getIntent().getStringExtra("idPersona");
             nombreActividad = getIntent().getStringExtra("nombreActividad");

            Log.e("",""+idPersona);
            // Enlazamos con xml
            botonIniciar = (Button)findViewById(R.id.botonIniciar);
            letrapvacia1 = (ImageView) findViewById(R.id.letrapvacia1);
            letrapCompleta1 = (ImageView) findViewById(R.id.letrapCompleta1);
            letraDvacia1 = (ImageView) findViewById(R.id.letraDVacia1);
            letraDCompleta1 = (ImageView) findViewById(R.id.letrapCompleta1);
            letraQCompleta1 = (ImageView) findViewById(R.id.letraQCompleta1);
            letraQVacia1 = (ImageView) findViewById(R.id.letraQVacia1);
            letraQCompleta2 = (ImageView) findViewById(R.id.letraQCompleta2);
            letraQVacia2 = (ImageView) findViewById(R.id.letraQVacia2);
            letraPVacia2 = (ImageView) findViewById(R.id.letraQVacia2);
            letraPVacia2 = (ImageView) findViewById(R.id.letraPVacia2);
            letraPCompleta2 = (ImageView) findViewById(R.id.letraPCompleta2);
            letrabVacia1 = (ImageView) findViewById(R.id.letraBVacia1);
            letrabCompleta1 = (ImageView) findViewById(R.id.letraBCompleta1);






            botonIniciar.setOnClickListener(escucharClick);
            letrapCompleta1.setVisibility(View.INVISIBLE);
            letraDCompleta1.setVisibility(View.INVISIBLE);
            letraQCompleta1.setVisibility(View.INVISIBLE);
            letraQCompleta2.setVisibility(View.INVISIBLE);
            letraPCompleta2.setVisibility(View.INVISIBLE);
            letrabCompleta1.setVisibility(View.INVISIBLE);




        inicializarFirebase();

    }

    private void inicializarFirebase() {
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();


    }
    View.OnClickListener escucharClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {



            //Listener para arrastrar letra
            findViewById(R.id.letrab).setOnTouchListener(Nivel_1_ReconocimientoGrafias.this);
            findViewById(R.id.letraD).setOnTouchListener(Nivel_1_ReconocimientoGrafias.this);
            findViewById(R.id.letraQ).setOnTouchListener(Nivel_1_ReconocimientoGrafias.this);
            findViewById(R.id.letrap).setOnTouchListener(Nivel_1_ReconocimientoGrafias.this);

            //Drag para que las ventanas puedan recibir letra

            findViewById(R.id.letraPView1).setOnDragListener(Nivel_1_ReconocimientoGrafias.this);
            findViewById(R.id.letraPView2).setOnDragListener(Nivel_1_ReconocimientoGrafias.this);
            findViewById(R.id.letraDView).setOnDragListener(Nivel_1_ReconocimientoGrafias.this);
            findViewById(R.id.letraQView1).setOnDragListener(Nivel_1_ReconocimientoGrafias.this);
            findViewById(R.id.letraQView2).setOnDragListener(Nivel_1_ReconocimientoGrafias.this);
            findViewById(R.id.letraBView).setOnDragListener(Nivel_1_ReconocimientoGrafias.this);

            cronometro = new Chronometer(Nivel_1_ReconocimientoGrafias.this);
            cronometro.setBase(SystemClock.elapsedRealtime());
            cronometro.setFormat(null);
            cronometro.start();


        }
    };
    @Override
    public boolean onDrag(View v, DragEvent e) {

        Resultado resultado = new Resultado(); // Crea instancia resultado
        int action = e.getAction();
        View view = (View) e.getLocalState();
        int idView = view.getId();


          while(cantidadCompletada <=6) {                             //Chequea que se hayan puesto todas las letras
              switch (e.getAction()) {
                  case DragEvent.ACTION_DROP:

                      if (view.getId() == R.id.letrap && v.getId() == R.id.letraPView1) {

                          letrapCompleta1.setVisibility(View.VISIBLE); //Muestra la imagen completa
                          letrapvacia1.setVisibility(View.INVISIBLE);  // Oculta la imagen vacia
                          cantidadCompletada++;
                          Log.e("", "" + cantidadCompletada);


                          Toast.makeText(Nivel_1_ReconocimientoGrafias.this, "Correcto", Toast.LENGTH_SHORT).show();
                          if (cantidadCompletada == 6) {

                              Calendar c = Calendar.getInstance();
                              SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                              String fecha = sdf.format(c.getTime());
                              String tiempo = String.valueOf(SystemClock.elapsedRealtime() - cronometro.getBase());

                              cronometro.stop();
                              resultado.registrarResultado(nombreActividad,"1",cantidadFallas,tiempo,idPersona,fecha,databaseReference);
                              Toast.makeText(Nivel_1_ReconocimientoGrafias.this,"Felicitaciones Nivel Completado",Toast.LENGTH_SHORT).show();
                          }
                          return true;
                      }
                      if (view.getId() == R.id.letrap && v.getId() == R.id.letraPView2) {

                          letraPCompleta2.setVisibility(View.VISIBLE);
                          letraPVacia2.setVisibility(View.INVISIBLE);
                          cantidadCompletada++;
                          Log.e("", "" + cantidadCompletada);

                          Toast.makeText(Nivel_1_ReconocimientoGrafias.this, "Correcto", Toast.LENGTH_SHORT).show();
                          if (cantidadCompletada ==6) {

                              Calendar c = Calendar.getInstance();
                              SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                              String fecha = sdf.format(c.getTime());
                              String tiempo = String.valueOf(SystemClock.elapsedRealtime() - cronometro.getBase());
                              cronometro.stop();
                              resultado.registrarResultado(nombreActividad,"1",cantidadFallas,tiempo,idPersona,fecha,databaseReference);
                              Toast.makeText(Nivel_1_ReconocimientoGrafias.this,"Felicitaciones Nivel Completado",Toast.LENGTH_SHORT).show();
                          }
                          return true;
                      }



                      if (view.getId() == R.id.letraD && v.getId() == R.id.letraDView) {

                          letraDCompleta1.setVisibility(View.VISIBLE);
                          letraDvacia1.setVisibility(View.INVISIBLE);
                          cantidadCompletada++;
                          Log.e("",""+cantidadCompletada);

                          Toast.makeText(Nivel_1_ReconocimientoGrafias.this, "Correcto", Toast.LENGTH_SHORT).show();
                          if(cantidadCompletada ==6){

                              Calendar c = Calendar.getInstance();
                              SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                              String fecha = sdf.format(c.getTime());
                              String tiempo = String.valueOf(SystemClock.elapsedRealtime() - cronometro.getBase());
                              cronometro.stop();
                              resultado.registrarResultado(nombreActividad,"1",cantidadFallas,tiempo,idPersona,fecha,databaseReference);
                              Toast.makeText(Nivel_1_ReconocimientoGrafias.this,"Felicitaciones Nivel Completado",Toast.LENGTH_SHORT).show();
                          }
                          return true;
                          }


                      if (view.getId() == R.id.letrab && v.getId() == R.id.letraBView) {

                          letrabCompleta1.setVisibility(View.VISIBLE);
                          letrabVacia1.setVisibility(View.INVISIBLE);
                          cantidadCompletada++;
                          Log.e("",""+cantidadCompletada);

                          Toast.makeText(Nivel_1_ReconocimientoGrafias.this, "Correcto", Toast.LENGTH_SHORT).show();
                          if(cantidadCompletada ==6){

                              Calendar c = Calendar.getInstance();
                              SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                              String fecha = sdf.format(c.getTime());
                              String tiempo = String.valueOf(SystemClock.elapsedRealtime() - cronometro.getBase());
                              cronometro.stop();
                              resultado.registrarResultado(nombreActividad,"1",cantidadFallas,tiempo,idPersona,fecha,databaseReference);
                              Toast.makeText(Nivel_1_ReconocimientoGrafias.this,"Felicitaciones Nivel Completado",Toast.LENGTH_SHORT).show();
                          }
                          return true;
                          }


                      if (view.getId() == R.id.letraQ && v.getId() == R.id.letraQView1) {

                          letraQCompleta1.setVisibility(View.VISIBLE);
                          letraQVacia1.setVisibility(View.INVISIBLE);
                          cantidadCompletada++;
                          Log.e("",""+cantidadCompletada);

                          Toast.makeText(Nivel_1_ReconocimientoGrafias.this, "Correcto", Toast.LENGTH_SHORT).show();
                          if(cantidadCompletada == 6){

                              Calendar c = Calendar.getInstance();
                              SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                              String fecha = sdf.format(c.getTime());
                              String tiempo = String.valueOf(SystemClock.elapsedRealtime() - cronometro.getBase());
                              cronometro.stop();
                              resultado.registrarResultado(nombreActividad,"1",cantidadFallas,tiempo,idPersona,fecha,databaseReference);
                              Toast.makeText(Nivel_1_ReconocimientoGrafias.this,"Felicitaciones Nivel Completado",Toast.LENGTH_SHORT).show();

                          }
                          return true;
                      }
                      if (view.getId() == R.id.letraQ && v.getId() == R.id.letraQView2) {

                          letraQVacia2.setVisibility(View.INVISIBLE);
                          letraQCompleta2.setVisibility(View.VISIBLE);
                          cantidadCompletada++;
                          Log.e("",""+cantidadCompletada);

                          Toast.makeText(Nivel_1_ReconocimientoGrafias.this, "Correcto", Toast.LENGTH_SHORT).show();
                          if(cantidadCompletada ==6){

                              Calendar c = Calendar.getInstance();
                              SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                              String fecha = sdf.format(c.getTime());
                              String tiempo = String.valueOf(SystemClock.elapsedRealtime() - cronometro.getBase());
                              cronometro.stop();
                              resultado.registrarResultado(nombreActividad,"1",cantidadFallas,tiempo,idPersona,fecha,databaseReference);
                              Toast.makeText(Nivel_1_ReconocimientoGrafias.this,"Felicitaciones Nivel Completado",Toast.LENGTH_SHORT).show();

                          }
                          return true;
                      } else {
                          cantidadFallas++;
                          Toast.makeText(Nivel_1_ReconocimientoGrafias.this, "Incorrecto", Toast.LENGTH_SHORT).show();
                          Log.e("",""+cantidadCompletada);
                          return true;
                      }





                  case DragEvent.ACTION_DRAG_STARTED:

                      return true;
                  // do nothing

                  case DragEvent.ACTION_DRAG_ENTERED:

                      return true;

                  case DragEvent.ACTION_DRAG_EXITED:

                      return true;


                  case DragEvent.ACTION_DRAG_ENDED:
                      return true;

                  default:
                      return true;

              }

          }

          Toast.makeText(Nivel_1_ReconocimientoGrafias.this,"aaaa",Toast.LENGTH_SHORT).show();


    return true;



    }


    @Override
    public boolean onTouch(View v, MotionEvent e) {

        if (e.getAction() == MotionEvent.ACTION_DOWN) {

            View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);
            v.startDrag(null, shadowBuilder, v, 0);

            return true;
        } else {
            return false;
        }
    }






}




