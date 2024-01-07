package com.example.practica2;



import android.content.Context;
import android.content.SharedPreferences;
import android.util.JsonReader;
import android.util.JsonWriter;

import com.example.androidengine.AndrGraphics2D;
import com.example.androidengine.AndroidEngine;
import com.example.androidengine.CLogic;
import com.example.androidengine.EngineClass;
import com.example.androidengine.TouchEvent;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class Logic implements CLogic {
    Scene currScene;
    EngineClass currEngine;

    String SKIN = "skin";
    String BACKGROUND = "background";
    String SKIN_SAVE_FILE_PATH = "moneyskins.json";
    String LEVELS_SAVE_FILE_PATH = "levelsComplete.json";

    int nbSkins = 5;
    int currBG; //este int es el fondo actual que tenemos en la aplicacion
    int currSkin; //este int es la skin actual que tenemos en la aplicacion

    int money;

    Boolean[] unlockedSkins;
    Boolean[] unlockedBackgrounds;

    //AndroidEngine currEngine;

    public Logic(EngineClass engine) { //AndroidEngine engine){
        //empezamos siempre desde el Menú principal

        this.currBG=4; //por defecto empezamos con el fondo blanco
        this.currSkin=4; //por defecto empezamos con la skin basica
        this.money = 100;

        this.currEngine=engine;

        unlockedSkins = new Boolean[this.nbSkins];
        unlockedBackgrounds = new Boolean[this.nbSkins];
        Arrays.fill(unlockedBackgrounds, false);
        Arrays.fill(unlockedSkins, false);
        unlockedBackgrounds[4] = true;
        unlockedSkins[4] = true;

        try {
            loadGameSkins();
        } catch (Exception err) {
            err.printStackTrace();
        }

        this.currScene=new MainMenu(this);
    }

    public Scene getScene() {
        return currScene;
    }

    public EngineClass getEngine() {
        return currEngine;
    }

    //public AndroidEngine getEngine() {
        //return currEngine;
    //}

    //Unicamente debemos renderizar, actualizar y manejar los inputs
    // de la escena que esté mostrandose.
    @Override
    public void update(double t) {
        currScene.update(t);

    }

    @Override
    public void render(AndrGraphics2D graph) {
        currScene.render(graph);


    }

    @Override
    public void handleEvents(ArrayList<TouchEvent> input) {
       currScene.handleInput(input);
    }


    public void SetScene(Scene scene){
        currScene=scene;
    }


    void saveGameState(SharedPreferences.Editor preferencesEditor){
        System.out.print("Saving stuff...\n");
        currScene.onSave(preferencesEditor);
    };

    void loadGameState(SharedPreferences preferences) {
        currScene.onLoad(preferences);
    }
    void loadGameSkins() throws IOException {
        try
        {

            Context context = currEngine.getaJsonlodr().getCtxt();
            FileInputStream fileInputStream = context.openFileInput(SKIN_SAVE_FILE_PATH);

            JsonReader reader = new JsonReader(new InputStreamReader(fileInputStream, "UTF-8"));
            reader.beginObject();
            while (reader.hasNext()) {
                String name = reader.nextName();
                if (name.equals("money")) {
                    this.money = reader.nextInt();
                } else if (name.equals("selected_skin")) {
                    this.currSkin = reader.nextInt();
                } else if (name.equals("selected_background")) {
                    this.currBG = reader.nextInt();
                } else {
                    String[] data = name.split("_");
                    if (data.length == 2) {
                        if (Objects.equals(data[0], SKIN)) {
                            unlockedSkins[Integer.parseInt(data[1])] = reader.nextBoolean();
                        } else if (Objects.equals(data[0], BACKGROUND)) {
                            unlockedBackgrounds[Integer.parseInt(data[1])] = reader.nextBoolean();
                        }
                    }
                }
            }
            reader.endObject();
        } catch (Exception err){
            err.printStackTrace();
        }
    }

    void saveGameSkins() throws IOException {

        try
        {
            //Saving of object in a file
            Context context = currEngine.getaJsonlodr().getCtxt();

            FileOutputStream file = context.openFileOutput(SKIN_SAVE_FILE_PATH, Context.MODE_PRIVATE);
            JsonWriter writer = new JsonWriter(new OutputStreamWriter(file, "UTF-8"));
            writer.setIndent("  ");

            writer.beginObject();
            // Store the current money
            writer.name("money").value(this.money);
            writer.name("selected_skin").value(currSkin);
            writer.name("selected_background").value(currBG);

            // Store the skins and background skins unlocked status
            for (int idx = 0; idx < 4; idx++){
                if (unlockedSkins[idx]) {
                    writer.name(SKIN + "_" + idx).value(true);
                }
                if (unlockedBackgrounds[idx]) {
                    writer.name(BACKGROUND + "_" + idx).value(true);
                }
            }
            writer.endObject();
            writer.close();
            file.close() ;
        } catch (Exception err){
            err.printStackTrace();
        }
    }

    //cargamos el fichero de niveles completados, si no existe, guardamos uno nuevo con todo_ a 0
    int[] loadLevelsCompleted(int numWorlds){
        int[] numLvlsCompletedWorlds = new int[0];
        try
        {
            Context context = currEngine.getaJsonlodr().getCtxt();
            FileInputStream fileInputStream = null;
            try{
                fileInputStream = context.openFileInput(LEVELS_SAVE_FILE_PATH);
            }
            catch (FileNotFoundException f){
                f.printStackTrace();
                int[] worlds = new int[numWorlds];
                saveLevelsCompleted(worlds);
                fileInputStream = context.openFileInput(LEVELS_SAVE_FILE_PATH);
            }

            JsonReader reader = new JsonReader(new InputStreamReader(fileInputStream, "UTF-8"));
            reader.beginObject();

            String s = reader.nextName();

            int num_worlds = -1;
            if(s.equals("num_worlds")){
                 num_worlds = reader.nextInt();
            }
            numLvlsCompletedWorlds = new int[num_worlds];

            while (reader.hasNext()) {
                String name = reader.nextName();

                String[] data = name.split("_");

                numLvlsCompletedWorlds[Integer.valueOf(data[1])] = reader.nextInt();
                /*if (Objects.equals(data[0], SKIN)) {
                    unlockedSkins[Integer.parseInt(data[1])] = reader.nextBoolean();
                } else if (Objects.equals(data[0], BACKGROUND)) {
                    unlockedBackgrounds[Integer.parseInt(data[1])] = reader.nextBoolean();
                }*/
            }
            reader.endObject();
        } catch (Exception err){
            err.printStackTrace();
        }

        return numLvlsCompletedWorlds;
    }
    //guardamos cuantos niveles llevamos completados, generalmente lo combinariamos con el archivo de skins pero
    //por el momento ira separado para hacer pruebas y porque se llama "moneyskins" el archivo, y esto no es ni
    //money ni skins
    void saveLevelsCompleted(int[] numLvlsCompletedWorlds){
        try
        {
            //Saving of object in a file
            Context context = currEngine.getaJsonlodr().getCtxt();

            FileOutputStream file = context.openFileOutput(LEVELS_SAVE_FILE_PATH, Context.MODE_PRIVATE);
            JsonWriter writer = new JsonWriter(new OutputStreamWriter(file, "UTF-8"));
            writer.setIndent("  ");

            writer.beginObject();

            //guardamos la cantidad de mundos
            writer.name("num_worlds").value(numLvlsCompletedWorlds.length);


            //guardamos en formato world0 -> 3, world1 -> 1...
            int ii = 0;
            for(int i : numLvlsCompletedWorlds){
                writer.name("world_"+ii).value(i);

                ++ii;
            }

            writer.endObject();
            writer.close();
            file.close() ;
        } catch (Exception err){
            err.printStackTrace();
        }
    }

}