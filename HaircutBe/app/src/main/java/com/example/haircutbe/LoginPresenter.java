package com.example.haircutbe;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.util.concurrent.ExecutionException;

public class LoginPresenter {
    private static final String KEY_EMPTY = "";
    private static final String ERROR_EMPTY = "error_empty";
    private static final String TYPE_LOGIN = "login";
    private static final String LOGIN_SUCCESS = "login_success";
    private static final String ADDRESS_URL = "http://192.168.43.174/HaircutBe/includes/login.inc.php";

    private Login login;
    private LoginActivity view;

    public LoginPresenter(LoginActivity view) {
        this.login = new Login();
        this.view = view;
    }


    public String signIn(String username, String user_pwd) {
        login.setUsername(username);
        login.setUser_pwd(user_pwd);
        return signIn(login);
    }

    private boolean validateInputs(Login login) {
        if (KEY_EMPTY.equals(login.getUsername()) || KEY_EMPTY.equals(login.getUser_pwd())) {
            return false;
        }
        return true;
    }

    /**
     *
     * @param login login obj, holds username and password
     * @return JSON
     */
    private String signIn(Login login) {
        String result = KEY_EMPTY;
        if (validateInputs(login)) {
            DBConnection dbConnection = new DBConnection(ADDRESS_URL);
            try {
                result = dbConnection.execute(TYPE_LOGIN, login.getUsername(), login.getUser_pwd()).get();
                if (result.equals(LOGIN_SUCCESS)) {
                    view.startSession(login.getUsername());
                }
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            result = ERROR_EMPTY;
        }
        if (!result.equals(LOGIN_SUCCESS)) view.createToast(result);
        return result;
    }

    public boolean checkNetworkConnection() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) view.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public boolean isNetworkAvailable(boolean available){
        if(!available){
            view.displayNetworkIsNotAvailable();
            return false;
        }
        return true;
    }
}
