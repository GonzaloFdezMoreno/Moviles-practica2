package com.example.desktopengine;

import com.example.commonengine.CInput;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JFrame;

import com.example.commonengine.TouchEvent;

public class DesktopInput implements CInput, MouseListener {
    private ArrayList<TouchEvent> events;
    private ArrayList<TouchEvent> pendingEvents;

    public ArrayList<TouchEvent> getTouchEvent(){
        synchronized (this) {
            this.events.clear();
            this.events.addAll(pendingEvents);
            this.pendingEvents.clear();
            return events;
        }
    }

    public DesktopInput(JFrame view){
        events = new ArrayList<>();
        pendingEvents = new ArrayList<>();
        view.addMouseListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        TouchEvent event = new TouchEvent();
        event.x = mouseEvent.getX();
        event.y = mouseEvent.getY();
        event.type = TouchEvent.TouchEventType.CLICK;

        synchronized (this){
            pendingEvents.add(event);
        }
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        TouchEvent event = new TouchEvent();
        event.x = mouseEvent.getX();
        event.y = mouseEvent.getY();
        event.type = TouchEvent.TouchEventType.TOUCH_DOWN;

        synchronized (this){
            pendingEvents.add(event);
        }
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
        TouchEvent event = new TouchEvent();
        event.x = mouseEvent.getX();
        event.y = mouseEvent.getY();
        event.type = TouchEvent.TouchEventType.TOUCH_UP;

        synchronized (this){
            pendingEvents.add(event);
        }
    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }
}
