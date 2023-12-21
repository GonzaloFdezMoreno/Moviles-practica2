package com.example.practica2;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;

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

public class MainActivity extends AppCompatActivity {

    SurfaceView sfv;
    AndroidEngine andrEng;
    AdView adView;
    CLogic logic;
    private String sharedPrefFile = "com.example.android.mastermindPrefs";
    private SharedPreferences mPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
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

        andrEng=new AndroidEngine(this.sfv, this.adView,this);

        logic = new Logic(andrEng);

        this.andrEng.SetLogic(logic);

        mPreferences = getSharedPreferences(sharedPrefFile, MODE_PRIVATE);
}
    @Override
    protected void onResume() {
        super.onResume();
        andrEng.resume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        andrEng.pause();

        SharedPreferences.Editor preferencesEditor = mPreferences.edit();

        Logic logic_ = (Logic)logic;
        logic_.saveStuffTest();

       /* preferencesEditor.putInt("count", mCount);
        preferencesEditor.putBoolean("playing", True);
        preferencesEditor.apply(); //también podemos usar .commit()*/
    }
}