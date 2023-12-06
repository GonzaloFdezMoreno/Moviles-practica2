package com.example.practica2;

import com.example.androidengine.AndrGraphics2D;

import java.io.File;
import java.util.ArrayList;
import java.lang.Object;
public class WorldLevelSelector {
    int posX, posY;
    int numLevelsBeaten = 0;
    int numLevels = 14;
    int widthMargin = 90, heightMargin = 90; //margenes entre botones de niveles
    ArrayList<Button> worldLevels;

    WorldLevelSelector(int posX_, int posY_, Logic log){
        posX = posX_; posY = posY_;

        worldLevels = new ArrayList<>();
        try{
            File f = new File(".");
            numLevels = f.list().length;
            for(String s : f.list()){
                System.out.print(s + '\n');
            }
        }
        catch (NullPointerException except){
            System.out.print("no mr buckaroo");
        }

        for (int i = 0, j = 0, k = 0; i < numLevels; ++i, ++k){
            if(i%3==0){
                k = 0;
                ++j;
            }
            worldLevels.add(new Button(posX + k * widthMargin, posY + j * heightMargin, 80, 80,
                    String.valueOf(i + 1), 0XDDD3D3D3,log.currEngine.getAudio()));
        }

    }

    public void render(AndrGraphics2D graph){
        for(Button b : worldLevels)
            b.render(graph);
    }

}
