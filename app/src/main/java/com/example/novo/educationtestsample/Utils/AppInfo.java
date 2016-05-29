package com.example.novo.educationtestsample.Utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Varun on 5/23/2016.
 */
/*
In this class we will be storing data which will be required through out the application like during relogin
 */
public class AppInfo {
    public static final String APPLICATION_INFO="APPLICATION_INFO";
    public static final String USER_ID="USER_ID";
    public static final String USER_PASSWORD="USER_PASSWORD";
    public static final String DEFAULT_VALUE="DEFAULT_VALUE";
    public static final String IS_KEEP_ME_SIGN_IN="IS_KEEP_ME_SIGN_IN";

   public static void setUserId(Activity activity, String userId){
       SharedPreferences sharedPref = activity.getSharedPreferences(
               APPLICATION_INFO, Context.MODE_PRIVATE);
       SharedPreferences.Editor editor= sharedPref.edit();
       editor.putString(USER_ID,userId);
       editor.commit();
   }

    public static void setUserPassword(Activity activity,String userPassword) {
        SharedPreferences sharedPref = activity.getSharedPreferences(
                APPLICATION_INFO, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor= sharedPref.edit();
        editor.putString(USER_PASSWORD,userPassword);
        editor.commit();
    }

    public static String getUserId(Activity activity) {
        SharedPreferences sharedPref = activity.getSharedPreferences(
                APPLICATION_INFO, Context.MODE_PRIVATE);
        return sharedPref.getString(USER_ID,DEFAULT_VALUE);
    }

    public static String getUserPassword(Activity activity) {
        SharedPreferences sharedPref = activity.getSharedPreferences(
                APPLICATION_INFO, Context.MODE_PRIVATE);
        return sharedPref.getString(USER_PASSWORD,DEFAULT_VALUE);
    }

    public static void setIsKeepMeSignIn(Activity activity,String IsKeepMeSignIn) {
        SharedPreferences sharedPref = activity.getSharedPreferences(
                APPLICATION_INFO, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor= sharedPref.edit();
        editor.putString(IS_KEEP_ME_SIGN_IN,IsKeepMeSignIn);
        editor.commit();
    }

    public static String getIsKeepMeSignIn(Activity activity) {
        SharedPreferences sharedPref = activity.getSharedPreferences(
                APPLICATION_INFO, Context.MODE_PRIVATE);
        return sharedPref.getString(IS_KEEP_ME_SIGN_IN,DEFAULT_VALUE);
    }

}
