package com.example.haircutbe;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    EditText edUserName, edUserPwd;
    private String username;
    private String user_pwd;
    private LoginPresenter presenter;
    private SessionHandler session;
    private static final String ERROR_INTERNET_CONNECTION = "Network is not available";
    private static final String ERROR_INTERNET_CONNECTION_DATA = "Please check your internet connection!!!";
    private static final String ERROR_INTERNET_OK = "OK";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        checkSession();

        setContentView(R.layout.activity_login);

        presenter = new LoginPresenter(this);

        setViews();
    }

    private void setViews(){
        edUserName = (EditText) findViewById(R.id.etUserName);
        edUserPwd = (EditText) findViewById(R.id.etUserPwd);
    }

    private void checkSession(){
        session = new SessionHandler(getApplicationContext());
        if(session.isLoggedIn()){
            loadReservation();
        }
    }

    public void onLogin(View view) {
        boolean connection, available;
        connection = presenter.checkNetworkConnection();
        available = presenter.isNetworkAvailable(connection);
        if(available) {
            username = edUserName.getText().toString();
            user_pwd = edUserPwd.getText().toString();

            presenter.signIn(username, user_pwd);
        }
    }

    public void startSession(String username){
        session.loginUser(username);
        loadReservation();
    }

    public void createToast(String result){
        Toast toast = Toast.makeText(getApplicationContext(), getApplicationContext().getResources().getIdentifier(result,
                "string", getApplicationContext().getPackageName()), Toast.LENGTH_LONG);
        toast.setGravity(Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 10);
        toast.show();
    }

    private void loadReservation() {
        Intent i = new Intent(getApplicationContext(), ReservationActivity.class);
        startActivity(i);
        finish();
    }

    public void loadRegister(View view) {
        Intent i = new Intent(getBaseContext(), RegisterActivity.class);
        startActivity(i);
        finish();
    }

    public void displayNetworkIsNotAvailable(){
        AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
        builder.setTitle(ERROR_INTERNET_CONNECTION)
                .setMessage(ERROR_INTERNET_CONNECTION_DATA)
                .setIcon(R.drawable.internet_error_action_name)
                .setCancelable(false)
                .setNegativeButton(ERROR_INTERNET_OK,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
        AlertDialog alert = builder.create();
        alert.show();
    }
}
