package com.example.practica2;


import com.example.androidengine.AndrGraphics2D;
import com.example.androidengine.TouchEvent;

import java.util.ArrayList;

public class LevelMenu extends Scene {

    Button easyButton, mediumButton, hardButton, insaneButton;
    Button goBackButton;
    public LevelMenu(Logic log){
        super(log);

        goBackButton = new Button(20,20,20,20, "goback.png", log.currEngine.getAudio());
        addGameObject(new SceneText(75,100,0,0,"SELECCIONA UN NIVEL"));

        easyButton = new Button(100,200,200,50,"FACIL", 0xFF25E31F, log.currEngine.getAudio());
        mediumButton = new Button(100,260,200,50,"MEDIO", 0xFFE3E31F, log.currEngine.getAudio());
        hardButton = new Button(100,320,200,50,"DIFICIL", 0xFFE3991F, log.currEngine.getAudio());
        insaneButton = new Button(100,380,200,50,"IMPOSIBLE", 0xFFE3371F, log.currEngine.getAudio());
    }

    @Override
    public void render(AndrGraphics2D graph) {
        super.render(graph);
        goBackButton.render(graph);
        easyButton.render(graph);
        mediumButton.render(graph);
        hardButton.render(graph);
        insaneButton.render(graph);
    }

    @Override
    public void update(double deltaTime) {
        super.update(deltaTime);
        goBackButton.update(deltaTime);
        easyButton.update(deltaTime);
        mediumButton.update(deltaTime);
        hardButton.update(deltaTime);
        insaneButton.update(deltaTime);
    }

    @Override
    public void handleInput(ArrayList<TouchEvent> event) {
        super.handleInput(event);

        if(goBackButton.handleInput(event))
            goBackButtonEffect();
        if(easyButton.handleInput(event))
            difficultyButtonsEffect(0);
        if(mediumButton.handleInput(event))
            difficultyButtonsEffect(1);
        if(hardButton.handleInput(event))
            difficultyButtonsEffect(2);
        if(insaneButton.handleInput(event))
            difficultyButtonsEffect(3);
    }

    void goBackButtonEffect(){
        log.SetScene(new MainMenu(log));
    }
    void difficultyButtonsEffect(int difficulty){
        log.SetScene(new PlayScene(log,difficulty));
    }
}
