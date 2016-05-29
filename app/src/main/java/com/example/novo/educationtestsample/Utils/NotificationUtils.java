package com.example.novo.educationtestsample.Utils;

import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.NotificationCompat;

/**
 * Created by Hisham on 2/15/2016.
 */
public class NotificationUtils {

    private static NotificationUtils instance;

    private Context mContext;
    private NotificationManager mNotifyManager;
    private int NOTIFICATION_SIMPLE_MESSAGE_ID = 1;

    private NotificationUtils(Context context) {
        mContext = context;
        mNotifyManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

    }

    public static NotificationUtils getInstance(Context context) {
        if (instance == null)
            instance = new NotificationUtils(context);
        return instance;
    }

    public void showNormalNotification(CharSequence title, CharSequence message, int iconIDForLollipop, int iconIDforOldVersion, int number, Activity activity){
        NotificationCompat.Builder builder = new NotificationCompat.Builder(mContext);
        Intent intent = new Intent(mContext, activity.getClass());

        setBuilderDefaults(builder, title, message, intent, iconIDForLollipop, iconIDforOldVersion, number);
        mNotifyManager.notify(NOTIFICATION_SIMPLE_MESSAGE_ID, builder.build());
    }

    public void showInboxStyleNotification(CharSequence title, CharSequence message, int iconIDForLollipop, int iconIDforOldVersion, CharSequence inboxStyleLine, CharSequence subText, int number, Activity activity){
        NotificationCompat.Builder builder = new NotificationCompat.Builder(mContext);
        NotificationCompat.InboxStyle inboxStyle = new NotificationCompat.InboxStyle();

        inboxStyle.addLine(inboxStyleLine);
        builder.setSubText(subText);
        builder.setStyle(inboxStyle);

        Intent intent = new Intent(mContext, activity.getClass());
        setBuilderDefaults(builder, title, message, intent, iconIDForLollipop, iconIDforOldVersion, number);
        mNotifyManager.notify(NOTIFICATION_SIMPLE_MESSAGE_ID, builder.build());
    }

    public void showBigStyleNotification(CharSequence title, CharSequence message, int iconIDForLollipop, int iconIDforOldVersion, CharSequence inboxStyleLine, CharSequence subText, int number, Activity activity){
        NotificationCompat.Builder builder = new NotificationCompat.Builder(mContext);
        builder.setStyle(new NotificationCompat.BigTextStyle().bigText(message));
//        builder.setStyle(new NotificationCompat.BigPictureStyle().bigPicture(bitmap));

        Intent intent = new Intent(mContext, activity.getClass());
        setBuilderDefaults(builder, title, message, intent, iconIDForLollipop, iconIDforOldVersion, number);
        mNotifyManager.notify(NOTIFICATION_SIMPLE_MESSAGE_ID, builder.build());
    }
    /**
     * Build notification builder that will use when you need to generate notification
     * author : hisham
     * @param builder notification builder
     * @param title
     * @param message message for showing  @return NotificationCompat.Builder
     * @param number
     *
     */
    private void setBuilderDefaults(NotificationCompat.Builder builder, CharSequence title, CharSequence message, Intent intent, int iconIDForLollipop, int iconIDforOldVersion, int number) {

        builder.setContentTitle(title);
        builder.setTicker(message);
        builder.setNumber(number);
        Bitmap icon;
        //set small icon as per android version because in LOLLIPOP
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            builder.setSmallIcon(iconIDForLollipop);
            icon = BitmapFactory.decodeResource(mContext.getResources(), iconIDForLollipop);
        } else{
            builder.setSmallIcon(iconIDforOldVersion);
            icon = BitmapFactory.decodeResource(mContext.getResources(), iconIDforOldVersion);
        }
        builder.setLargeIcon(icon);
        PendingIntent pendingIntent = PendingIntent.getActivity(mContext, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_ONE_SHOT);
        builder.setContentIntent(pendingIntent);
        builder.setLights(Color.BLUE, 500, 500);

        // wait before 1st vibration,ms for
        long[] pattern = {100, 500};
        builder.setVibrate(pattern);
        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        builder.setSound(alarmSound);
        builder.setAutoCancel(true);
    }
}
