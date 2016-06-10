package com.example.novo.educationtestsample.Utils;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import com.example.novo.educationtestsample.interfaces.ResponseCallback;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by Varun on 5/30/2016.
 */
public class GetHitAsyncTask extends AsyncTask<String,String,String> {


    private ResponseCallback callback;
    private String url;
    private String requestJson;
    private static final String TAG = "GetHitAsyncTask";

    public GetHitAsyncTask(String url, String requestJson, ResponseCallback callback) {
        this.callback = callback;
        this.url = url;
        this.requestJson=Uri.encode(requestJson);
    }
    @Override
    protected String doInBackground(String... params) {
        URL url;
        String response = "";
        try {

            url = new URL(this.url+requestJson);
            Log.e(TAG, "doInBackground: url: " + url);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(15000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("GET");
//            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoInput(true);
       //     conn.setDoOutput(true);

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

        Log.e(TAG, "doInBackground: resp; " + response);
        return response;
    }

    @Override
    protected void onPostExecute(String response) {
        super.onPostExecute(response);
        callback.onResult(response);
    }
}
