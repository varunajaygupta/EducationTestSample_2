package com.example.novo.educationtestsample.models;

/**
 * Created by Varun on 5/22/2016.
 */
public class LoginRequest {
    String loginid;
    String password;

    public LoginRequest(String loginid, String password) {
        this.loginid = loginid;
        this.password = password;
    }

    public String getLoginid() {
        return loginid;
    }

    public void setLoginid(String loginid) {
        this.loginid = loginid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "LoginRequest{" +
                "loginid='" + loginid + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
