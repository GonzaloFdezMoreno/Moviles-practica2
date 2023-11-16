package com.example.androidengine;

public abstract class EngineClass {


    protected CLogic log;

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
}
