package com.example.haircutbe;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class ReservationPresenter {
    private static final String ADDRESS_URL ="http://192.168.43.174/HaircutBe/reservations.php";
    private static final String TYPE_RESERVATION = "reservation";
    private static final String RESERVATIONS_DETAILS = "reservationAsString";
    private List<Reservation> reservationList;
    private ReservationActivity view;
    private Klient klient;

    public void setKlient(Klient klient) {
        this.klient = klient;
    }

    public ReservationPresenter(){}
    public ReservationPresenter(ReservationActivity view, Klient klient) {
        this.view = view;
        this.klient = klient;
        this.reservationList = new ArrayList<>();
    }

    /**
     * Set and display reservations list
     * If user has not reservation, display nothing
     */
    public void setReservationList(){
        String data;
        data = getReservationsData();
        parseReservationData(data);

        if(!reservationList.isEmpty()){
            view.displayReservations(reservationList);
        }
    }

    public List<Reservation> getReservationList() {
        return reservationList;
    }

    /**
     * Connect to database and get reservation data from database
     * @return String in JSON
     */
    public String getReservationsData(){
        String result = null;
        DBConnection dbConnection = new DBConnection(ADDRESS_URL);
        try {
            result = dbConnection.execute(TYPE_RESERVATION,klient.getUsername()).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Parse JSON and set into reservation list
     * @param data JSON
     */
    public void parseReservationData(String data){
        if(data != null)
        {
            Parser parser = new Parser(data);
            try {
                reservationList = parser.execute().get();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     *
     * @param idReservation ID of user selected reservation
     */
    public void setAndLoadReservationDetails(int idReservation){
        Reservation reservation = reservationList.get(idReservation);
        Gson gson = new Gson();
        String reservationAsString = gson.toJson(reservation);
        view.loadReservationDetails(reservationAsString,RESERVATIONS_DETAILS);
    }
}
