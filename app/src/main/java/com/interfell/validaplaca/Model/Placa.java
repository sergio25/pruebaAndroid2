package com.interfell.validaplaca.Model;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Placa extends RealmObject {
    @PrimaryKey
    private int id;
    private String placa;
    private int ultimo_digito;
    private int is_discapacidad;




    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public int getUltimo_digito() {
        return ultimo_digito;
    }

    public void setUltimo_digito(int ultimo_digito) {
        this.ultimo_digito = ultimo_digito;
    }

    public int getIs_discapacidad() {
        return is_discapacidad;
    }

    public void setIs_discapacidad(int is_discapacidad) {
        this.is_discapacidad = is_discapacidad;
    }
}
