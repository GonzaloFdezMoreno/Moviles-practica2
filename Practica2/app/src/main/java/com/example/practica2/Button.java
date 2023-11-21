package com.example.practica2;


import com.example.androidengine.AndrGraphics2D;
import com.example.androidengine.TouchEvent;

import java.util.ArrayList;

public class Button extends GameObject {

    //protected CSound sound;

    Button(int posX_, int posY_, int width_, int height_){
        super(posX_, posY_, width_, height_);
    }

    @Override
    public boolean handleInput(ArrayList<TouchEvent> event/*, CAudio audio*/) {

        /*if (sound == null){
            sound=audio.newSound("bloop.wav");
        }*/

        for(TouchEvent events : event) {
            //comprobamos que esta dentro de los limites del boton

            if(this.getPosX() < events.x && this.getPosX() + this.getWidth() > events.x
                    && this.getPosY() < events.y && this.getPosY() + this.getHeight() > events.y) {
                if (events.type == TouchEvent.TouchEventType.CLICK) {
                    System.out.println("ButtonPressedRAPIDO");
                    onClick();
                }
                if (events.type == TouchEvent.TouchEventType.TOUCH_UP) {
                    System.out.println("RELEASE");
                    onTouchUp();
                }
                if (events.type == TouchEvent.TouchEventType.TOUCH_DOWN) {
                    System.out.println("ButtonPressed");
                    onTouchDown();
                    //audio.playSound(sound);
                }
            }

        }

        return true;
    }

    @Override
    public void update(double t){
    }
    @Override
    public void render(AndrGraphics2D graph){
        graph.setColor(0xFF00FF);
        graph.fillCircle((int)getPosX(),(int)getPosY(),(int)getWidth(),(int)getHeight());
        graph.setColor(0x000000);

    }

    void onClick(){

    }

    void onTouchUp(){

    }

    void onTouchDown(){

    }

}
