package com.example.practica2;



import com.example.androidengine.AndrGraphics2D;
import com.example.androidengine.AndroidEngine;
import com.example.androidengine.CLogic;
import com.example.androidengine.EngineClass;
import com.example.androidengine.TouchEvent;

import java.util.ArrayList;

public class Logic implements CLogic {
    Scene currScene;
    EngineClass currEngine;

    //AndroidEngine currEngine;

    public Logic(EngineClass engine){ //AndroidEngine engine){
        //empezamos siempre desde el Menú principal

        this.currEngine=engine;

        this.currScene=new MainMenu(this);
    }

    public Scene getScene() {
        return currScene;
    }

    public EngineClass getEngine() {
        return currEngine;
    }

    //public AndroidEngine getEngine() {
        //return currEngine;
    //}

    //Unicamente debemos renderizar, actualizar y manejar los inputs
    // de la escena que esté mostrandose.
    @Override
    public void update(double t) {
        currScene.update(t);

    }

    @Override
    public void render(AndrGraphics2D graph) {
        currScene.render(graph);


    }

    @Override
    public void handleEvents(ArrayList<TouchEvent> input) {
       currScene.handleInput(input);
    }


    public void SetScene(Scene scene){
        currScene=scene;
    }






}