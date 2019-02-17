package com.example.haircutbe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ReservationDetailsActivity extends AppCompatActivity {
    TextView tvDateTime,tvSalon,tvAddress,tvService,tvHairDresser,tvPrice, tvDuration;
    ImageView imageView;
    Button btnCancel;
    private ReservationDetailsPresenter presenter;
    private SessionHandler session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation_details);

        session = new SessionHandler(getApplicationContext());

        presenter = new ReservationDetailsPresenter(this);
        presenter.setReservationDetails();
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

    private void setViews(){
        tvDateTime = findViewById(R.id.tvDateTime);
        tvSalon = findViewById(R.id.tvSalon);
        tvAddress = findViewById(R.id.tvAddress);
        tvService = findViewById(R.id.tvService);
        tvHairDresser = findViewById(R.id.tvHairDresser);
        tvPrice = findViewById(R.id.tvPrice);
        tvDuration = findViewById(R.id.tvDuration);
        btnCancel = findViewById(R.id.btnCancel);
        imageView = findViewById(R.id.ivImage);
    }

    public void displayReservationDetails(Reservation reservation){
        setViews();
        tvDateTime.setText(reservation.dateAndTimeToString());
        tvSalon.setText(reservation.getSalon_name());
        tvService.setText(reservation.getService_name());
        tvPrice.setText(reservation.priceToString());
        tvDuration.setText(reservation.durationtoString());
        tvAddress.setText(reservation.getService_address());
        tvHairDresser.setText(reservation.getHairdresser().getName());
        imageView.setImageResource(R.drawable.image);
    }

    private void loadLogin(){
        Intent i = new Intent(ReservationDetailsActivity.this, LoginActivity.class);
        startActivity(i);
        finish();
    }
}
