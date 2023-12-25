package com.example.practica2;

import android.util.JsonReader;

import com.example.androidengine.AndrGraphics2D;
import com.example.androidengine.TouchEvent;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class World {
    String worldName;
    Logic log;
    int numLevelsBeaten = 0;
    boolean[] levelsAvailable; //que niveles estan disponibles para jugar
    int numLevels = 0;
    String[] worldLevelFileNames;
    ArrayList<Button> buttonsLevels; //aqui cargamos todos los botones cuando creamos la clase

    World(int posX_, int posY_, int widthMargin_, int heightMargin_, String worldName_, Logic log_){
        worldName = worldName_;
        log = log_;

        //colocamos botones
        getLevels(worldName);

        buttonsLevels = new ArrayList<>();
        for (int i = 0, j = 0, k = 0; i < numLevels; ++i, ++k){
            if(i%3==0){
                k = 0;
                ++j;
            }

            buttonsLevels.add(new Button(posX_ + k * widthMargin_, posY_ + j * heightMargin_, 110, 110, "lock.png", true, log.currEngine.getAudio(), log.currEngine.getSound()));

        }
    }

    public void render(AndrGraphics2D graph){
        for(Button b : buttonsLevels)
            b.render(graph);
    }
    public void handleInput(ArrayList<TouchEvent> event){
        int i = 0;
        for(Button b : buttonsLevels){
            if(levelsAvailable[i] && b.handleInput(event)){
                try{
                    loadLevelJSON(i);
                }
                catch (IOException e){
                    System.err.println(e.getMessage());
                }
            }
            ++i;
        }
    }

    void getLevels(String worldName){ //cogemos los archivos en la carpeta correspondiente al mundo y asignamos cuantos hay
        try{
            worldLevelFileNames = log.getEngine().getaJsonlodr().getAssetsDirectory("levels/" + worldName);

            numLevels = worldLevelFileNames.length;

            levelsAvailable = new boolean[numLevels];
        }
        catch(IOException e){
            System.err.println(e.getMessage());
        }

    }
    //cuando completemos un nivel llamamos aqui
    void levelComplete(){
        buttonsLevels.get(numLevelsBeaten).color = 0xFFFFFFFF;

        if(numLevelsBeaten > 0)
            buttonsLevels.get(numLevelsBeaten-1).color = 0XDDD3D3D3;

        if(numLevelsBeaten < levelsAvailable.length){
            levelsAvailable[numLevelsBeaten] = true;
            buttonsLevels.get(numLevelsBeaten).changeButtonTypeToNoImg(String.valueOf(numLevelsBeaten + 1));
        }
        else{
            //levelsAvailablePerWorld.get(currWorld+1)[0] = true;
            //allWorldLevels.get(currWorld+1).get(numLevelsBeaten-).changeButtonTypeToNoImg(String.valueOf(numLevelsBeaten + 1));
        }

        numLevelsBeaten++;

       /* if(numLevelsBeaten >= worldLevelFileNames.get(currWorld).length)
            worldsBeaten[currWorld] = true;*/

    }

    void loadLevelJSON(int n) throws IOException {
        String filename = "levels/" + worldName + "/" + worldLevelFileNames[n];

        InputStream is = log.getEngine().getaJsonlodr().getResource(filename);
        try {
            Level L = readJsonStream(is);

            if(L.codeOpt <= -1 || L.codeSize <= -1 || L.attempts <= -1){
                throw new IllegalArgumentException("Level file didnt have correct formatting");
            }
            log.SetScene(new PlayScene(log,L));

        }
        catch(IllegalArgumentException e){
            System.err.println(e);

        }
    }
    public Level readJsonStream(InputStream in) throws IOException { //creamos el JsonReader
        JsonReader reader = new JsonReader(new InputStreamReader(in, "UTF-8"));
        try {
            return readLevel(reader);
        } finally {
            reader.close();
        }
    }
    public Level readLevel(JsonReader reader) throws IOException {
        //los tipos que estan en el json
        int codeSize = -1;
        int codeOpt = -1;
        boolean repeat = false;
        int attempts = -1;

        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals("codeSize")) {
                codeSize = reader.nextInt();
            } else if (name.equals("codeOpt")) {
                codeOpt = reader.nextInt();

            } else if (name.equals("repeat")) {
                repeat = reader.nextBoolean();

            } else if (name.equals("attempts")) {
                attempts = reader.nextInt();;

            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return new Level(codeSize, codeOpt, repeat, attempts);
    }
}
