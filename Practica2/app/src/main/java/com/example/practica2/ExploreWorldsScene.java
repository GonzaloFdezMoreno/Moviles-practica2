package com.example.practica2;

import com.example.androidengine.AndrGraphics2D;
import com.example.androidengine.TouchEvent;

import java.io.IOException;
import java.util.ArrayList;

public class ExploreWorldsScene extends Scene{
    Button nextWorld;
    Button prevWorld;
    //ArrayList<WorldLevelSelector> levelSelector;
    int currentWorld = 0;
    WorldLevelSelector levelSelector;

    String[] worldBackgroundImages = {"world1_background.jpg", "world2_bg.jpeg", "world3_bg.jpg", "world4_bg.jpeg"};
    String[] worldFolderNames = {"world1", "world2","world3","world4"};
    protected ExploreWorldsScene(Logic logic) {
        super(logic);

        levelSelector = new WorldLevelSelector(30,-40, worldFolderNames, logic);


        prevWorld = new Button(10,10,30,40, "PrevArrows.png", log.currEngine.getAudio());
        nextWorld = new Button(350,10,30,40, "NextArrows.png", log.currEngine.getAudio());

    }

    @Override
    public void render(AndrGraphics2D graph) {
        super.render(graph);
        graph.drawImage(graph.createImage(worldBackgroundImages[currentWorld]),0,0,400,600);

        prevWorld.render(graph);
        nextWorld.render(graph);
        levelSelector.render(graph);
    }

    @Override
    public void handleInput(ArrayList<TouchEvent> event) {
        super.handleInput(event);

        if(prevWorld.handleInput(event)){
            if(currentWorld > 0){
                currentWorld--;
                levelSelector.setCurrWorld(currentWorld);
            }
        }

        if(nextWorld.handleInput(event)){
            if(currentWorld < 3){
                currentWorld++;
                //loadLevelButtons(worldFolderNames[currentWorld]);
                levelSelector.setCurrWorld(currentWorld);
            }
        }
        levelSelector.handleInput(event);
    }
}
