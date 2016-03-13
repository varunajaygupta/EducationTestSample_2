package com.example.novo.educationtestsample;

import android.content.Context;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Time;
import java.util.concurrent.TimeUnit;

/**
 * Created by Varun Ajay Gupta on 10/3/16.
 */
public class Utils
{
    public static String loadJSONfromAssests(Context context,String fileName) {
        InputStream is = null;
        String json=null;
        try {
            is = context.getAssets().open(fileName);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");

        } catch (IOException e) {
            e.printStackTrace();
        }
        return json;
    }
    public static long changeTimeIntoMilliseconds(int timeInMins){
    return TimeUnit.MINUTES.toMillis(timeInMins) ;
    }
}
