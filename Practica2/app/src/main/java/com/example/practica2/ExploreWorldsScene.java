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
    protected ExploreWorldsScene(Logic logic) {
        super(logic);

        levelSelector = new WorldLevelSelector(30,30, logic);


        prevWorld = new Button(10,10,30,40, "prev", 0XFFD3D3D3, log.currEngine.getAudio());
        nextWorld = new Button(350,10,30,40, "next", 0XFFD3D3D3, log.currEngine.getAudio());

        //levelSelector = new ArrayList<>();
    }

    @Override
    public void render(AndrGraphics2D graph) {
        super.render(graph);
        graph.drawImage(graph.createImage("world4_bg.jpeg"),0,0,500,800);

        prevWorld.render(graph);
        nextWorld.render(graph);
        levelSelector.render(graph);
        /*for(WorldLevelSelector wls : levelSelector)
            wls.render(graph);*/

    }

    @Override
    public void handleInput(ArrayList<TouchEvent> event) {
        super.handleInput(event);

        levelSelector.handleInput(event);
    }
}
