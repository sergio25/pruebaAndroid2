package com.interfell.validaplaca.Activity.Main;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.widget.DatePicker;
import android.widget.TimePicker;

import com.interfell.validaplaca.Activity.Main.MainActivity;
import com.interfell.validaplaca.CRUD.CRUDPlaca;
import com.interfell.validaplaca.Model.Contravencion;
import com.interfell.validaplaca.Model.Infraccion;
import com.interfell.validaplaca.Model.Placa;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import io.realm.Realm;

public class MainPresenter {
    private MainActivity activity;
    private Calendar calendar;
    private Integer LUNES = 2;
    private Integer MARTES = 3;
    private Integer MIERCOLES = 4;
    private Integer JUEVES = 5;
    private Integer VIERNES = 6;
    private Date HORA_INICIO_M;
    private Date HORA_INICIO_T;
    private Date HORA_FIN_M;
    private Date HORA_FIN_T;
    private CRUDPlaca crudPlaca;
    private Realm realm;

    public MainPresenter(MainActivity activity) {
        this.activity = activity;
        realm = Realm.getDefaultInstance();
        asiganaHoras();
    }


    private void asiganaHoras() {
        try {
            DateFormat dateFormat = new SimpleDateFormat("hh:mm");
            HORA_INICIO_M = dateFormat.parse("7:00");
            HORA_INICIO_T = dateFormat.parse("16:00");
            HORA_FIN_M = dateFormat.parse("9:30");
            HORA_FIN_T = dateFormat.parse("19:00");
        } catch (ParseException parseException) {
            System.out.println("ERROR" + parseException.toString());
        }
    }

