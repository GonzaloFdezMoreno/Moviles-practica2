package com.example.practica2;


import com.example.androidengine.AndrGraphics2D;
import com.example.androidengine.TouchEvent;

import java.util.ArrayList;

public abstract class Scene {

    Logic log;

    private final ArrayList<GameObject> objects = new ArrayList<>();



    protected Scene(Logic logic){
        this.log = logic;

    }

    public void init() {

    }


    public void render(AndrGraphics2D graph) {
        //Renderizamos todos los objetos de la escena
        for (GameObject object : objects) {
            object.render(graph);
        }
 }


    public void update(double deltaTime) {
        //mastermindBoard.update(deltaTime);

        //actualizamos cada objeto
        for (GameObject object : objects) {
            object.update(deltaTime);
        }
    }


    public void handleInput(ArrayList<TouchEvent> event, CAudio audio) {

        //cada objeto tiene su propio handle input
        for (GameObject object : objects) {
            object.handleInput(event,audio);

        }

    }

    public void addGameObject(GameObject gobj){
        objects.add(gobj);
    }


}
