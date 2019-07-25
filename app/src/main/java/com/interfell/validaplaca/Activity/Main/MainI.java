package com.interfell.validaplaca.Activity.Main;

import com.interfell.validaplaca.Model.Contravencion;

import java.util.ArrayList;

public interface MainI {

    void mustraFecha (String fecha, int dia_seman);
    void muestraHora(String hora);
    void msg(String msg);
    void showContravencion(ArrayList<Contravencion> arrayList);
}
