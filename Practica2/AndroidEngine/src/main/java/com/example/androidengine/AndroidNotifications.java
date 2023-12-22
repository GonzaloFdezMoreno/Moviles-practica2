package com.example.androidengine;


import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import androidx.core.app.NotificationCompat;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class AndroidNotifications {
    private static String CHANNEL_ID = "NotifyMsg";
    private Context context;
    //private Notification notification = new Notification("notification","terminar esto",10);
    private ArrayList<Notification> notifications=new ArrayList<>() ;


    String title="Noti";
    String description= "que se termine ya por dios";


    public AndroidNotifications(Context cntxt){
        context=cntxt;

        /*Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic)
                .setContentTitle("VUELVE")
                .setContentText("Come here you little scum")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);*/


       createNotificationChannel();

    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            //CharSequence name = getString(R.string.channel_name);
            //String description = getString(R.string.channel_description);

            NotificationChannel notiChannel= new NotificationChannel(CHANNEL_ID,title,NotificationManager.IMPORTANCE_DEFAULT);
            notiChannel.setDescription(description);
            NotificationManager notiMngr=(NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            notiMngr.createNotificationChannel(notiChannel);



        }

    }

    public String GetChannelID(){
        return CHANNEL_ID;
    }

    public ArrayList<Notification> getNotification(){
        return notifications;

    }

    public void addNotification(Notification noti){
        notifications.add(noti);
    }

}
