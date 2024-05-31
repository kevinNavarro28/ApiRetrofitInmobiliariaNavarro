package com.example.tp_inmobiliaria_navarro.ui.contratos;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Formateo_Date {
    public static String formatDate(String dateString) {
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        SimpleDateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = null;
        try {
            date = inputFormat.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date != null ? outputFormat.format(date) : "";
    }
}
