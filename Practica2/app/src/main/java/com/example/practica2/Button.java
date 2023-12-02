package com.example.practica2;


import com.example.androidengine.AndrGraphics2D;
import com.example.androidengine.AndroidAudio;
import com.example.androidengine.AndroidSound;
import com.example.androidengine.TouchEvent;

import java.util.ArrayList;

public class Button extends GameObject {

    //ok para que el audio funcione hacer que la escena le pase el audio de engine al boton al crearlo y
    //asi tiene acceso a audio el boton (que la escena coja el audio de logic y se lo da al boton)
    private AndroidSound sound;
    private AndroidAudio andAudio;

    String text = "";
    String imgName = "";
    int color = 0xFF00FFFF;
    Button(int posX_, int posY_, int width_, int height_, String imgName_, AndroidAudio audio){
        super(posX_, posY_, width_, height_);
        imgName = imgName_;
        andAudio = audio;
        sound = andAudio.newSound("bloop.wav");
    }

    Button(int posX_, int posY_, int width_, int height_, String text_, int color_, AndroidAudio audio){
        super(posX_, posY_, width_, height_);
        text = text_;
        color = color_;
        andAudio = audio;
        sound = andAudio.newSound("bloop.wav");
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
                    andAudio.playSound(sound);
                    pressed = true;
                }

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
            graph.setColor(color);
            graph.fillRoundRectangle(getPosX(), getPosY(), getWidth(), getHeight(), 15, 10);
            graph.setColor(0xFFFFFFFF);

        }
        else{
            graph.drawImage(graph.createImage(imgName),getPosX(),getPosY(),getWidth(),getHeight());
        }

        if(text != ""){
            graph.setColor(0xFF000000);
            int size = 25;
            graph.setFont(graph.createFont("AARVC___.TTF",size,true,false));
            graph.drawText(text,getPosX() + getWidth()/2 - 30, getPosY() + getHeight()/2);
            graph.setColor(0xFFFFFFFF);
        }

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
