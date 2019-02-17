package com.example.haircutbe;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

public class ReservationActivity extends AppCompatActivity {
    private ReservationPresenter presenter;
    private ListView listViewReservation;
    private ReservationListAdapter adapter;
    private SessionHandler session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation);

        listViewReservation =  findViewById(R.id.lvReservation);

        session = new SessionHandler(getApplicationContext());
        Klient klient = session.getKlientDetails();

        presenter = new ReservationPresenter(this, klient);
        presenter.setReservationList();

        listViewReservation.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                presenter.setAndLoadReservationDetails(position);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_logout:
                session.logoutUser();
                loadLogin();
                break;
            default:
                break;
        }
        return true;
    }

    public void displayReservations(List<Reservation> reservationList){
        adapter = new ReservationListAdapter(getApplicationContext(),reservationList);
        listViewReservation.setAdapter(adapter);
    }

    public void loadReservationDetails(String data, String name){
        Intent i = new Intent(ReservationActivity.this, ReservationDetailsActivity.class);
        i.putExtra(name, data);
        startActivity(i);
    }

    private void loadLogin(){
        Intent i = new Intent(ReservationActivity.this, LoginActivity.class);
        startActivity(i);
        finish();
    }
}
