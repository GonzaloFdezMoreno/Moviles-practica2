package com.example.logic;

import com.example.commonengine.Cengine;

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
}
