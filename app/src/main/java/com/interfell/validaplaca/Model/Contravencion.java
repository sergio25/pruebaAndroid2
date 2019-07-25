package com.interfell.validaplaca.Model;

public class Contravencion {
    private String placa;
    private String fecha;

    public Contravencion(String placa, String fecha) {
        this.placa = placa;
        this.fecha = fecha;
    }


    public Contravencion() {
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}
