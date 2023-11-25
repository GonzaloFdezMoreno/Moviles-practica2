package com.example.practica2;


import com.example.androidengine.AndrGraphics2D;
import com.example.androidengine.TouchEvent;

import java.util.ArrayList;

public class MainMenu extends Scene {

    Button playButton;
    public MainMenu(Logic logic){
        super(logic);
        log=logic;

        addGameObject(new SceneText(150,100,0,0,"MASTERMIND"));

        playButton = new Button(100,400,150,50,"JUGAR", 0xFF1FE3E0);

    }

    @Override
    public void render(AndrGraphics2D graph) {
        super.render(graph);
        playButton.render(graph);
    }

    @Override
    public void update(double deltaTime) {
        super.update(deltaTime);
        playButton.update(deltaTime);
    }

    @Override
    public void handleInput(ArrayList<TouchEvent> event) {
        super.handleInput(event);
        if(playButton.handleInput(event))
            playButtonEffect();
    }

    void playButtonEffect(){
        log.SetScene(new LevelMenu(log));
    }
}
