package com.example.logic;

import com.example.commonengine.CAudio;
import com.example.commonengine.CGraphics2D;
import com.example.commonengine.CSound;
import com.example.commonengine.TouchEvent;

import java.util.ArrayList;

public class GoBackMenuButton extends Button{
    Logic log;
    private CSound sound;
    GoBackMenuButton(int posX_, int posY_, int width_, int height_, Logic logic){
        super(posX_,posY_,width_, height_);
        log=logic;


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
                    System.out.println("ButtonPressedRAPIDO");
                }
                if (events.type == TouchEvent.TouchEventType.TOUCH_UP) {
                    System.out.println("RELEASE");
                }
                if (events.type == TouchEvent.TouchEventType.TOUCH_DOWN) {
                    System.out.println("ButtonPressed");

                    onTouchDown();
                    //engine.GetLogic().SetScene(new LevelMenu(engine));

                    audio.playSound(sound);
                }
            }

        }

        return true;
    }

    @Override
    public void update(double t){
        //handleInput(event);
        //double newPos = (getPosX() + 50 * t);
        //setPosX(newPos);
    }
    @Override
    public void render(CGraphics2D graph){

        graph.drawImage(graph.createImage("goback.png"),getPosX(),(int)getPosY(),(int)getWidth(),(int)getHeight());

        //graph.setColor(0xFF00FF00);
        //graph.clear();

    }

    @Override
    void onClick() {

    }

    @Override
    void onTouchDown() {

        log.SetScene(new MainMenu(log));
    }





}
