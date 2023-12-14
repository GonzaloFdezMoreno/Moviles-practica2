package com.example.androidengine;

import android.content.Context;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

public class AndroidAdsManager {


    AdView adview;
    AndroidAdsManager(AdView ads, Context cntxt){



        MobileAds.initialize(cntxt, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
                // cargamos los anuncios
            }

        });
        AdRequest adRequest = new AdRequest.Builder().build();
        this.adview=ads;
        adview.loadAd(adRequest);

    }


}
