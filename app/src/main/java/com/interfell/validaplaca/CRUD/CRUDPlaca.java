package com.interfell.validaplaca.CRUD;

import com.interfell.validaplaca.Model.Infraccion;
import com.interfell.validaplaca.Model.Placa;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;

public class CRUDPlaca {
    private final static int calculateIndex() {
        Realm realm = Realm.getDefaultInstance();
        Number currentIdNum = realm.where(Placa.class).max("id");
        int nextId;
        if (currentIdNum == null) {
            nextId = 0;
        } else {
            nextId = currentIdNum.intValue() + 1;
        }
        return nextId;
    }


    public final static Integer getExistePlaca(String numero_placa) {
        Realm realm = Realm.getDefaultInstance();
        Placa placa = realm.where(Placa.class).equalTo("placa", numero_placa).findFirst();
        if (placa != null) {
            return placa.getId();
        } else {
            return 0;
        }
    }


    public final static void addPlaca(final Placa placa) {
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                int index = CRUDPlaca.calculateIndex();
                Placa realmPlaca = realm.createObject(Placa.class, index);
                realmPlaca.setPlaca(placa.getPlaca());
                realmPlaca.setIs_discapacidad(placa.getIs_discapacidad());
                realmPlaca.setUltimo_digito(placa.getUltimo_digito());
            }
        });
    }


    public final static void addContravencion(final Infraccion infraccion) {
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Infraccion realmPlaca = realm.createObject(Infraccion.class);
                realmPlaca.setFecha(infraccion.getFecha());
                realmPlaca.setHora(infraccion.getHora());
                realmPlaca.setId_placa(infraccion.getId_placa());
            }
        });
    }


    public final static List<Infraccion> getAllInfraccion(int idplaca){
        Realm realm = Realm.getDefaultInstance();
        List<Infraccion> infraccions = realm.where(Infraccion.class).equalTo("id_placa", idplaca).findAll();
        return infraccions;
    }
}
