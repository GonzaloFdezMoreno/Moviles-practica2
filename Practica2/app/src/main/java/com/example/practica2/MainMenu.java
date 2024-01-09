package com.example.practica2;


import com.example.androidengine.AndrGraphics2D;
import com.example.androidengine.TouchEvent;

import java.util.ArrayList;

public class MainMenu extends Scene {

    Button playButton;
    Button partidaRapida;
    Button explorarMundos;
    Button skins;
    public MainMenu(Logic logic){
        super(logic);
        log=logic;

        addGameObject(new SceneText(150,100,0,0,"MASTERMIND"));

        //playButton = new Button(100,400,150,50,"JUGAR", 0xFF1FE3E0);
        partidaRapida = new Button(100,300,150,50,"PARTIDA RAPIDA", 0xFF1FE3E0, log);
        explorarMundos = new Button(100,400,150,50,"EXPLORAR MUNDOS", 0xFF1FE3E0, log);
        skins = new Button(100,500,150,50,"SKINS", 0xFF1FE3E0, log);
    }

    @Override
    public void render(AndrGraphics2D graph) {
        if(log.currBG < 4) {
            graph.drawImage(graph.createImage(backgroundImages[log.currBG]), 0, 0, 400, 600);

            graph.setColor(0xFFFFFFFF);
            graph.fillRectangle(0,0,400, 50);
            graph.setColor(0xFF000000);
        }

        super.render(graph);



        partidaRapida.render(graph);
        explorarMundos.render(graph);
        skins.render(graph);

    }

    @Override
    public void update(double deltaTime) {
        super.update(deltaTime);
        partidaRapida.update(deltaTime);
        explorarMundos.update(deltaTime);
        skins.update(deltaTime);
    }

    @Override
    public void handleInput(ArrayList<TouchEvent> event) {
        super.handleInput(event);
        if(partidaRapida.handleInput(event))
            playButtonEffect();
        if(explorarMundos.handleInput(event))
            explorarMundosEffect();
        if(skins.handleInput(event))
            skinsEffect();

    }

    void playButtonEffect(){
        log.SetScene(new LevelMenu(log));
    }
    void explorarMundosEffect(){
        log.SetScene(new ExploreWorldsScene(log));
    }

    void skinsEffect(){
        log.SetScene(new SkinScene(log));
    }
}
