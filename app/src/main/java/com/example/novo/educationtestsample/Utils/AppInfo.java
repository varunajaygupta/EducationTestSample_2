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
    public static final String COACHING_ID="COACHING_ID";
    public static final String BATCH_ID="BATCH_ID";

   public static void setUserId(Context activity, String userId){
       SharedPreferences sharedPref = activity.getSharedPreferences(
               APPLICATION_INFO, Context.MODE_PRIVATE);
       SharedPreferences.Editor editor= sharedPref.edit();
       editor.putString(USER_ID,userId);
       editor.commit();
   }
    public static String getUserId(Context activity) {
        SharedPreferences sharedPref = activity.getSharedPreferences(
                APPLICATION_INFO, Context.MODE_PRIVATE);
        return sharedPref.getString(USER_ID,DEFAULT_VALUE);
    }

    public static void setUserPassword(Context activity,String userPassword) {
        SharedPreferences sharedPref = activity.getSharedPreferences(
                APPLICATION_INFO, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor= sharedPref.edit();
        editor.putString(USER_PASSWORD,userPassword);
        editor.commit();
    }

    public static String getUserPassword(Context activity) {
        SharedPreferences sharedPref = activity.getSharedPreferences(
                APPLICATION_INFO, Context.MODE_PRIVATE);
        return sharedPref.getString(USER_PASSWORD,DEFAULT_VALUE);
    }

    public static void setIsKeepMeSignIn(Context activity,String IsKeepMeSignIn) {
        SharedPreferences sharedPref = activity.getSharedPreferences(
                APPLICATION_INFO, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor= sharedPref.edit();
        editor.putString(IS_KEEP_ME_SIGN_IN,IsKeepMeSignIn);
        editor.commit();
    }

    public static String getIsKeepMeSignIn(Context activity) {
        SharedPreferences sharedPref = activity.getSharedPreferences(
                APPLICATION_INFO, Context.MODE_PRIVATE);
        return sharedPref.getString(IS_KEEP_ME_SIGN_IN,DEFAULT_VALUE);
    }
    public static void setCoachingId(Context activity,String coachingId) {
        SharedPreferences sharedPref = activity.getSharedPreferences(
                APPLICATION_INFO, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor= sharedPref.edit();
        editor.putString(COACHING_ID,coachingId);
        editor.commit();
    }

    public static String getCoachingId(Context activity) {
        SharedPreferences sharedPref = activity.getSharedPreferences(
                APPLICATION_INFO, Context.MODE_PRIVATE);
        return sharedPref.getString(COACHING_ID,DEFAULT_VALUE);
    }

    public static void setBatchId(Context activity,String batchId) {
        SharedPreferences sharedPref = activity.getSharedPreferences(
                APPLICATION_INFO, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor= sharedPref.edit();
        editor.putString(COACHING_ID,batchId);
        editor.commit();
    }

    public static String getBatchId(Context activity) {
        SharedPreferences sharedPref = activity.getSharedPreferences(
                APPLICATION_INFO, Context.MODE_PRIVATE);
        return sharedPref.getString(BATCH_ID,DEFAULT_VALUE);
    }
}
