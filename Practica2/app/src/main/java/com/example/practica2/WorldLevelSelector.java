package com.example.practica2;

import android.util.JsonReader;
import android.view.ViewDebug;

import com.example.androidengine.AndrGraphics2D;
import com.example.androidengine.TouchEvent;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class WorldLevelSelector {
    int posX, posY;
    int widthMargin = 120, heightMargin = 120; //margenes entre botones de niveles
    int currWorld = 0;
    Button aux;
    ArrayList<World> worlds;
    WorldLevelSelector(int posX_, int posY_, String[] worldNames_, Logic log_){
        posX = posX_; posY = posY_;

        aux = new Button(posX + widthMargin, posY - 20, 110, 110, "exit.png", true, log_.currEngine.getAudio(), log_.currEngine.getSound());

        worlds = new ArrayList<>();

        for(int i = 0; i < worldNames_.length; ++i){
            worlds.add(new World(posX_, posY_, widthMargin, heightMargin, worldNames_[i], this, log_));
        }


        //ponemos el primer nivel a jugable
        worlds.get(currWorld).levelComplete();
    }

    public void render(AndrGraphics2D graph){
        aux.render(graph);

        worlds.get(currWorld).render(graph);
    }
    public void handleInput(ArrayList<TouchEvent> event){
        if(aux.handleInput(event))
            worlds.get(currWorld).levelComplete();

        worlds.get(currWorld).handleInput(event);

    }
    void setCurrWorld(int i){
        currWorld = i;
    }
    void startNextWorld(){
        if(currWorld < worlds.size()-1)
            worlds.get(currWorld+1).levelComplete();
    }


}
