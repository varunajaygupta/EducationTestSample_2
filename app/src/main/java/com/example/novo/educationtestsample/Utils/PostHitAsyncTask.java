package com.example.novo.educationtestsample.Utils;


import android.os.AsyncTask;

import com.example.novo.educationtestsample.interfaces.ResponseCallback;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class PostHitAsyncTask extends AsyncTask<String, String, String> {

    private ResponseCallback callback;
    private String url;
    private String requestJson;
    private String TAG = PostHitAsyncTask.class.getSimpleName();

    public PostHitAsyncTask(String url, String requestJson, ResponseCallback callback) {
        this.callback = callback;
        this.url = url;
        this.requestJson = requestJson;
    }

    @Override
    protected void onPreExecute() {
    }

    @Override
    protected String doInBackground(String... params) {

        URL url;
        String response = "";
        try {
            url = new URL(this.url);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(15000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
            conn.setDoInput(true);
            conn.setDoOutput(true);


            OutputStream os = conn.getOutputStream();
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(os, "UTF-8"));
            writer.write(requestJson);

            writer.flush();
            writer.close();
            os.close();
            int responseCode=conn.getResponseCode();

            if (responseCode == HttpsURLConnection.HTTP_OK) {
                String line;
                BufferedReader br=new BufferedReader(new InputStreamReader(conn.getInputStream()));
                while ((line=br.readLine()) != null) {
                    response+=line;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return response;
    }

    @Override
    protected void onPostExecute(String result) {
        callback.onResult(result);
    }

    @Override
    protected void onProgressUpdate(String... text) {
    }



}
