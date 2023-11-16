package com.example.practica2;


import com.example.androidengine.AndrGraphics2D;

public class Pista extends GameObject{

    int state = 0;  //0 -> defecto/color no esta en codigo
                    //1 -> color existe, pos correcta
                    //2 -> color existe, pos incorrecta

    public Pista(int posX_, int posY_, int width_, int height_) {
        super(posX_, posY_, width_, height_);
    }

    @Override
    public void render(AndrGraphics2D graph) {
        switch (state){
            case 0:
                graph.setColor(0xFFc7cbd1);
                graph.fillCircle(getPosX(),getPosY(),getWidth(),getHeight());
                break;
            case 1:
                graph.setColor(0xFF000000);
                graph.fillCircle(getPosX(),getPosY(),getWidth(),getHeight());
                break;
            case 2:
                graph.drawCircle(getPosX(),getPosY(),getWidth(),getHeight());
                break;
            default:
                break;
        }

        graph.setColor(0xFF000000);

    }

    void setState(int state_){ //0, 1 o 2
        state = state_;
    }
}
