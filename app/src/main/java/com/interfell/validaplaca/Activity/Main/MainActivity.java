package com.interfell.validaplaca.Activity.Main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.interfell.validaplaca.Adapter.MainAdapter;
import com.interfell.validaplaca.Model.Contravencion;
import com.interfell.validaplaca.R;

import java.util.ArrayList;

import io.realm.Realm;

public class MainActivity extends AppCompatActivity implements MainI {
    private Realm realm;
    private MainPresenter presenter;
    private TextView txt_fecha;
    private RelativeLayout relativeFecha;
    private EditText txt_placa;
    private TextView txt_hora;
    private RelativeLayout relative_hora;
    private int dia_seman = 0;
    private String hora = "";
    private Button button_valida;
    private MainAdapter adapter;
    private ArrayList<Contravencion> arrayList = new ArrayList<>();
    private RecyclerView recycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        realm = Realm.getDefaultInstance();
        presenter = new MainPresenter(this);
        configView();


    }

    private void configView() {
        txt_fecha = (TextView) findViewById(R.id.txt_fecha);
        relativeFecha = (RelativeLayout) findViewById(R.id.relativeFecha);
        txt_hora = (TextView) findViewById(R.id.txt_hora);
        relative_hora = (RelativeLayout) findViewById(R.id.relative_hora);
        button_valida = (Button) findViewById(R.id.button_valida);
        txt_placa= (EditText) findViewById(R.id.txt_placa);
        recycler = (RecyclerView) findViewById(R.id.recycler);


        relativeFecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.obtenerFecha();
            }
        });


        relative_hora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.obtenerHora();
            }
        });


        button_valida.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.validaDatos(txt_placa.getText().toString(), hora,dia_seman);
            }
        });

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recycler.setLayoutManager(layoutManager);
        adapter =
                new MainAdapter(arrayList, R.layout.adapter_contavencion,
                        MainActivity.this);
        recycler.setAdapter(adapter);


    }

    @Override
    public void mustraFecha(String fecha, int dia_seman) {
        txt_fecha.setText(fecha);
        this.dia_seman = dia_seman;
    }

    @Override
    public void muestraHora(String hora) {
        txt_hora.setText(hora);
        this.hora = hora;
    }

    @Override
    public void msg(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showContravencion(ArrayList<Contravencion> arrayList) {
        this.arrayList.clear();
        for (int i = 0; i < arrayList.size(); i++) {
            this.arrayList.add(arrayList.get(i));
        }

        adapter.notifyDataSetChanged();
    }
}
