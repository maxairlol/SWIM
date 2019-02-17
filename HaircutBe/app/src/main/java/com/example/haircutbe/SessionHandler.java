package com.example.haircutbe;
import android.content.Context;
import android.content.SharedPreferences;
import java.util.Date;

public class SessionHandler {
    private static final String PREF_NAME = "UserSession";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_EXPIRES = "expires";
    private static final String KEY_EMPTY = "";
    private Context mContext;
    private SharedPreferences.Editor mEditor;
    private SharedPreferences mPreferences;

    public SessionHandler(Context mContext) {
        this.mContext = mContext;
        mPreferences = mContext.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        this.mEditor = mPreferences.edit();
    }

    /**
     * Logs in the user by saving user details and setting session
     *
     * @param username
     * */
    public void loginUser(String username) {
        mEditor.putString(KEY_USERNAME, username);
        Date date = new Date();

        //Set user session for next 7 days
        long millis = date.getTime() + (7 * 24 * 60 * 60 * 1000);
        mEditor.putLong(KEY_EXPIRES, millis);
        mEditor.commit();
    }

    /**
     * Checks whether user is logged in
     *
     * @return
     */
    public boolean isLoggedIn() {
        Date currentDate = new Date();
        long millis = mPreferences.getLong(KEY_EXPIRES, 0);

        if (millis == 0) {
            return false;
        }
        Date expiryDate = new Date(millis);

        return currentDate.before(expiryDate);
    }

    /**
     * Fetches and returns user details
     *
     * @return klient hold info about clients
     */
    public Klient getKlientDetails() {
        //Check if user is logged in first
        if (!isLoggedIn()) {
            return null;
        }
        Klient klient = new Klient();
        klient.setUsername(mPreferences.getString(KEY_USERNAME, KEY_EMPTY));
        klient.setSessionExpiryDate(new Date(mPreferences.getLong(KEY_EXPIRES, 0)));

        return klient;
    }

    /**
     * Logs out user by clearing the session
     */
    public void logoutUser(){
        mEditor.clear();
        mEditor.commit();
    }


}
