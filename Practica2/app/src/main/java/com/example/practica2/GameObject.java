package com.example.practica2;

import com.example.androidengine.AndrGraphics2D;
import com.example.androidengine.TouchEvent;

import java.util.ArrayList;

public abstract class GameObject {
    private int posX, posY, width, height;

    public GameObject(int posX_, int posY_, int width_, int height_) {
        posX = posX_;
        posY = posY_;
        width = width_;
        height = height_;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosX(int posX_) {
        posX = posX_;
    }

    public void setPosY(int posY_) {
        posY = posY_;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void render(AndrGraphics2D graph){
    }

    public void update(double delta) {
    }

    public boolean handleInput(ArrayList<TouchEvent> event /*,CAudio audio*/) {

        return true;
    }
}
