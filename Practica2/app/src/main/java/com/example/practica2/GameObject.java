package com.example.practica2;

import com.example.androidengine.AndrGraphics2D;
import com.example.androidengine.TouchEvent;

import java.util.ArrayList;

public abstract class GameObject {
    private int posX, posY, width, height;

    ArrayList<GameObject> hijos;
    public GameObject(int posX_, int posY_, int width_, int height_) {
        posX = posX_;
        posY = posY_;
        width = width_;
        height = height_;

        hijos = new ArrayList<GameObject>();
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosX(int posX_) {
        int deltaX = posX_ - posX; // Calculamos el cambio en la posicion
        posX = posX_;

        for(GameObject g : hijos)
            g.setPosX(posX_ + g.getPosX() + deltaX);
    }

    public void setPosY(int posY_) {
        int deltaY = posY_ - posY; // Calculamos el cambio en la posicion

        posY = posY_;

        for (GameObject child : hijos) {
            child.setPosY(child.getPosY() + deltaY);
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    void addChild(GameObject hijo){
        hijos.add(hijo);
    }
    public void render(AndrGraphics2D graph){
    }

    public void update(double delta) {
    }

    public boolean handleInput(ArrayList<TouchEvent> event /*,CAudio audio*/) {

        return true;
    }
}
