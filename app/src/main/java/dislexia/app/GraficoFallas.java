package dislexia.app;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import dislexia.app.Modelo.Resultado;
import dislexia.app.R;

public class GraficoFallas extends AppCompatActivity {

    BarChart graficoBarrasFallas;


    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grafico_fallas);

        graficoBarrasFallas = (BarChart) findViewById(R.id.graficoBarrasFallas);

        String idPersona = (String) getIntent().getSerializableExtra("idPersona");
        String nombreActividad = (String) getIntent().getSerializableExtra("nombreActividad");
        String nivelSeleccionado = (String) getIntent().getSerializableExtra("nivelSeleccionado");
        ArrayList<Resultado> listaResultado = (ArrayList<Resultado>) getIntent().getSerializableExtra("resultado");
        ArrayList<String> fecha = new ArrayList<>();


        Collections.sort(listaResultado, new Comparator<Resultado>() {
            @Override
            public int compare(Resultado resultado, Resultado t1) {


                return resultado.getFecha().compareTo(t1.getFecha());
            }
        });


                //Valor entrada
                List<BarEntry> list = new ArrayList<>();

                //Agregar a lista
                for(int i=0;i<listaResultado.size();i++) {
                    Log.e(""," entro for"+listaResultado.get(i).getTiempo());
                    list.add(new BarEntry(i, listaResultado.get(i).getCantidadFallas()));
                    fecha.add(listaResultado.get(i).getFecha());

                }
                BarDataSet datos = new BarDataSet(list,"Grafico de Fallas");

                BarData data = new BarData(datos);
                datos.setColors(ColorTemplate.COLORFUL_COLORS);
                data.setBarWidth(0.9f);

                graficoBarrasFallas.setData(data);
                graficoBarrasFallas.setFitBars(true);
                graficoBarrasFallas.animateXY(2000,2000);

                XAxis xAxis = graficoBarrasFallas.getXAxis();
                 xAxis.setValueFormatter(new IndexAxisValueFormatter(fecha));

                xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
                xAxis.setDrawGridLines(false);
                xAxis.setGranularity(1);
                xAxis.setGranularityEnabled(true);

                graficoBarrasFallas.setDragEnabled(true);
                graficoBarrasFallas.setVisibleXRangeMaximum(3);













    }


    private void inicializarFirebase() {
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();


    }


    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }


}
