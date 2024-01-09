package com.example.androidengine;

import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;

import java.util.ArrayList;

public class InputHandler implements View.OnTouchListener{

    private ArrayList<TouchEvent> events;
    private ArrayList<TouchEvent> pendingEvents;

    public InputHandler(SurfaceView view){
        events = new ArrayList<>();
        pendingEvents = new ArrayList<>();
        view.setOnTouchListener(this);
    }

    public ArrayList<TouchEvent> getTouchEvent(){
        synchronized (this){
            this.events.clear();
            this.events.addAll(this.pendingEvents);
            this.pendingEvents.clear();
            return events;
        }
    }

    public boolean onTouch(View view, MotionEvent motionEvent){
        TouchEvent event = new TouchEvent();
        int index = motionEvent.getActionIndex();
        int action = motionEvent.getActionMasked();

        event.x = (int) motionEvent.getX(index);
        event.y = (int) motionEvent.getY(index);

        if(action == MotionEvent.ACTION_DOWN){
            event.type = TouchEvent.TouchEventType.TOUCH_DOWN;
        } else if (action == MotionEvent.ACTION_UP) {
            event.type = TouchEvent.TouchEventType.TOUCH_UP;
        }
        else if(action == MotionEvent.ACTION_MOVE){
            event.type = TouchEvent.TouchEventType.TOUCH_DRAG;
        }


        synchronized (this){
            pendingEvents.add(event);
        }

        return true;
    }
}
