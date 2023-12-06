package com.example.practica2;


import com.example.androidengine.AndrGraphics2D;
import com.example.androidengine.TouchEvent;

import java.util.ArrayList;

public class MainMenu extends Scene {

    Button playButton;
    Button partidaRapida;
    Button explorarMundos;
    public MainMenu(Logic logic){
        super(logic);
        log=logic;

        addGameObject(new SceneText(150,100,0,0,"MASTERMIND"));

        //playButton = new Button(100,400,150,50,"JUGAR", 0xFF1FE3E0);
        partidaRapida = new Button(100,300,150,50,"PARTIDA RAPIDA", 0xFF1FE3E0, logic.currEngine.getAudio());
        explorarMundos = new Button(100,400,150,50,"EXPLORAR MUNDOS", 0xFF1FE3E0, logic.currEngine.getAudio());
    }

    @Override
    public void render(AndrGraphics2D graph) {
        super.render(graph);
        partidaRapida.render(graph);
        explorarMundos.render(graph);
    }

    @Override
    public void update(double deltaTime) {
        super.update(deltaTime);
        partidaRapida.update(deltaTime);
        explorarMundos.update(deltaTime);
    }

    @Override
    public void handleInput(ArrayList<TouchEvent> event) {
        super.handleInput(event);
        if(partidaRapida.handleInput(event))
            playButtonEffect();
        if(explorarMundos.handleInput(event))
            explorarMundosEffect();
    }

    void playButtonEffect(){
        log.SetScene(new LevelMenu(log));
    }
    void explorarMundosEffect(){
        log.SetScene(new ExploreWorldsScene(log));
    }
}
