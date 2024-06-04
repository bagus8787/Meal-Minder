package com.example.MealMinder;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;

public class Notification extends BroadcastReceiver {
    @Override
    // implement onReceive() method
    public void onReceive(Context context, Intent intent) {

        android.app.Notification notification = new NotificationCompat.Builder(context, "channel1")
                .setSmallIcon(android.R.drawable.ic_dialog_info)
                .setContentTitle(intent.getStringExtra("titleExtra"))
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText(intent.getStringExtra("messageExtra"))
                )
                .setContentText(intent.getStringExtra("messageExtra"))
                .build();

        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(1, notification);
    }
}