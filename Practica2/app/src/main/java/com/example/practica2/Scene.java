package com.example.practica2;


import android.content.SharedPreferences;

import com.example.androidengine.AndrGraphics2D;
import com.example.androidengine.TouchEvent;

import java.util.ArrayList;

public abstract class Scene {

    Logic log;

    //estas son las imagenes de fondo que podemos poner cuando cambiamos la skin de fondo en el menu de skins
    String[] backgroundImages = {"levels/world1/world_bg.jpg", "levels/world2/world_bg.jpeg", "levels/world3/world_bg.jpg", "levels/world4/world_bg.jpeg"};
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


    public void handleInput(ArrayList<TouchEvent> event/*, CAudio audio*/) {

        //cada objeto tiene su propio handle input
        for (GameObject object : objects) {
            object.handleInput(event/*,audio*/);

        }

    }

    public void addGameObject(GameObject gobj){
        objects.add(gobj);
    }

    public void onLoad(SharedPreferences preferences){

    }
    public void onSave(SharedPreferences.Editor preferencesEditor){ //devolvemos si la escena es una que queramos guardar

    }
}
