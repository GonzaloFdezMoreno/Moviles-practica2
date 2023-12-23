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
    int numLevelsBeaten = 0;
    int numLevels = 0;
    int widthMargin = 120, heightMargin = 120; //margenes entre botones de niveles
    ArrayList<String[]> worldLevelFileNames;
    String[] worldNames;
    ArrayList<ArrayList<Button>> allWorldLevels; //aqui cargamos todos los botones cuando creamos la clase
    Logic log;
    int currWorld = 0;
    WorldLevelSelector(int posX_, int posY_, String[] worldNames_, Logic log_){
        posX = posX_; posY = posY_;
        worldNames = worldNames_;

        log = log_;

        allWorldLevels = new ArrayList<>();
        for(int i = 0; i < worldNames.length; ++i)
            allWorldLevels.add(new ArrayList<>());

        worldLevelFileNames = new ArrayList<>();

        int l = 0;
        for(ArrayList<Button> a : allWorldLevels){
            getLevels(worldNames[l]);

            for (int i = 0, j = 0, k = 0; i < numLevels; ++i, ++k){
                if(i%3==0){
                    k = 0;
                    ++j;
                }

                a.add(new Button(posX + k * widthMargin, posY + j * heightMargin, 110, 110,
                        String.valueOf(i + 1), 0XDDD3D3D3,log.currEngine.getAudio(), log.currEngine.getSound()));
            }
            ++l;
        }

    }

    void getLevels(String worldName){ //cogemos los archivos en la carpeta correspondiente al mundo y asignamos cuantos hay
        try{
            String[] lvls = log.getEngine().getaJsonlodr().getAssetsDirectory("levels/" + worldName);
            worldLevelFileNames.add(lvls);

            numLevels = lvls.length;
            int i = 1;
        }
        catch(IOException e){
            System.err.println(e.getMessage());
        }

    }

    public void render(AndrGraphics2D graph){
        for(Button b : allWorldLevels.get(currWorld))
            b.render(graph);
    }

    public void handleInput(ArrayList<TouchEvent> event){
        int i = 0;
        for(Button b : allWorldLevels.get(currWorld)){
            if(b.handleInput(event)){
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

    void setCurrWorld(int i){
        currWorld = i;
    }
    void loadLevelJSON(int n) throws IOException {
        String filename = "levels/" + worldNames[currWorld] + "/" + worldLevelFileNames.get(currWorld)[n];

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
