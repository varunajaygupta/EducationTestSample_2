package com.example.novo.educationtestsample;


import android.os.AsyncTask;

import com.example.novo.educationtestsample.interfaces.ResponseCallback;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

/**
 * Created by Deepak Sharma on 9/17/2015. modified by faisal khan
 */
public class MyAsyncTask extends AsyncTask<String, String, String> {

    private ResponseCallback callback;
    private String url;
    private String requestJson;
    private String TAG = MyAsyncTask.class.getSimpleName();

    public MyAsyncTask(String url, String requestJson, ResponseCallback callback) {
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
//        try {
//            url = new URL(this.url);
//
//            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//            conn.setReadTimeout(15000);
//            conn.setConnectTimeout(15000);
//            conn.setRequestMethod("POST");
//            conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
//            conn.setDoInput(true);
//            conn.setDoOutput(true);
//
//            //disableSSLCertificateChecking();
//            OutputStream os = conn.getOutputStream();
//            BufferedWriter writer = new BufferedWriter(
//                    new OutputStreamWriter(os, "UTF-8"));
//            writer.write(requestJson);
//
//            writer.flush();
//            writer.close();
//            os.close();
//            int responseCode=conn.getResponseCode();
//
//            if (responseCode == HttpsURLConnection.HTTP_OK) {
//                String line;
//                BufferedReader br=new BufferedReader(new InputStreamReader(conn.getInputStream()));
//                while ((line=br.readLine()) != null) {
//                    response+=line;
//                }
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

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
