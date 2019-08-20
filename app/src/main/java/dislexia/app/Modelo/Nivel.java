package dislexia.app.Modelo;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import java.io.Serializable;

public class Nivel implements Serializable {

    String numero;
    Class ventanaNivel;



    public Nivel(String numero,Class ventanaNivel){
        this.numero=numero;
        this.ventanaNivel=ventanaNivel;

    }
    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Class getVentanaNivel() {
        return ventanaNivel;
    }

    public void setVentanaNivel(Class ventanaNivel) {
        this.ventanaNivel = ventanaNivel;
    }




}
