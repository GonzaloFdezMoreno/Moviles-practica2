package com.example.androidengine;

import android.view.SurfaceView;

import java.util.ArrayList;

public class AndrInput  {

    private InputHandler handler;

    public AndrInput(SurfaceView view){
        handler = new InputHandler(view);
    }

    public ArrayList<TouchEvent> getTouchEvent(){
        return handler.getTouchEvent();
    }
}
