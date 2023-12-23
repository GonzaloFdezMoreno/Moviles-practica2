package com.example.practica2;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

public class NotificationViewer extends Worker {


    String CHANNEL_ID;
    String title;
    String description;
    boolean auto_cancel;


    public static String INPUT_CHANNEL_ID = "channelID";

    public static String INPUT_TITLE = "tittle";
    public static String INPUT_DESCRIPTION = "description";
    public static String INPUT_AUTOCANCEL = "autocancel";

    private static NotificationCompat.Builder builder;


    public NotificationViewer(Context context, WorkerParameters wkparam) {
        super(context, wkparam);


    }

    @NonNull
    @Override
    public Result doWork() {
        title = getInputData().getString(INPUT_TITLE);
        description = getInputData().getString(INPUT_DESCRIPTION);
        CHANNEL_ID = getInputData().getString(INPUT_CHANNEL_ID);
        auto_cancel = getInputData().getBoolean(INPUT_AUTOCANCEL, true);


        showNotification();
        return Result.success();
    }


    public void showNotification() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.putExtra("notification", true);
        PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(),
                0, intent, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);

         builder = new NotificationCompat.Builder(getApplicationContext(), CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle(title)
                .setContentText(description)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                // Set the intent that will fire when the user taps the notification
                .setContentIntent(pendingIntent)
                .setAutoCancel(auto_cancel);

    }

    public static NotificationCompat.Builder GetBuilder(){
        return builder;
    }



}
