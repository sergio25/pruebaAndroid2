package com.interfell.validaplaca.Adapter;

import android.app.Activity;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.interfell.validaplaca.Model.Contravencion;
import com.interfell.validaplaca.R;

import java.util.ArrayList;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainViewHolder> {
    private ArrayList<Contravencion> arrayList = new ArrayList<>();
    private int resource;
    private Activity activity;


    public MainAdapter(ArrayList<Contravencion> agendaListaArrayList, int resource,
                       Activity activity) {
        this.arrayList = agendaListaArrayList;
        this.resource = resource;
        this.activity = activity;
    }

    @Override
    public MainAdapter.MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(resource, parent, false);
        return new MainAdapter.MainViewHolder(view);
    }


    @Override
    public void onBindViewHolder(MainAdapter.MainViewHolder holder, int position) {

        Contravencion contravencion = arrayList.get(position);
        holder.txt_fecha.setText(contravencion.getFecha());
        holder.txt_placa.setText(contravencion.getPlaca());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }


    public class MainViewHolder extends RecyclerView.ViewHolder {
        private TextView txt_placa;
        private TextView txt_fecha;


        public MainViewHolder(View itemView) {
            super(itemView);
            txt_placa = (TextView) itemView.findViewById(R.id.txt_placa);
            txt_fecha = (TextView) itemView.findViewById(R.id.txt_fecha);


        }
    }

}
