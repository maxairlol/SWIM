package com.example.haircutbe;
import android.os.AsyncTask;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class Parser extends AsyncTask<Void, Integer, List> {

    private static final String SALON_NAME = "Salon";
    private static final String SERVICE_NAME = "Service";
    private static final String SERVICE_DATE = "Date";
    private static final String SERVICE_TIME = "Time";
    private static final String SERVICE_PRICE = "Price";
    private static final String SERVICE_DURATION = "Duration";
    private static final String SALON_ADDRESS = "Address";
    private static final String SERVICE_HAIRDRESSER = "Hairdresser";

    private String data;
    private List<Reservation> reservationsList;

    public Parser(String data) {
        this.data = data;
        reservationsList = new ArrayList<>();
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected List<Reservation> doInBackground(Void... params) {
        this.parse();
        return reservationsList;
    }

    @Override
    protected void onPostExecute(List integer) {
        super.onPostExecute(integer);

    }


    private void parse() {
        try {
            JSONArray ja = new JSONArray(data);
            JSONObject jo = null;
            Reservation reservation;

            reservationsList.clear();

            for (int i = 0; i < ja.length(); i++) {
                jo = ja.getJSONObject(i);
                reservation = new Reservation(
                        jo.getString(SALON_NAME),
                        jo.getString(SERVICE_NAME),
                        stringToDate(jo.getString(SERVICE_DATE),"yyy-MM-dd"),
                        stringToDate(jo.getString(SERVICE_TIME),"HH:mm"),
                        Float.parseFloat(jo.getString(SERVICE_PRICE)),
                        Integer.parseInt(jo.getString(SERVICE_DURATION)),
                        jo.getString(SALON_ADDRESS),
                        new Hairdresser(jo.getString(SERVICE_HAIRDRESSER)));
                reservationsList.add(reservation);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private Date stringToDate(String date, String dateFormmat) {
        SimpleDateFormat formatter = new SimpleDateFormat(dateFormmat);
        Date result = null;
        try {
            result = formatter.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result;
    }

}