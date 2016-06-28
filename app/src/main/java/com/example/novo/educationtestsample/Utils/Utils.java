package com.example.novo.educationtestsample.Utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.text.TextUtils;
import android.view.Gravity;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.novo.educationtestsample.R;
import com.example.novo.educationtestsample.activities.MainActivity;
import com.example.novo.educationtestsample.interfaces.ButtonClickCallback;

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
    public static long changeTimeIntoMilliseconds(int timeInSecs){
    return TimeUnit.SECONDS.toMillis(timeInSecs) ;
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
    /*
    Show the alert dialogbox with a callback
     */
    public static void showAlertWithCallback(final Activity activity, String title,
                                             String message, String positiveButtonText, String negativeButtonText,
                                             boolean isAlertCancelable,
                                             final ButtonClickCallback callback) {

        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setCancelable(isAlertCancelable);
        if(!TextUtils.isEmpty(title)){
            builder.setTitle(title);
        }
        if(!TextUtils.isEmpty(message)){
            builder.setMessage(message);
        }
        if(!TextUtils.isEmpty(positiveButtonText)){

            builder.setPositiveButton(positiveButtonText, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    callback.onPositiveButtonClick();
                }
            });
        }
        if(!TextUtils.isEmpty(negativeButtonText)){
            builder.setNegativeButton(negativeButtonText, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    callback.onNegativeButtonClick();
                }
            });
        }
        // Create the AlertDialog object and return it
        builder.show();


    }
    /**
     * Show an alert wherever you like, you can pass any field null except
     * activity.
     *
     * @param activity
     * @param title
     * @param message
     * @param buttonText
     * @param isAlertCancelable
     * @param finishActivityOnClickingOK
     */
    public static void showAlert(final Activity activity, String title,
                                 String message, String buttonText, boolean isAlertCancelable,
                                 final boolean finishActivityOnClickingOK) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setCancelable(isAlertCancelable);
        if(!TextUtils.isEmpty(title)){
            builder.setTitle(title);
        }
        if(!TextUtils.isEmpty(message)){
            builder.setMessage(message);
        }

        if(TextUtils.isEmpty(buttonText)){
           buttonText= activity.getString(R.string.OK);
        }
            builder.setPositiveButton(buttonText, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    if (finishActivityOnClickingOK) {
                        activity.finish();
                    }
                    dialog.dismiss();
                }
            });
        // Create the AlertDialog object and return it
        builder.show();

    }

    }
