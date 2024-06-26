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
    Button aux; //para poner un nivel como resuelto automaticamente
    Button currWorldText; //no tenemos un objeto texto, esto hara de texto del nombre del mundo pero no se podra pulsar
    ArrayList<World> worlds;
    int[] levelsCompletedByWorld;
    Logic log;
    WorldLevelSelector(int posX_, int posY_, String[] worldNames_, Logic log_){
        posX = posX_; posY = posY_;

        int windowCenterX=log_.getEngine().GetGraphics().getWidth()/2,
                windowCenterY=log_.getEngine().GetGraphics().getHeight()/2;

        aux = new Button(windowCenterX - 180, posY - 20, 30, 30, "eyeshow.png", true, log_);
        currWorldText = new Button(windowCenterX, 10, 110, 40, worldNames_[0], 0xFF1FE3E0,  log_);
        worlds = new ArrayList<>();

        for(int i = 0; i < worldNames_.length; ++i){
            worlds.add(new World(posX_, posY_, widthMargin, heightMargin, worldNames_[i], this, log_, i));
        }

        //ponemos el primer nivel a jugable
        worlds.get(currWorld).levelComplete();

        levelsCompletedByWorld = new int[worlds.size()];

        log = log_;

        LoadWorldCompletedLevels();
    }

    public void render(AndrGraphics2D graph){
        aux.render(graph);

        worlds.get(currWorld).render(graph);
        currWorldText.render(graph);
    }
    public void handleInput(ArrayList<TouchEvent> event){
        if(aux.handleInput(event)){
            worlds.get(currWorld).levelComplete();
            worlds.get(currWorld).saveLevelsBeaten();
        }

        worlds.get(currWorld).handleInput(event);

    }
    void setCurrWorld(int i){
        currWorld = i;
        currWorldText.text = worlds.get(currWorld).worldName;
    }
    void startNextWorld(int worldToStart){
        if(worldToStart < worlds.size())
            worlds.get(worldToStart).levelComplete();
    }

    void LoadWorldCompletedLevels(){
        levelsCompletedByWorld = log.loadLevelsCompleted(worlds.size());

        int i = 0;
        for(World w : worlds){
            for(int ii = 0; ii < levelsCompletedByWorld[i]; ++ii)
                w.levelComplete();
            ++i;
        }
    }
    void SaveWorldLevelsComplete(){
        log.saveLevelsCompleted(levelsCompletedByWorld);
    }

}
