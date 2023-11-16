package com.example.commonengine;

import java.util.ArrayList;
import java.util.Calendar;

public interface CLogic {


    void update(double t);
    void render(CGraphics2D graph);
    void handleEvents(ArrayList<TouchEvent> input, CAudio audio);



}
