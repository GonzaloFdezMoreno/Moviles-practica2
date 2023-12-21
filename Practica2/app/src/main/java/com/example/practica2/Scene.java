package com.example.practica2;


import com.example.androidengine.AndrGraphics2D;
import com.example.androidengine.TouchEvent;

import java.util.ArrayList;

public abstract class Scene {

    Logic log;

    //estas son las imagenes de fondo que podemos poner cuando cambiamos la skin de fondo en el menu de skins
    String[] backgroundImages = {"world1_background.jpg", "world2_bg.jpeg", "world3_bg.jpg", "world4_bg.jpeg"};

    String sceneName = "no_scene_name"; //usamos esto para que desde logic en la funcion de guardar
                                        // escena podamos saber en cual estamos. Para ello solamente
                                        //PlayScene va a tener un nombre distinto, que asignamos en su constructora

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

}
