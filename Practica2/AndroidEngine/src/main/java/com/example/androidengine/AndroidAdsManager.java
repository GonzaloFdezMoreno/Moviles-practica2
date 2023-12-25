package com.example.androidengine;

import android.app.Activity;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.OnUserEarnedRewardListener;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.rewarded.RewardItem;
import com.google.android.gms.ads.rewarded.RewardedAd;
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback;
import com.google.android.gms.ads.rewarded.ServerSideVerificationOptions;

public class AndroidAdsManager {


    AdView adview;
    AdRequest adRequest;
    AppCompatActivity act;
    String adUnit="ca-app-pub-3940256099942544/5224354917";

    boolean loaded=false;

    private RewardedAd rewardedAd;
    AndroidAdsManager(AdView ads, Context cntxt, AppCompatActivity activity){



        act=activity;
        adRequest = new AdRequest.Builder().build();
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

    public void setNotLoaded(){
        loaded=false;
    }


    public void loadRewardAd(){
        RewardedAd.load(act,adUnit, adRequest, new RewardedAdLoadCallback() {
            @Override
            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                // Handle the error.
                System.out.println("Not Loaded");
                rewardedAd = null;
                loaded=false;
            }

            @Override
            public void onAdLoaded(@NonNull RewardedAd ad) {
                rewardedAd = ad;
                System.out.println("Ad was loaded.");
                /*ServerSideVerificationOptions options = new ServerSideVerificationOptions
                        .Builder()
                        .setCustomData("SAMPLE_CUSTOM_DATA_STRING")
                        .build();
                rewardedAd.setServerSideVerificationOptions(options);*/
                loaded=true;
                showRewardAd();

            }
        });




        /*rewardedAd.setFullScreenContentCallback(new FullScreenContentCallback() {
            @Override
            public void onAdClicked() {
                // Called when a click is recorded for an ad.
                System.out.println("Ad was clicked.");
            }

            @Override
            public void onAdDismissedFullScreenContent() {
                // Called when ad is dismissed.
                // Set the ad reference to null so you don't show the ad a second time.
                System.out.println("Ad dismissed fullscreen content.");
                rewardedAd = null;
            }

            @Override
            public void onAdFailedToShowFullScreenContent(AdError adError) {
                // Called when ad fails to show.
                System.out.println( "Ad failed to show fullscreen content.");
                rewardedAd = null;
            }

            @Override
            public void onAdImpression() {
                // Called when an impression is recorded for an ad.
                System.out.println( "Ad recorded an impression.");
            }

            @Override
            public void onAdShowedFullScreenContent() {
                // Called when ad is shown.
                System.out.println( "Ad showed fullscreen content.");
            }
        });*/


    }

    public void showRewardAd(){
       /* if (rewardedAd != null) {
            Activity activityContext = act;
            rewardedAd.show(activityContext, new OnUserEarnedRewardListener() {
                @Override
                public void onUserEarnedReward(@NonNull RewardItem rewardItem) {
                    // Handle the reward.
                    System.out.println("The user earned the reward.");
                    int rewardAmount = rewardItem.getAmount();
                    String rewardType = rewardItem.getType();
                }
            });
        }
        else {
            System.out.println("The rewarded ad wasn't ready yet.");
        }*/

        this.act.runOnUiThread(new RewardViewer());
    }
    private class RewardViewer implements Runnable{

        RewardViewer(){

        }
        @Override
        public void run() {

            if (rewardedAd!=null) {
                Activity activityContext = act;
                rewardedAd.show(activityContext, new OnUserEarnedRewardListener() {
                    @Override
                    public void onUserEarnedReward(@NonNull RewardItem rewardItem) {
                        // Handle the reward.
                        System.out.println("The user earned the reward.");
                        int rewardAmount = rewardItem.getAmount();
                        String rewardType = rewardItem.getType();
                    }
                });
                rewardedAd=null; //de esta forma no se repite en bucle
            }
            else {
                System.out.println("The rewarded ad wasn't ready yet.");

            }

            if(!loaded)
                loadRewardAd(); //mientras no se haya cargado, que siga probandolo

        }
    }

    //rewardedAd.setFullScreenContentCallback(new FullScreenContentCallback() {

    private class ScreenContentCallback extends FullScreenContentCallback{
        @Override
        public void onAdClicked() {
            // Called when a click is recorded for an ad.
            System.out.println("Ad was clicked.");
        }

        @Override
        public void onAdDismissedFullScreenContent() {
            // Called when ad is dismissed.
            // Set the ad reference to null so you don't show the ad a second time.
            System.out.println("Ad dismissed fullscreen content.");
            rewardedAd = null;
        }

        @Override
        public void onAdFailedToShowFullScreenContent(AdError adError) {
            // Called when ad fails to show.
            System.out.println( "Ad failed to show fullscreen content.");
            rewardedAd = null;
        }

        @Override
        public void onAdImpression() {
            // Called when an impression is recorded for an ad.
            System.out.println( "Ad recorded an impression.");
        }

        @Override
        public void onAdShowedFullScreenContent() {
            // Called when ad is shown.
            System.out.println( "Ad showed fullscreen content.");
        }
    }


}


