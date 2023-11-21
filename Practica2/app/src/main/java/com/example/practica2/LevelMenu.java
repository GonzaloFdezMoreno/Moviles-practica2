package com.example.practica2;


import com.example.androidengine.AndrGraphics2D;
import com.example.androidengine.TouchEvent;

import java.util.ArrayList;

public class LevelMenu extends Scene {

    public LevelMenu(Logic log){
        super(log);


        //CGraphics2D grph;

        //grph= engine.getGraphics();

        //grph.drawRectangle(200,200,100,100);
        addGameObject(new GoBackMenuButton(20,20,20,20,log));
        addGameObject(new SceneText(75,100,0,0,"SELECCIONA UN NIVEL"));
        addGameObject(new LevelButton(100,200,200,50,"FACIL", log));
        addGameObject(new LevelButton(100,300,200,50,"NORMAL", log));
        addGameObject(new LevelButton(100,400,200,50,"DIFICIL", log));
        addGameObject(new LevelButton(100,500,200,50,"EXTREMO", log));






    }

    @Override
    public void render(AndrGraphics2D graph) {
        super.render(graph);
    }

    @Override
    public void update(double deltaTime) {
        super.update(deltaTime);
    }

    @Override
    public void handleInput(ArrayList<TouchEvent> event) {
        super.handleInput(event);
    }
}
