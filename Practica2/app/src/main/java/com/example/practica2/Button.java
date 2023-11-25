package com.example.practica2;


import com.example.androidengine.AndrGraphics2D;
import com.example.androidengine.TouchEvent;

import java.util.ArrayList;

public class Button extends GameObject {

    String text = "";
    String imgName = "";
    Button(int posX_, int posY_, int width_, int height_, String text_, String imgName_){
        super(posX_, posY_, width_, height_);
        text = text_;
        imgName = imgName_;
    }

    @Override
    public boolean handleInput(ArrayList<TouchEvent> event) {

        boolean pressed = false;
        for(TouchEvent events : event) {
            //comprobamos que esta dentro de los limites del boton

            if(this.getPosX() < events.x && this.getPosX() + this.getWidth() > events.x
                    && this.getPosY() < events.y && this.getPosY() + this.getHeight() > events.y) {

                if (events.type == TouchEvent.TouchEventType.TOUCH_DOWN) {
                    System.out.println("ButtonPressed");
                    onTouchDown();
                }
                pressed = true;
            }

        }
        return pressed;
    }

    @Override
    public void update(double t){
    }
    @Override
    public void render(AndrGraphics2D graph){
        if(imgName == ""){
            graph.setColor(0xFF00FF00);
            graph.fillCircle(getPosX(),getPosY(),getWidth());
            graph.setColor(0xFFFFFFFF);

        }
        else{
            graph.drawImage(graph.createImage(imgName),getPosX(),getPosY(),getWidth(),getHeight());
        }

        graph.setFont(graph.createFont("AARVC___.TTF",25,true,false));
        graph.drawText(text,getPosX() + 60, getPosY() + 45);
    }

    void onClick(){

    }

    void onTouchUp(){

    }

    void onTouchDown(){

    }

    void setImgName(String name_){
        imgName = name_;
    }

}
