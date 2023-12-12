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
    ArrayList<Button> worldLevels;
    Logic log;
    WorldLevelSelector(int posX_, int posY_, Logic log_){
        posX = posX_; posY = posY_;
        log = log_;

        worldLevels = new ArrayList<>();

        getLevels();

        for (int i = 0, j = 0, k = 0; i < numLevels; ++i, ++k){
            if(i%3==0){
                k = 0;
                ++j;
            }
            worldLevels.add(new Button(posX + k * widthMargin, posY + j * heightMargin, 110, 110,
                    String.valueOf(i + 1), 0XDDD3D3D3,log.currEngine.getAudio()));
        }
    }

    void getLevels(){ //cogemos los archivos en la carpeta correspondiente al mundo y asignamos cuantos hay
        try{
            String[] levelNames = log.getEngine().getaJsonlodr().getAssetsDirectory("levels/world1");
            numLevels = levelNames.length;
            int i = 1;
            for(String s : levelNames){
                System.out.print("Level: " + i + ": " + s + "\n");
                ++i;
            }
        }
        catch(IOException e){
            System.err.println(e.getMessage());
        }

    }

    public void render(AndrGraphics2D graph){
        for(Button b : worldLevels)
            b.render(graph);
    }

    public void handleInput(ArrayList<TouchEvent> event){
        int i = 1;
        for(Button b : worldLevels){
            if(b.handleInput(event)){
                try{
                    testLoadJSON(i);
                }
                catch (IOException e){
                    System.err.println(e.getMessage());
                }
            }
            ++i;
        }
    }

    void testLoadJSON(int n) throws IOException {
        String filename = "levels/world1/level_1_";

        if(n < 10)
            filename += "0" + String.valueOf(n) + ".json";
        else
            filename += String.valueOf(n) + ".json";

        InputStream is = log.getEngine().getaJsonlodr().getResource(filename);

        Level L = readJsonStream(is);

        log.SetScene(new PlayScene(log,L));
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
