package com.example.practica2;

import android.util.JsonReader;

import com.example.androidengine.AndrGraphics2D;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.lang.Object;
import java.util.List;

public class WorldLevelSelector {
    int posX, posY;
    int numLevelsBeaten = 0;
    int numLevels = 14;
    int widthMargin = 90, heightMargin = 90; //margenes entre botones de niveles
    ArrayList<Button> worldLevels;

    Logic log;
    WorldLevelSelector(int posX_, int posY_, Logic log_){
        posX = posX_; posY = posY_;
        log = log_;

        worldLevels = new ArrayList<>();

        for (int i = 0, j = 0, k = 0; i < numLevels; ++i, ++k){
            if(i%3==0){
                k = 0;
                ++j;
            }
            worldLevels.add(new Button(posX + k * widthMargin, posY + j * heightMargin, 80, 80,
                    String.valueOf(i + 1), 0XDDD3D3D3,log.currEngine.getAudio()));
        }

        try{
            testLoadJSON();
        }
        catch (IOException e){
            System.err.println(e.getMessage() + "\nNO SE PUDO ABRIR EL ARCHIVO");
        }
    }

    public void render(AndrGraphics2D graph){
        for(Button b : worldLevels)
            b.render(graph);
    }

    void testLoadJSON() throws IOException {
        String filename = "level_test.json";
        //log.getEngine()

        InputStream is = log.getEngine().getaJsonlodr().getResource(filename);
        readJsonStream(is);
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
                System.out.print("\ncodeSize: " + codeSize + "\n");
            } else if (name.equals("codeOpt")) {
                codeOpt = reader.nextInt();
                System.out.print("\ncodeOpt: " + codeOpt + "\n");

            } else if (name.equals("repeat")) {
                repeat = reader.nextBoolean();
                System.out.print("\nrepeat: " + repeat + "\n");

            } else if (name.equals("attempts")) {
                attempts = reader.nextInt();;
                System.out.print("\nattempts: " + attempts + "\n");

            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return new Level(codeSize, codeOpt, repeat, attempts);
    }

}