package com.example.practica2;


import android.content.SharedPreferences;

import com.example.androidengine.AndrGraphics2D;
import com.example.androidengine.TouchEvent;

import java.util.ArrayList;

public class PlayScene extends Scene {

    Button exitButton, daltonismoButton;
    boolean daltonismoMode = false;
    MastermindBoard mb;
    boolean fromExploringWorlds = false; //booleano para saber si esta escena de juego se creo desde el modo partida rapida o explorar mundos, solucion rapida porque no tenemos pila de escenas
    public PlayScene(Logic logic,int i){
        super(logic);
        log=logic;
        fromExploringWorlds = false;

        int windowCenterX=log.getEngine().GetGraphics().getWidth()/2,
            windowCenterY=log.getEngine().GetGraphics().getHeight()/2;

        exitButton = new Button(windowCenterX - 190,windowCenterY - 290,20,20, "exit.png", log);
        //creamos tablero de juego con un nivel de  dificultad i
        mb = new MastermindBoard(log, i,windowCenterX - 175, windowCenterY - 215, 1, 1 );
        addGameObject(mb);

        daltonismoButton = new Button(windowCenterX + 190, windowCenterY - 290, 40, 40, "eyehide.png", log);
    }

    public PlayScene(Logic logic,Level L, World world){
        super(logic);
        log=logic;
        fromExploringWorlds= true;

        int windowCenterX=log.getEngine().GetGraphics().getWidth()/2,
                windowCenterY=log.getEngine().GetGraphics().getHeight()/2;

        exitButton = new Button(windowCenterX - 190,windowCenterY - 290,20,20, "exit.png",  log);
        //creamos tablero de juego con un nivel de  dificultad i
        mb = new MastermindBoard(log, L,windowCenterX - 175, windowCenterY - 215, 1, 1, world);
        addGameObject(mb);

        daltonismoButton = new Button(windowCenterX + 190, windowCenterY - 290, 40, 40, "eyehide.png", log);
    }

    @Override
    public void update(double deltaTime) {
        super.update(deltaTime);
        exitButton.update(deltaTime);
        daltonismoButton.update(deltaTime);

    }

    @Override
    public void render(AndrGraphics2D graph) {
        if(log.currBG < 4) {
            graph.drawImage(graph.createImage(backgroundImages[log.currBG]), log.getEngine().GetGraphics().getWidth()/2, log.getEngine().GetGraphics().getHeight()/2, log.getEngine().GetGraphics().getWidth(), log.getEngine().GetGraphics().getHeight());

            graph.setColor(0xFFFFFFFF);
            graph.fillRectangle(0,0,log.getEngine().GetGraphics().getWidth(), log.getEngine().GetGraphics().getHeight());
            graph.setColor(0xFF000000);
        }

        super.render(graph);

        exitButton.render(graph);
        daltonismoButton.render(graph);
    }

    @Override
    public void handleInput(ArrayList<TouchEvent> event) {
        super.handleInput(event);
        if(exitButton.handleInput(event))
            exitButtonEffect();
        if(daltonismoButton.handleInput(event))
            daltonismoButtonEffect();
    }

    void exitButtonEffect(){
        if(!fromExploringWorlds)
            log.SetScene(new LevelMenu(log));
        else
            log.SetScene(new ExploreWorldsScene(log));
    }

    void daltonismoButtonEffect(){
        daltonismoMode = !daltonismoMode;
        if(!daltonismoMode)
            daltonismoButton.setImgName("eyehide.png");
        else
            daltonismoButton.setImgName("eyeshow.png");

        mb.setDaltonismo(daltonismoMode);
    }

    @Override
    public void onLoad(SharedPreferences preferences) {
        mb.loadGameState(preferences);
    }

    @Override
    public void onSave(SharedPreferences.Editor preferencesEditor) {
        mb.saveGameState(preferencesEditor);
    }
}
