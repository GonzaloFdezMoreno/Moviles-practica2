package com.example.androidengine;

public abstract class EngineClass {


    protected CLogic log;
    public AndroidAudio audio;
    public AndroidSound sound;
    AndrJSONLoader andrJSONLoader;

    AndroidNotifications andrNot;
    public int getWidth() {
        return 0;

    }
    public int getHeight() {
        return 0;

    }

    public CLogic GetLogic(){
        return log;
    }

    public void SetLogic(CLogic logic){
        this.log=logic;
    }

    public AndroidAudio getAudio() {
        return this.audio;
    }

    public AndroidSound getSound() {
        return this.sound;
    }

    public AndrJSONLoader getaJsonlodr(){ return andrJSONLoader;}

    public AndroidNotifications getAndroidNotifications(){return andrNot;}

    public void showRewardAd(){};
    public void loadRewardAd(){};
}
