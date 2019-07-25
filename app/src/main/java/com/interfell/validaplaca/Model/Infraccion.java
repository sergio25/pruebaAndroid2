package com.interfell.validaplaca.Model;

import io.realm.RealmObject;

public class Infraccion extends RealmObject {
    private int id_placa;
    private String fecha;
    private String hora;

    public int getId_placa() {
        return id_placa;
    }

    public void setId_placa(int id_placa) {
        this.id_placa = id_placa;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }
}
