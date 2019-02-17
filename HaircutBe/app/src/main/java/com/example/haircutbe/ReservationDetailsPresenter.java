package com.example.haircutbe;

import com.google.gson.Gson;



public class ReservationDetailsPresenter {
    private static final String RESERVATIONS_DETAILS = "reservationAsString";
    private Reservation reservation;
    private ReservationDetailsActivity view;


    public ReservationDetailsPresenter(ReservationDetailsActivity view) {
        this.view = view;
    }

    private void getReservationDetails(){
        Gson gson = new Gson();
        String reservationAsString = view.getIntent().getStringExtra(RESERVATIONS_DETAILS);
        reservation = gson.fromJson(reservationAsString, Reservation.class);
    }

    public void setReservationDetails(){
        getReservationDetails();
        view.displayReservationDetails(reservation);
    }
}
