package com.example.practica2;

import com.example.androidengine.AndrGraphics2D;
import com.example.androidengine.TouchEvent;

import java.io.IOException;
import java.util.ArrayList;

public class ExploreWorldsScene extends Scene{
    Button nextWorld;
    Button prevWorld;
    //ArrayList<WorldLevelSelector> levelSelector;
    WorldLevelSelector levelSelector;

    protected ExploreWorldsScene(Logic logic) {
        super(logic);

        levelSelector = new WorldLevelSelector(30,30, logic);
        //levelSelector = new ArrayList<>();
    }

    @Override
    public void render(AndrGraphics2D graph) {
        super.render(graph);
        graph.drawImage(graph.createImage("world1_background.jpg"),0,0,500,800);

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
