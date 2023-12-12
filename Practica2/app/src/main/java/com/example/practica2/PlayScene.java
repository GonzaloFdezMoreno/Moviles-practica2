package com.example.practica2;


import com.example.androidengine.AndrGraphics2D;
import com.example.androidengine.TouchEvent;

import java.util.ArrayList;

public class PlayScene extends Scene {

    Button exitButton, daltonismoButton;
    boolean daltonismoMode = false;
    MastermindBoard mb;
    public PlayScene(Logic logic,int i){
        super(logic);
        log=logic;
        
        exitButton = new Button(10,10,20,20, "exit.png", log.currEngine.getAudio());
        //creamos tablero de juego con un nivel de  dificultad i
        mb = new MastermindBoard(log, i,25, 75, 1, 1 );
        addGameObject(mb);

        daltonismoButton = new Button(350, 5, 40, 40, "eyehide.png", log.currEngine.getAudio());
    }

    public PlayScene(Logic logic,Level L){
        super(logic);
        log=logic;

        exitButton = new Button(10,10,20,20, "exit.png", log.currEngine.getAudio());
        //creamos tablero de juego con un nivel de  dificultad i
        mb = new MastermindBoard(log, L,25, 75, 1, 1 );
        addGameObject(mb);

        daltonismoButton = new Button(350, 5, 40, 40, "eyehide.png", log.currEngine.getAudio());
    }

    @Override
    public void update(double deltaTime) {
        super.update(deltaTime);
        exitButton.update(deltaTime);
        daltonismoButton.update(deltaTime);

    }

    @Override
    public void render(AndrGraphics2D graph) {
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
        log.SetScene(new LevelMenu(log));
    }

    void daltonismoButtonEffect(){
        daltonismoMode = !daltonismoMode;
        if(!daltonismoMode)
            daltonismoButton.setImgName("eyehide.png");
        else
            daltonismoButton.setImgName("eyeshow.png");

        mb.setDaltonismo(daltonismoMode);
    }
}
