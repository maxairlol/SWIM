package com.example.haircutbe;

import android.os.AsyncTask;


import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class DBConnection extends AsyncTask<String,Integer,String> {
    private static final String TYPE_LOGIN = "login";
    private static final String USER_NAME = "user_name";
    private static final String USER_PWD = "user_pwd";
    private static final String REQUEST_METHOD = "POST";
    private static final String UNICODE = "UTF-8";

    private String address_url;

    public DBConnection(String addressURL){
        address_url = addressURL;
    }

    @Override
    protected String doInBackground(String... params) {
        String data = downloadData(params);
        return data;
    }

    @Override
    protected void onPostExecute(String s) {

    }

    private String downloadData(String[] params)
    {
        InputStream inputStream = null;
        String line = null;
        String post_data;
        BufferedWriter bufferedWriter;

        try {
            URL url=new URL(address_url);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

            httpURLConnection.setRequestMethod(REQUEST_METHOD);
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            OutputStream outputStream = httpURLConnection.getOutputStream();

            if(params[0].equals(TYPE_LOGIN)){
                String user_name = params[1];
                String user_pwd = params[2];
                bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, UNICODE));
                post_data = URLEncoder.encode(USER_NAME,UNICODE) + "="
                        + URLEncoder.encode(user_name,UNICODE) + "&"
                        + URLEncoder.encode(USER_PWD, UNICODE) + "="
                        + URLEncoder.encode(user_pwd,UNICODE);
            } else {
                String user_name = params[1];
                bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, UNICODE));
                post_data = URLEncoder.encode(USER_NAME,UNICODE) + "="
                        + URLEncoder.encode(user_name,UNICODE);
            }

            bufferedWriter.write(post_data);
            bufferedWriter.flush();
            bufferedWriter.close();
            outputStream.close();


            inputStream = new BufferedInputStream(httpURLConnection.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            StringBuilder stringBuffer = new StringBuilder();

            while ((line = bufferedReader.readLine()) != null) {
                stringBuffer.append(line);
            }

            return stringBuffer.toString();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(inputStream != null)
            {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}
