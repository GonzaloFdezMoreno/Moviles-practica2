package com.example.practica2;

import com.example.androidengine.AndrGraphics2D;
import com.example.androidengine.AndroidImage;
import com.example.androidengine.TouchEvent;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ExploreWorldsScene extends Scene{
    Button nextWorld;
    Button prevWorld;
    Button goBack;
    int currentWorld = 0;
    WorldLevelSelector levelSelector;

    AndroidImage[] worldBackgroundImages;
    String[] worldFolderNames;
    String worldsLocationFolder = "levels";
    protected ExploreWorldsScene(Logic logic) {
        super(logic);
        try {
            getWorldNamesAndBackgrounds();
        }
        catch (IOException e){
            System.err.println(e.getMessage());
        }

        int windowCenterX=logic.getEngine().GetGraphics().getWidth()/2,
                windowCenterY=logic.getEngine().GetGraphics().getHeight()/2;

        levelSelector = new WorldLevelSelector(windowCenterX - 120,0, worldFolderNames, logic);


        prevWorld = new Button(windowCenterX - 90,windowCenterY - 290,30,40, "PrevArrows.png", log);
        nextWorld = new Button(windowCenterX + 90,windowCenterY - 290,30,40, "NextArrows.png", log);
        goBack = new Button(windowCenterX - 180,windowCenterY - 280,20,20, "goback.png", log);
    }

    @Override
    public void render(AndrGraphics2D graph) {

        super.render(graph);

        if(!(worldBackgroundImages[currentWorld] == null))
            graph.drawImage(worldBackgroundImages[currentWorld], graph.getWidth()/2,graph.getHeight()/2,800,1200);

        graph.setColor(0xFFFFFFFF);
        graph.fillRectangle(0,0,400, 50);
        graph.setColor(0xFF000000);

        prevWorld.render(graph);
        nextWorld.render(graph);
        goBack.render(graph);

        levelSelector.render(graph);
    }

    @Override
    public void handleInput(ArrayList<TouchEvent> event) {
        super.handleInput(event);

        if(prevWorld.handleInput(event)){
            if(currentWorld > 0){
                currentWorld--;
                levelSelector.setCurrWorld(currentWorld);
            }
        }

        if(nextWorld.handleInput(event)){
            if(currentWorld < worldFolderNames.length - 1){
                currentWorld++;
                levelSelector.setCurrWorld(currentWorld);
            }
        }

        if(goBack.handleInput(event)){
            log.SetScene(new MainMenu(log));
        }
        levelSelector.handleInput(event);
    }

    //NOTA: Solo aceptamos que las imagenes de fondo sean jpg o jpeg, porque no tengo tiempo de investigar como cambiar la terminacion para ver si existe el archivo
    void getWorldNamesAndBackgrounds() throws IOException {

        //conseguimos los nombres de todas las carpetas
        worldFolderNames = log.getEngine().getaJsonlodr().getAssetsDirectory(worldsLocationFolder);

        worldBackgroundImages = new AndroidImage[worldFolderNames.length];
        //conseguimos los backgrounds, buscaremos un archivo que se llame word_bg, y si no existe no cargaremos nada
        int i = 0;
        for(String s : worldFolderNames) {
            String folderContentsName = worldsLocationFolder + "/" + s;
            //si el archivo de fondo de mundo existe lo creamos como imagen
            List<String> aux = Arrays.asList(log.getEngine().getaJsonlodr().getAssetsDirectory(folderContentsName));
            if (aux.contains("world_bg.jpg")){
                String fullDirectory = folderContentsName + "/world_bg.jpg";
                worldBackgroundImages[i] = log.getEngine().GetGraphics().createImage(fullDirectory);
            }
            else if(aux.contains("world_bg.jpeg")){
                String fullDirectory = folderContentsName + "/world_bg.jpeg";
                worldBackgroundImages[i] = log.getEngine().GetGraphics().createImage(fullDirectory);
            }
            else{
                worldBackgroundImages[i] = log.getEngine().GetGraphics().createImage("default_bg.jpg");
            }
            ++i;
        }
    }


}
