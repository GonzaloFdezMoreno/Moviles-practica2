package com.example.practica2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;
import androidx.work.Data;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;

import com.example.androidengine.Notification;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.SurfaceView;
import android.view.View;

import com.example.androidengine.AndroidEngine;
import com.example.androidengine.CLogic;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import android.content.SharedPreferences;
import java.io.File;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    SurfaceView sfv;
    AndroidEngine andrEng;
    AdView adView;
    CLogic logic;
    Bundle savedInstanceState;
    private String sharedPrefFile = "com.example.android.mastermindPrefs";
    private SharedPreferences mPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState_) {
        savedInstanceState = savedInstanceState_;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.sfv = findViewById(R.id.surfaceView);
        this.adView=findViewById(R.id.adView);
        //setContentView(this.sfv);


        /*MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
                // cargamos los anuncios
            }
        });

        //AdView adView=new AdView(this);
        //adView.setAdSize(AdSize.BANNER);
        //adView.setAdUnitId("ca-app-pub-3940256099942544/6300978111");
        //adView.setVisibility(View.VISIBLE);



        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);*/

        andrEng=new AndroidEngine(this.sfv, this.adView,this,this);

        logic = new Logic(andrEng);

        this.andrEng.SetLogic(logic);

        mPreferences = getSharedPreferences(sharedPrefFile, MODE_PRIVATE);
}
    @Override
    protected void onResume() {
        super.onResume();
        andrEng.resume();

        System.out.print("WE BACK BBY!!\n");
        if (savedInstanceState == null) {
            //load stuff
            Logic logic_ = (Logic)logic;
            logic_.loadGameState(mPreferences);

        } else {
            System.out.print("Nothing loaded, savedInstanceState in MainActivity was null\n");
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        andrEng.pause();

        SharedPreferences.Editor preferencesEditor = mPreferences.edit();

        Logic logic_ = (Logic)logic;
        logic_.saveGameState(preferencesEditor);

        preferencesEditor.apply();

        NotificationManagerCompat mngr = NotificationManagerCompat.from(getApplicationContext());


        if (ContextCompat. checkSelfPermission(this, android.Manifest.permission.POST_NOTIFICATIONS) ==
                PackageManager. PERMISSION_GRANTED) {
            // Si tenemos permisos, podemos usar la API solicitada.
        }
        else {
            // Pedimos permisos al usuario. Lo correcto es explicar al usuario antes el porqué son
            //necesarios
            requestPermissions(new String[]{android.Manifest.permission.POST_NOTIFICATIONS},
                    1);
        }


        ArrayList<Notification> notifications= andrEng.getAndroidNotifications().getNotification();

        //Para cargar cada notificacion
        for(Notification notification: notifications){
        Data input = new Data.Builder()
                .putString(NotificationViewer.INPUT_CHANNEL_ID, andrEng.getAndroidNotifications().GetChannelID())
                .putString(NotificationViewer.INPUT_TITLE, notification.GetTitle())
                .putString(NotificationViewer.INPUT_DESCRIPTION, notification.GetDescription())
                .putBoolean(NotificationViewer.INPUT_AUTOCANCEL, true)
                .build();

        OneTimeWorkRequest notificationWork = new OneTimeWorkRequest.Builder(NotificationViewer.class)
                .setInitialDelay(10, TimeUnit.SECONDS)
                .setInputData(input)
                .build();

        WorkManager.getInstance(this).enqueue(notificationWork);

        System.out.println("aparece");
        mngr.notify(1, NotificationViewer.GetBuilder().build());
        }
        notifications.clear();


    }


}