    public void obtenerFecha() {
        calendar = java.util.Calendar.getInstance();
        int mes = calendar.get(Calendar.MONTH);
        int dia = calendar.get(Calendar.DAY_OF_MONTH);
        int anio = calendar.get(Calendar.YEAR);
        DatePickerDialog recogerFecha = new DatePickerDialog(activity, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                Calendar c = Calendar.getInstance();
                Date date = new Date(year, month - 1, dayOfMonth - 1);
                c.setTime(date);
                int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
                String fecha = dayOfMonth + "-" + month + "-" + year;

                activity.mustraFecha(fecha, dayOfWeek);
            }
        }, anio, mes, dia);

        recogerFecha.show();

    }


    public void obtenerHora() {
        calendar = java.util.Calendar.getInstance();
        final int hora = calendar.get(Calendar.HOUR_OF_DAY);
        final int minuto = calendar.get(Calendar.MINUTE);
        TimePickerDialog recogerFecha = new TimePickerDialog(activity, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                activity.muestraHora(hourOfDay + ":" + minute);
            }


        }, hora, minuto, false);

        recogerFecha.show();

    }

    public void validaDatos(String placa, String hora, int day) {
        if (placa.equals("") || hora.equals("") || day == 0) {
            activity.msg("Datos incompletos");
        } else {

            try {
                Integer ultimo_digito = Integer.parseInt(placa.substring(placa.length() - 1));
                validaPlaca(placa, ultimo_digito);
                Boolean valida_dia = validaDia(day, ultimo_digito);
                ArrayList<Contravencion> contravencions = new ArrayList<>();

                if (valida_dia == false) {
                    messageCirculacion(ultimo_digito);
                    activity.showContravencion(contravencions);
                } else {
                    Boolean valida_hora = validaHora(hora);
                    if (valida_hora == true) {
                        activity.msg("NO tiene permitido circular, de acuerdo a la normativa");
                        insertContravencion(placa);
                    } else {
                        messageCirculacion(ultimo_digito);
                        activity.showContravencion(contravencions);
                    }
                }
            } catch (Exception e) {
                System.out.println("ERROR" + e.toString());
                activity.msg("Formato incorrecto de placa");
            }

        }
    }


    private Boolean validaDia(int day, int digito) {
        if ((digito == 1 || digito == 2) && day == 2) {
            return true;
        } else if ((digito == 3 || digito == 4) && day == 3) {
            return true;
        } else if ((digito == 5 || digito == 6) && day == 4) {
            return true;
        } else if ((digito == 7 || digito == 8) && day == 5) {
            return true;
        } else if ((digito == 9 || digito == 0) && day == 6) {
            return true;
        } else {
            return false;
        }
    }


    private void messageCirculacion( int digito) {
        if ((digito == 1 || digito == 2) ) {
            activity.msg("De acuerdo a la normativa, el vehículo\n" +
                    "no podrá circular en la ciudad los días lunes desde las 07:00 hasta las 09:30 y en la tarde desde las\n" +
                    "16:00 hasta las 19:30. Fuera de este horario, el vehículo puede circular sin problemas dentro de la\n" +
                    "ciudad.");
        } else if ((digito == 3 || digito == 4) ) {
            activity.msg("De acuerdo a la normativa, el vehículo\n" +
                    "no podrá circular en la ciudad los días Martes desde las 07:00 hasta las 09:30 y en la tarde desde las\n" +
                    "16:00 hasta las 19:30. Fuera de este horario, el vehículo puede circular sin problemas dentro de la\n" +
                    "ciudad.");
        } else if ((digito == 5 || digito == 6) ) {
            activity.msg("De acuerdo a la normativa, el vehículo\n" +
                    "no podrá circular en la ciudad los días Miercoles desde las 07:00 hasta las 09:30 y en la tarde desde las\n" +
                    "16:00 hasta las 19:30. Fuera de este horario, el vehículo puede circular sin problemas dentro de la\n" +
                    "ciudad.");
        } else if ((digito == 7 || digito == 8) ) {
            activity.msg("De acuerdo a la normativa, el vehículo\n" +
                    "no podrá circular en la ciudad los días Jueves desde las 07:00 hasta las 09:30 y en la tarde desde las\n" +
                    "16:00 hasta las 19:30. Fuera de este horario, el vehículo puede circular sin problemas dentro de la\n" +
                    "ciudad.");
        } else if ((digito == 9 || digito == 0) ) {
            activity.msg("De acuerdo a la normativa, el vehículo\n" +
                    "no podrá circular en la ciudad los días Viernes desde las 07:00 hasta las 09:30 y en la tarde desde las\n" +
                    "16:00 hasta las 19:30. Fuera de este horario, el vehículo puede circular sin problemas dentro de la\n" +
                    "ciudad.");
        } else {
            activity.msg("Error el ultimo digito no esta incluido en la normativa");
        }
    }

    private boolean validaHora(String hora) {
        try {
            DateFormat dateFormat = new SimpleDateFormat("hh:mm");
            Date hora_Captura = dateFormat.parse(hora);
            System.out.println("ERROR+hora" + hora);
            if ((hora_Captura.after(HORA_INICIO_M) && hora_Captura.before(HORA_FIN_M))
                    || (hora_Captura.after(HORA_INICIO_T) && hora_Captura.before(HORA_FIN_T))) {
                System.out.println("ERROR+entro aqui");
                return true;
            }
        } catch (ParseException parseException) {
            System.out.println("ERROR" + parseException.toString());
        }

        return false;
    }

    private void validaPlaca(String placa, int ultimo_digito) {

        Integer valida = CRUDPlaca.getExistePlaca(placa);
        if (valida == 0) {
            Placa placaObject = new Placa();
            placaObject.setUltimo_digito(ultimo_digito);
            placaObject.setIs_discapacidad(0);
            placaObject.setPlaca(placa);
            CRUDPlaca.addPlaca(placaObject);

        }
    }


    private void insertContravencion(String placa) {
        ArrayList<Contravencion> contravencions = new ArrayList<>();
        DateFormat dateFormat = new SimpleDateFormat("hh:mm");
        Integer valida = CRUDPlaca.getExistePlaca(placa);
        String hora = dateFormat.format(new Date());
        String fecha = getFecha();
        Infraccion infraccion = new Infraccion();
        infraccion.setId_placa(valida);
        infraccion.setHora(hora);
        infraccion.setFecha(fecha);
        CRUDPlaca.addContravencion(infraccion);

        List<Infraccion> list = CRUDPlaca.getAllInfraccion(valida);
        for (Infraccion infraccionObject : list){
            Contravencion contravencion = new Contravencion(placa,infraccionObject.getFecha()+" "+infraccionObject.getHora());
            contravencions.add(contravencion);
        }
        activity.showContravencion(contravencions);

    }

    private String getFecha(){
        calendar = java.util.Calendar.getInstance();
        int mes = calendar.get(Calendar.MONTH);
        int dia = calendar.get(Calendar.DAY_OF_MONTH);
        int anio = calendar.get(Calendar.YEAR);
        mes = mes +1;
        return dia+"-"+mes+"-"+anio;
    }
}
