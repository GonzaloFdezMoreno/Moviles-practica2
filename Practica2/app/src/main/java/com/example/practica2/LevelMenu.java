package com.example.practica2;


import com.example.androidengine.AndrGraphics2D;
import com.example.androidengine.TouchEvent;

import java.util.ArrayList;

public class LevelMenu extends Scene {

    Button easyButton, mediumButton, hardButton, insaneButton;
    Button goBackButton;
    public LevelMenu(Logic log){
        super(log);

        int windowCenterX=log.getEngine().GetGraphics().getWidth()/2,
            windowCenterY=log.getEngine().GetGraphics().getHeight()/2;

        goBackButton = new Button(windowCenterX - 180,windowCenterY - 290,20,20, "goback.png", log);
        addGameObject(new SceneText(windowCenterX,(windowCenterY/3),0,0,"SELECCIONA UN NIVEL"));

        easyButton = new Button(windowCenterX,windowCenterY - 100,200,50,"FACIL", 0xFF25E31F, log);
        mediumButton = new Button(windowCenterX,windowCenterY - 40,200,50,"MEDIO", 0xFFE3E31F, log);
        hardButton = new Button(windowCenterX,windowCenterY + 20,200,50,"DIFICIL", 0xFFE3991F, log);
        insaneButton = new Button(windowCenterX,windowCenterY + 80,200,50,"IMPOSIBLE", 0xFFE3371F,  log);
    }

    @Override
    public void render(AndrGraphics2D graph) {
        if(log.currBG < 4) {
            graph.drawImage(graph.createImage(backgroundImages[log.currBG]), log.getEngine().GetGraphics().getWidth()/2, log.getEngine().GetGraphics().getHeight()/2, 800, 1200);

        }

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
