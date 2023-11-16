package com.example.logic;

import com.example.commonengine.CAudio;
import com.example.commonengine.CGraphics2D;
import com.example.commonengine.CSound;
import com.example.commonengine.TouchEvent;

import java.util.ArrayList;

public class DaltonismoButton extends Button{

    private CSound sound;

    MastermindBoard mb;
    boolean activated;

    DaltonismoButton(MastermindBoard mb_, int posX_, int posY_, int width_, int height_){
        super(posX_,posY_,width_, height_);

        mb = mb_;
        activated = false;
    }

    public boolean handleInput(ArrayList<TouchEvent> event, CAudio audio) {

        if(sound == null){
            sound=audio.newSound("bloop.wav");
        }

        for(TouchEvent events : event) {
            //comprobamos que esta dentro de los limites del boton

            if(this.getPosX() < events.x && this.getPosX() + this.getWidth() > events.x
                    && this.getPosY() < events.y && this.getPosY() + this.getHeight() > events.y) {
                if (events.type == TouchEvent.TouchEventType.CLICK) {
                    //System.out.println("ButtonPressedRAPIDO");
                }
                if (events.type == TouchEvent.TouchEventType.TOUCH_UP) {
                    //System.out.println("RELEASE");
                }
                if (events.type == TouchEvent.TouchEventType.TOUCH_DOWN) {
                    //System.out.println("ButtonPressed");

                    onTouchDown();


                    audio.playSound(sound);
                }
            }
        }
        return true;
    }

    @Override
    public void render(CGraphics2D graph) {

        if(!activated){
            graph.drawImage(graph.createImage("eyehide.png"),getPosX(),(int)getPosY(),(int)getWidth(),(int)getHeight());
        } else {
            graph.drawImage(graph.createImage("eyeshow.png"),getPosX(),(int)getPosY(),(int)getWidth(),(int)getHeight());
        }
    }


    @Override
    void onTouchDown() {
        activated = !activated;
        mb.setDaltonismo(activated);
    }
}
