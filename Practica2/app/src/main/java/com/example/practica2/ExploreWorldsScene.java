package com.example.practica2;

import com.example.androidengine.AndrGraphics2D;
import com.example.androidengine.TouchEvent;

import java.util.ArrayList;

public class ExploreWorldsScene extends Scene{
    WorldLevelSelector levelSelector;
    protected ExploreWorldsScene(Logic logic) {
        super(logic);

        levelSelector = new WorldLevelSelector(70,30, logic);

    }

    @Override
    public void render(AndrGraphics2D graph) {
        super.render(graph);
        graph.drawImage(graph.createImage("world1_background.jpg"),0,0,500,800);
        levelSelector.render(graph);

    }

    @Override
    public void handleInput(ArrayList<TouchEvent> event) {
        super.handleInput(event);
    }
}
