package com.example.practica2;


import android.graphics.Paint;
import android.util.Log;

import com.example.androidengine.AndrGraphics2D;
import com.example.androidengine.AndroidAudio;
import com.example.androidengine.AndroidFont;
import com.example.androidengine.AndroidImage;
import com.example.androidengine.AndroidSound;
import com.example.androidengine.TouchEvent;

import java.util.ArrayList;

public class Button extends GameObject {

    //ok para que el audio funcione hacer que la escena le pase el audio de engine al boton al crearlo y
    //asi tiene acceso a audio el boton (que la escena coja el audio de logic y se lo da al boton)
    private AndroidSound esound;
    private AndroidAudio andAudio;

    String text = "";
    String imgName = "";
    int color = 0xFFFFFFFF;
    boolean background = false;

    Logic log;
    AndroidImage Image;

    //boton con imagen
    Button(int posX_, int posY_, int width_, int height_, String imgName_, Logic log_){
        super(posX_, posY_, width_, height_);
        imgName = imgName_;

        andAudio = log_.currEngine.getAudio();
        esound = log_.currEngine.getSound();
        log = log_;
        Image = log_.getEngine().GetGraphics().createImage(imgName_);
    }

    //he creado esta version con el booleano background porque no tengo tiempo de cambiar cada uno de las constructoras de botones para que incluyan un booleano inicializado a false
    //botones de fondo en los mundos
    Button(int posX_, int posY_, int width_, int height_, String imgName_, boolean background_, Logic log_){
        super(posX_, posY_, width_, height_);
        imgName = imgName_;
        andAudio = log_.currEngine.getAudio();
        esound = log_.currEngine.getSound();

        background = background_;

        log = log_;
        Image = log_.getEngine().GetGraphics().createImage(imgName_);

    }

    //botones que solo usan colores
    Button(int posX_, int posY_, int width_, int height_, String text_, int color_, Logic log_){
        super(posX_, posY_, width_, height_);
        text = text_;
        color = color_;
        andAudio = log_.currEngine.getAudio();
        esound = log_.currEngine.getSound();

        log = log_;
    }

    @Override
    public boolean handleInput(ArrayList<TouchEvent> event) {

        boolean pressed = false;
        for(TouchEvent events : event) {
            //comprobamos que esta dentro de los limites del boton

            if((this.getPosX() - getWidth()/2) < events.x && this.getPosX() + this.getWidth()/2 > events.x
                    && this.getPosY() - getHeight()/2 < events.y && this.getPosY() + getHeight()/2 > events.y) {

                if (events.type == TouchEvent.TouchEventType.TOUCH_DOWN) {
                    System.out.println("ButtonPressed\n");
                    onTouchDown();
                    andAudio.playSound(esound);
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
        if(imgName == "" || background){
            graph.setColor(color);
            graph.fillRoundRectangle(getPosX() - getWidth()/2, getPosY() - getHeight()/2, getWidth(), getHeight(), 15, 10);
            graph.setColor(0xFFFFFFFF);

        }

        if(imgName != ""){
            graph.drawImage(Image,getPosX(),getPosY(),getWidth(),getHeight());
        }

        if(text != ""){
            graph.setColor(0xFF000000);
            int size = 25;
            AndroidFont font = graph.createFont("AARVC___.TTF",size,true,false);

            graph.setFont(font);
            graph.drawText(text,getPosX(), getPosY());
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
        log.getEngine().GetGraphics().createImage(imgName);
    }

    //nuestro boton pasa de tener una imagen a renderizar texto
    void changeButtonTypeToNoImg(String newText){
        imgName = "";
        text = newText;
    }
}
