package com.example.logic;

import com.example.commonengine.CAudio;
import com.example.commonengine.CGraphics2D;
import com.example.commonengine.CLogic;

import com.example.commonengine.EngineClass;
import com.example.commonengine.TouchEvent;

import java.util.ArrayList;

public class Logic implements CLogic {
    Scene currScene;
    //EngineClass currEngine;


    public Logic(EngineClass engine){
        //epezamos siempre desde el Menú principal
        this.currScene=new MainMenu(this);

        //this.currEngine=engine;

    }

    public Scene getScene() {
        return currScene;
    }
    /*public EngineClass getEngine() {
        return currEngine;
    }*/

    //Unicamente debemos renderizar, actualizar y manejar los inputs
    // de la escena que esté mostrandose.
    @Override
    public void update(double t) {
        currScene.update(t);

    }

    @Override
    public void render(CGraphics2D graph) {
        currScene.render(graph);


    }

    @Override
    public void handleEvents(ArrayList<TouchEvent> input, CAudio audio) {
       currScene.handleInput(input,audio);
    }


    public void SetScene(Scene scene){
        currScene=scene;
    }






}