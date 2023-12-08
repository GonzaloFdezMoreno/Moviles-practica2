package com.example.androidengine;

public abstract class EngineClass {


    protected CLogic log;
    public AndroidAudio audio;
    AndrJSONLoader andrJSONLoader;
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

    public AndrJSONLoader getaJsonlodr(){ return andrJSONLoader;}
}
