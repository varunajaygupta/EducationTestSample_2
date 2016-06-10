package com.example.novo.educationtestsample.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.novo.educationtestsample.R;
import com.example.novo.educationtestsample.Utils.AppInfo;
import com.example.novo.educationtestsample.Utils.ConstURL;
import com.example.novo.educationtestsample.Utils.DateTimeUtils;
import com.example.novo.educationtestsample.Utils.PostHitAsyncTask;
import com.example.novo.educationtestsample.Utils.Utils;
import com.example.novo.educationtestsample.interfaces.ResponseCallback;
import com.example.novo.educationtestsample.models.LoginRequest;
import com.example.novo.educationtestsample.models.LoginResponse;
import com.google.gson.Gson;

import java.util.Calendar;
import java.util.Date;

public class SplashScreen extends AppCompatActivity {

    Context context;
    static final String TAG=SplashScreen.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        context = this;

        Log.e("Date",String.valueOf(System.currentTimeMillis()));
        checkForStoredCredentials();

    }

    private void checkForStoredCredentials() {
        if (!AppInfo.DEFAULT_VALUE.equalsIgnoreCase(AppInfo.getIsKeepMeSignIn((Activity) context))&&!AppInfo.DEFAULT_VALUE.equalsIgnoreCase(AppInfo.getUserId((Activity) context)) && !AppInfo.DEFAULT_VALUE.equalsIgnoreCase(AppInfo.getUserPassword((Activity) context))) {
           Log.e("Batchid",AppInfo.getBatchId(context));
           Log.e("Coachingid",AppInfo.getCoachingId(context));
            hitLogin();
        }else{
            startActivity(new Intent(context, LoginScreen.class));
        }
    }

    private void hitLogin() {
        String requestJSON = new Gson().toJson(new LoginRequest(AppInfo.getUserId((Activity) context), AppInfo.getUserPassword((Activity) context))).toString();
        PostHitAsyncTask postHitAsyncTask = new PostHitAsyncTask(ConstURL.LOGIN, requestJSON, new ResponseCallback() {
            @Override
            public void onResult(String response) {
                if (!TextUtils.isEmpty(response)) {
                    if (context.getString(R.string.invalid_login).equalsIgnoreCase(response)) {
                        startActivity(new Intent(context, LoginScreen.class));
                    } else {
                        LoginResponse loginResponse = new Gson().fromJson(response, LoginResponse.class);
                        if (loginResponse.getResponse().getLoginid() != null) {
                            if (loginResponse.getResponse().getStudent_firstname() == null) {
                                startActivity(new Intent(context, LoginScreen.class));
                            } else {
                                Toast.makeText(SplashScreen.this, "Login Successfull", Toast.LENGTH_SHORT).show();
                                Log.e(TAG,"Login Successfull");
                                Intent intent = new Intent(context, MainActivity.class);
                                startActivity(intent);
                            }
                        } else {
                            startActivity(new Intent(context, LoginScreen.class));
                        }
                    }

                } else {
                    startActivity(new Intent(context, LoginScreen.class));
                }
            }

        });
         postHitAsyncTask.execute();
    }

}