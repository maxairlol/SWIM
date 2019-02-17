package com.example.haircutbe;

import android.os.Parcel;
import android.os.Parcelable;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Reservation implements Parcelable {
    private String salon_name;
    private String service_name;
    private String service_address;
    private Klient klient;
    private Hairdresser hairdresser;
    private Date date;
    private Date time;
    private float price;
    private int duration;

    public Reservation(String salon_name, String service_name, Date date,
                       Date time, float price, int duration, String service_address, Hairdresser hairdresser){
        this.salon_name = salon_name;
        this.service_name = service_name;
        this.date = date;
        this.time = time;
        this.price = price;
        this.duration = duration;
        this.service_address = service_address;
        this.hairdresser = hairdresser;
    }

    public String getSalon_name() {
        return salon_name;
    }

    public void setSalon_name(String salon_name) {
        this.salon_name = salon_name;
    }

    public String getService_name() {
        return service_name;
    }

    public void setService_name(String service_name) {
        this.service_name = service_name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public float getPrice() {
        return price;
    }

    public Klient getKlient() {
        return klient;
    }

    public void setKlient(Klient klient) {
        this.klient = klient;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getService_address() {
        return service_address;
    }

    public void setService_address(String service_address) {
        this.service_address = service_address;
    }

    public Hairdresser getHairdresser() {
        return hairdresser;
    }

    public void setHairdresser(Hairdresser hairdresser) {
        this.hairdresser = hairdresser;
    }

    public int getDayFromDate(){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    public String getMonthName(){
        String[] monthName = {"STY", "LUT",
                "MAR", "KWI", "MAY", "CZE", "LIP",
                "SIE", "WRZ", "PAZ", "LIS",
                "GRU"};
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        String month = monthName[calendar.get(Calendar.MONTH)];
        return month;
    }

    public String dateToString(){
        DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
        return df.format(date);
    }

    public String dateAndTimeToString(){
        return dateToString() + ", " + timeToString();
    }

    public String timeToString(){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(time);
        int hrs = calendar.get(Calendar.HOUR_OF_DAY);
        int mnts = calendar.get(Calendar.MINUTE);
        String result = hrs + ":" + (mnts == 0 ? "00" : mnts);
        return  result;
    }

    public String priceToString(){
        return Float.toString(getPrice()) + "zl";
    }

    public String durationtoString(){
        return Integer.toString(duration) + " minut";
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(salon_name);
        dest.writeString(service_name);
        dest.writeFloat(price);
    }

    private Reservation(Parcel in) {
        salon_name = in.readString();
        service_name = in.readString();
        price = in.readFloat();
    }

    public static final Creator<Reservation> CREATOR = new Creator<Reservation>() {
        @Override
        public Reservation createFromParcel(Parcel in) {
            return new Reservation(in);
        }

        @Override
        public Reservation[] newArray(int size) {
            return new Reservation[size];
        }
    };
}
