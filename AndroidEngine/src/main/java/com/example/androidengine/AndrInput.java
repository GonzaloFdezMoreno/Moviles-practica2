package com.example.androidengine;

import android.view.SurfaceView;

import com.example.commonengine.CInput;
import com.example.commonengine.TouchEvent;
import java.util.ArrayList;

public class AndrInput implements CInput {

    private InputHandler handler;

    public AndrInput(SurfaceView view){
        handler = new InputHandler(view);
    }

    @Override
    public ArrayList<TouchEvent> getTouchEvent(){
        return handler.getTouchEvent();
    }
}
