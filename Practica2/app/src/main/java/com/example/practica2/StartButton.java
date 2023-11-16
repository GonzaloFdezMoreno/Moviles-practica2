package com.example.logic;

import com.example.commonengine.CAudio;
import com.example.commonengine.CGraphics2D;
import com.example.commonengine.CSound;
import com.example.commonengine.Cengine;
import com.example.commonengine.TouchEvent;

import java.util.ArrayList;

public class StartButton extends Button {
    Logic log;
    private CSound sound;
    String text;


    StartButton(int posX_, int posY_, int width_, int height_, String txt,Logic logic){
    //Button(double posX_, double posY_, double width_, double height_){
        super(posX_, posY_, width_, height_);
        log=logic;
        text=txt;
    }

    @Override
    public boolean handleInput(ArrayList<TouchEvent> event, CAudio audio) {

        if(sound == null){
            sound=audio.newSound("bloop.wav");
        }

        for(TouchEvent events : event) {
            //comprobamos que esta dentro de los limites del boton

            if(this.getPosX() < events.x && this.getPosX() + this.getWidth() > events.x
                    && this.getPosY() < events.y && this.getPosY() + this.getHeight() > events.y) {
                if (events.type == TouchEvent.TouchEventType.CLICK) {
                    System.out.println("ButtonPressedRAPIDO");
                }
                if (events.type == TouchEvent.TouchEventType.TOUCH_UP) {
                    System.out.println("RELEASE");
                }
                if (events.type == TouchEvent.TouchEventType.TOUCH_DOWN) {
                    System.out.println("ButtonPressed");

                    onTouchDown();

                    audio.playSound(sound);
                }
            }

        }

        return true;
    }

    @Override
    public void update(double t){

    }
    @Override
    public void render(CGraphics2D graph){



        graph.setFont(graph.createFont("AARVC___.TTF",25,true,false));


        graph.setColor(0xFF00FFFF);
        graph.fillRoundRectangle((int)getPosX(),(int)getPosY(),(int)getWidth(),(int)getHeight(),20,20);
        graph.setColor(0xFF000000);
        graph.drawRoundRectangle((int)getPosX(),(int)getPosY(),(int)getWidth(),(int)getHeight(),20,20);

        graph.drawText(text,(int)getPosX()+60, (int)getPosY()+45);

        //graph.setColor(0xFF00FF00);
        //graph.clear();

    }

    @Override
    void onClick() {

    }

    @Override
    void onTouchDown() {


        log.SetScene(new LevelMenu(log));
    }
}
