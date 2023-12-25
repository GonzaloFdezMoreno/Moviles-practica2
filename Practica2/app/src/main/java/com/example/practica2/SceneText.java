package com.example.practica2;

import com.example.androidengine.AndrGraphics2D;

public class SceneText extends GameObject{

    String txt;

    public SceneText(int posX_, int posY_, int width_, int height_, String text){
        super(posX_, posY_, width_, height_);

        txt=text;

    }

    @Override
    public void render(AndrGraphics2D graph) {

        graph.setFont(graph.createFont("stocky.ttf", 20, false, false));

        graph.drawText(txt,getPosX(),getPosY());

    }

    public void changeText(String text){
        txt = text;
    }
}
