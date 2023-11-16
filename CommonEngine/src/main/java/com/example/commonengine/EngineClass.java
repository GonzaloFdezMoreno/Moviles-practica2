package com.example.commonengine;

public abstract class EngineClass implements Cengine{


    protected CLogic log;

    public int getWidth() {
        return 0;

    }
    public int getHeight() {
        return 0;

    }
    @Override
    public CLogic GetLogic(){
        return log;
    }
    @Override
    public void SetLogic(CLogic logic){
        this.log=logic;
    }
}
