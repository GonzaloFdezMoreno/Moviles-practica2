package com.example.androidengine;


import java.util.ArrayList;

public interface CLogic {


    void update(double t);
    void render(AndrGraphics2D graph);
    void handleEvents(ArrayList<TouchEvent> input/*, AndroidAudio audio*/);



}
