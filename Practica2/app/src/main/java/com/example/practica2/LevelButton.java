package com.example.practica2;


import com.example.androidengine.AndrGraphics2D;
import com.example.androidengine.CLogic;
import com.example.androidengine.TouchEvent;

import java.util.ArrayList;

public class LevelButton extends Button {
    private String lvl;
    int difficulty;

    //private AndroidSound sound;
    private Logic log;

        LevelButton(int posX_, int posY_, int width_, int height_, String txt, CLogic logic){

            super(posX_, posY_, width_, height_);

            lvl=txt;
            log = (Logic)logic;
            //sound = log.getAudio().newSound("bloop.wav");
        }

        @Override
        public boolean handleInput(ArrayList<TouchEvent> event/*, CAudio audio*/) {

            /*if(sound == null){
                sound=audio.newSound("bloop.wav");
            }*/

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
                        //audio.playSound(sound);
                        onTouchDown();
                    }
                }

            }

            return true;
        }

        @Override
        public void update(double t){
            //handleInput(event);

        }
        @Override
        public void render(AndrGraphics2D graph){

            //Segun la dificultad tendrá un color distinto
            if(lvl=="FACIL"){
                graph.setColor(0xFF00FA0F);
                difficulty=0;
            }
            else if(lvl=="NORMAL"){
                graph.setColor(0xFFFFFF00);
                difficulty=1;
            }
            else if(lvl=="DIFICIL"){
                graph.setColor(0xFFFF8000);
                difficulty=2;
            }
            else if(lvl=="EXTREMO"){
                graph.setColor(0xFFFF0000);
                difficulty=3;
            }

            graph.fillRoundRectangle((int)getPosX(),(int)getPosY(),(int)getWidth(),(int)getHeight(),50,50);
            graph.setColor(0xFF000000);
            graph.drawText(lvl,(int)getPosX()+60,(int)getPosY()+30);


        }

    @Override
    void onTouchDown() {
       log.SetScene(new PlayScene(log,difficulty));
    }
}
