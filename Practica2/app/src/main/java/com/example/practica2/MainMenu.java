package com.example.practica2;


import com.example.androidengine.AndrGraphics2D;
import com.example.androidengine.AndroidEngine;
import com.example.androidengine.AndroidImage;
import com.example.androidengine.TouchEvent;

import java.util.ArrayList;

public class MainMenu extends Scene {

    Button playButton;
    Button partidaRapida;
    Button explorarMundos;
    Button skins;

    AndroidImage bgImage;
    AndroidImage emoji, emojiSun;
    LightManager lightManager;
    float lightTolerance = 100;
    public MainMenu(Logic logic){
        super(logic);
        log=logic;
        int windowCenterX=logic.getEngine().GetGraphics().getWidth()/2,
            windowCenterY=logic.getEngine().GetGraphics().getHeight()/2;

        addGameObject(new SceneText(windowCenterX, windowCenterY- 250,0,0,"MASTERMIND"));

        partidaRapida = new Button(windowCenterX,windowCenterY,200,50,"PARTIDA RAPIDA", 0xFF1FE3E0, log);
        explorarMundos = new Button(windowCenterX,windowCenterY + 100,200,50,"EXPLORAR MUNDOS", 0xFF1FE3E0, log);
        skins = new Button(windowCenterX,windowCenterY + 200,200,50,"SKINS", 0xFF1FE3E0, log);

        lightManager = new LightManager(((AndroidEngine)logic.getEngine()).getContext());

        emoji = logic.getEngine().GetGraphics().createImage("sprites/emojis/amarillo.png");
        emojiSun = logic.getEngine().GetGraphics().createImage("sprites/emojis/cool.png");
    }

    @Override
    public void render(AndrGraphics2D graph) {
        if(log.currBG < 4) {
            bgImage = graph.createImage(backgroundImages[log.currBG]);
            graph.drawImage(bgImage, graph.getWidth()/2, graph.getHeight()/2, 800, 1200);
        }

        if(lightManager.getLight() > lightTolerance)
            graph.drawImage(emojiSun, graph.getWidth()/2, graph.getHeight()/2 - 150, 200, 200);
        else
            graph.drawImage(emoji, graph.getWidth()/2, graph.getHeight()/2 - 150, 200, 200);


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
