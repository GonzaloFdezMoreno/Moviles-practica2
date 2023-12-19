package com.example.practica2;



import com.example.androidengine.AndrGraphics2D;
import com.example.androidengine.AndroidAudio;
import com.example.androidengine.AndroidSound;
import com.example.androidengine.TouchEvent;

import java.util.ArrayList;

public class IntentoButton extends Button{ //contenidos en cada intento, con color marcado por los botones de selector(de MastermindBoard)
                                            //si tienen color, click encima para quitarselo
    boolean colorSet = false;
    int currentColor = 0xFFFF00FF;
    double smallInteriorCircleScale = 3; //por cuanto es mas pequeño el circulo interno cuando esta sin color un IntentoButton
                                        //se usara para dividir, si == 1 su tamaño sera igual que el circulo mas grande, a mayor
                                        // valor mas pequeño su tamaño
    int assignedNum = 0;
    MastermindBoard mb;
    IntentoButton(MastermindBoard mb_, int posX_, int posY_, int width_, int height_, AndroidAudio audio, AndroidSound sound) {
        super(posX_, posY_, width_, height_, "", 0XFFFFFFFF, audio, sound);
        mb = mb_;
    }

    @Override
    public boolean handleInput(ArrayList<TouchEvent> event /*, CAudio audio*/) {
        return super.handleInput(event/*,audio*/);
    }

    @Override
    public void render(AndrGraphics2D graph) {
        if(!colorSet){
            graph.setColor(0xFFc7cbd1);
            graph.fillCircle(getPosX(),getPosY(),getWidth());

            //circulo interno mas pequeño
            graph.setColor(0xFF92969C);
            double smallWidth = (getWidth()/smallInteriorCircleScale);
            double smallHeight = (getHeight()/smallInteriorCircleScale);

            graph.fillCircle((int)(getPosX() + getWidth()/2 - smallWidth/2),
                    (int)(getPosY() + getHeight()/2 - smallHeight/2),
                    (int)(smallWidth));
        }
        else{
            if(mb.log.currSkin < 4){
                graph.drawImage(graph.createImage("sprites/"+ mb.skinFolderNames[mb.log.currSkin] + "/" +mb.skinsColores[assignedNum-1]), getPosX(), getPosY(), getWidth(), getHeight());
            }
            else {
                graph.setColor(currentColor);
                graph.fillCircle(getPosX(), getPosY(), getWidth());
            }
            if(mb.isDaltonismo()){
                graph.setColor(0xFF000000);
                graph.drawText(String.valueOf(assignedNum), (int) getPosX() + getWidth()/2 -3, (int) getPosY() + getHeight()/2 + 3);
            }
        }
        graph.setColor(0xFF000000);

    }

    @Override
    void onTouchDown() {
        if(colorSet)
            colorSet = false;
        mb.cantColoresRellenados--;
        if(mb.cantColoresRellenados < 0) //no me fio, por si acaso pongo esto, asi evitamos despistes
            mb.cantColoresRellenados = 0;
    }

    void setColor(int newColor_){
        currentColor = newColor_;
        colorSet = true;
        for (int i = 0; i < mb.hexColores.length; i++) {
            if (currentColor == mb.hexColores[i]){
                assignedNum = i + 1;
            }
        }
    }

    int getColor(){
        return currentColor;
    }
}
