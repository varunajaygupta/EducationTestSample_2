package com.example.novo.educationtestsample.activities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.example.novo.educationtestsample.Utils.AppInfo;
import com.example.novo.educationtestsample.Utils.ConstURL;
import com.example.novo.educationtestsample.Utils.PostHitAsyncTask;
import com.example.novo.educationtestsample.R;
import com.example.novo.educationtestsample.Utils.Utils;
import com.example.novo.educationtestsample.interfaces.ResponseCallback;
import com.example.novo.educationtestsample.models.LoginRequest;
import com.example.novo.educationtestsample.models.LoginResponse;
import com.google.gson.Gson;

public class LoginScreen extends AppCompatActivity implements View.OnClickListener {
    private EditText etUsername;
    private EditText etPassword;
    private TextView tvInvalidCredentials;
    private TextView tvEmptyUsername;
    private TextView tvEmptyPassword;
    private TextView tvForgotPassword;
    private Button login;
    private ProgressDialog progressDialog;
    private static final String TAG = LoginScreen.class.getSimpleName();
    private CheckBox cbKeepMeSignedIn;
    private String userName;
    private String password;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        context = this;
        initializeViews();
        login.setOnClickListener(this);
        userName="studentc1t1";
        password="qkpak4";
        cbKeepMeSignedIn.setChecked(true);
        hitLogin();
    }

    public void initializeViews() {

        etUsername = (EditText) findViewById(R.id.etLoginUsername);
        etPassword = (EditText) findViewById(R.id.etLoginPassword);
        tvInvalidCredentials = (TextView) findViewById(R.id.tvInvalidCredentials);
        tvEmptyUsername = (TextView) findViewById(R.id.tvEmptyUsername);
        tvEmptyPassword = (TextView) findViewById(R.id.tvEmptyPassword);
        tvForgotPassword = (TextView) findViewById(R.id.tvForgotPassword);
        cbKeepMeSignedIn = (CheckBox) findViewById(R.id.cbKeepMeSignedIn);
        login = (Button) findViewById(R.id.btnLogin);
        tvForgotPassword.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnLogin:
                userName = etUsername.getText().toString().trim();
                password = etPassword.getText().toString().trim();
                resetAlertMessages();
                validateInputFeildsAndLogin();

        }
    }

    private void resetAlertMessages() {
        tvEmptyUsername.setVisibility(View.INVISIBLE);
        tvEmptyPassword.setVisibility(View.INVISIBLE);
        tvInvalidCredentials.setVisibility(View.INVISIBLE);
    }

    private void validateInputFeildsAndLogin() {


        if (userName.isEmpty() && password.isEmpty()) {
            tvEmptyUsername.setVisibility(View.VISIBLE);
            tvEmptyPassword.setVisibility(View.VISIBLE);
        } else if (userName.isEmpty()) {
            tvEmptyUsername.setVisibility(View.VISIBLE);
        } else if (password.isEmpty()) {
            tvEmptyPassword.setVisibility(View.VISIBLE);
        } else if (!userName.isEmpty() && !password.isEmpty()) {
            hitLogin();
        }
    }

    private void hitLogin() {
        String requestJSON = new Gson().toJson(new LoginRequest(userName, password)).toString();
        progressDialog = ProgressDialog.show(this, "Please wait",
                "Loging in", true);
         final PostHitAsyncTask loginAsyncTask = new PostHitAsyncTask(ConstURL.LOGIN, requestJSON, new ResponseCallback() {
            @Override
            public void onResult(String response) {
                progressDialog.dismiss();
                if (!TextUtils.isEmpty(response)) {
                    if (context.getString(R.string.invalid_login).equalsIgnoreCase(response)) {
                        tvInvalidCredentials.setVisibility(View.VISIBLE);
                        etUsername.setText("");
                        etPassword.setText("");
                    } else {
                        LoginResponse loginResponse = new Gson().fromJson(response, LoginResponse.class);
                        if (loginResponse.getResponse().getLoginid() != null) {
                            if (loginResponse.getResponse().getStudent_firstname() == null) {
                                Utils.showAlert((Activity) context, getString(R.string.first_time_login), getString(R.string.first_time_login_message), null, false, false);
                            } else {
                                if (cbKeepMeSignedIn.isChecked()) {
                                    storeCredentials(loginResponse.getResponse().getCoaching_id(),loginResponse.getResponse().getBatch_id());
                                }
                                Intent intent= new Intent(context,MainActivity.class);
                                startActivity(intent);
                            }
                        } else {
                            invalidCredentials();
                        }
                    }

                } else {
                    invalidCredentials();
                }
            }
        });
        loginAsyncTask.execute();

    }

    private void storeCredentials(String coachingId, String batchId) {
        AppInfo.setUserId(this, userName);
        AppInfo.setUserPassword(this, password);
        AppInfo.setCoachingId(this,coachingId);
        AppInfo.setBatchId(this,batchId);
        AppInfo.setTeacherId(this,"teacherc1t1");
        AppInfo.setIsKeepMeSignIn(this, String.valueOf(cbKeepMeSignedIn.isChecked()));
    }

    private void invalidCredentials() {
        tvInvalidCredentials.setVisibility(View.VISIBLE);
        etUsername.setText("");
        etPassword.setText("");
    }
}
