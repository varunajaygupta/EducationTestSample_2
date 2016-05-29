package com.example.novo.educationtestsample.Utils;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

import com.example.novo.educationtestsample.R;
import com.example.novo.educationtestsample.activities.MainActivity;

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

    public static void showNotification(Context context,String title,String text){
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(context)
                        .setSmallIcon(R.drawable.appicon)
                        .setContentTitle(title)
                        .setContentText(text);
        Intent resultIntent = new Intent(context, MainActivity.class);

        PendingIntent resultPendingIntent = PendingIntent.getActivity(
                context,
                0,
                resultIntent,
                PendingIntent.FLAG_UPDATE_CURRENT
        );
        mBuilder.setContentIntent(resultPendingIntent);
        int mNotificationId = 001;
        NotificationManager mNotifyMgr = (NotificationManager)context. getSystemService(Context.NOTIFICATION_SERVICE);
        mNotifyMgr.notify(mNotificationId, mBuilder.build());

    }
}
