package dislexia.app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.BarLineChartBase;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import dislexia.app.Modelo.Resultado;

public class GraficoTiempo extends AppCompatActivity {

    BarChart graficoBarrasTiempo;


    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grafico_tiempo);

        graficoBarrasTiempo = (BarChart) findViewById(R.id.graficoBarrasTiempo);

        String idPersona = (String) getIntent().getSerializableExtra("idPersona");
        String nombreActividad = (String) getIntent().getSerializableExtra("nombreActividad");
        String nivelSeleccionado = (String) getIntent().getSerializableExtra("nivelSeleccionado");
        ArrayList<Resultado> listaResultado = (ArrayList<Resultado>) getIntent().getSerializableExtra("resultado");

        inicializarFirebase();


        Collections.sort(listaResultado, new Comparator<Resultado>() {
            @Override
            public int compare(Resultado resultado, Resultado t1) {


                return resultado.getFecha().compareTo(t1.getFecha());
            }
        });

        final ArrayList<String> fecha = new ArrayList<>();
        List<BarEntry> list = new ArrayList<>();

                //Agregar a lista
        for(int i=0;i<listaResultado.size();i++) {
                    list.add(new BarEntry(i, Float.parseFloat(listaResultado.get(i).getTiempo())/1000));
                    fecha.add(listaResultado.get(i).getFecha());
                    Log.e("",""+fecha.get(i));

                }


                BarDataSet datos = new BarDataSet(list,"Grafico de Tiempo");

                BarData data = new BarData(datos);
                datos.setColors(ColorTemplate.COLORFUL_COLORS);
                data.setBarWidth(0.9f);

                graficoBarrasTiempo.setData(data);
                graficoBarrasTiempo.setFitBars(true);

                graficoBarrasTiempo.animateXY(2000,2000);

                XAxis xAxis = graficoBarrasTiempo.getXAxis();
                 xAxis.setValueFormatter(new IndexAxisValueFormatter(fecha));

                 xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
                 xAxis.setGranularity(1);
                 xAxis.setGranularityEnabled(true);
                 graficoBarrasTiempo.setDragEnabled(true);
                 graficoBarrasTiempo.setVisibleXRangeMaximum(3);














    }



    private void inicializarFirebase() {
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
