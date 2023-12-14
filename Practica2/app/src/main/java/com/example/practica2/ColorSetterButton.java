package com.example.practica2;


import com.example.androidengine.AndrGraphics2D;
import com.example.androidengine.AndroidAudio;
import com.example.androidengine.AndroidSound;

public class ColorSetterButton extends Button{
    int currentColor = 0xFFFF00FF; //color por defecto, si falla algo se vera asi
    MastermindBoard boardRef;
    int assignedNum = 0;
    ColorSetterButton(MastermindBoard boardRef_, int color_, int posX_, int posY_, int width_, int height_, AndroidAudio audio, AndroidSound sound) { //boton para insertar colores en el Intento actual
        super(posX_, posY_, width_, height_, "", 0xFFFFFFFF, audio, sound);
        currentColor = color_;
        boardRef = boardRef_;

        for (int i = 0; i < boardRef.hexColores.length; i++) {
            if (currentColor == boardRef.hexColores[i]){
                assignedNum = i + 1;
            }
        }
    }

    @Override
    public void render(AndrGraphics2D graph) {
        graph.setColor(currentColor);
        graph.fillCircle(getPosX(),getPosY(),getWidth());
        graph.setColor(0xFF000000);
        if(boardRef.isDaltonismo()){
            graph.drawText(String.valueOf(assignedNum),  getPosX() + getWidth()/2 -3,  getPosY() + getHeight()/2 + 3);
        }
    }

    @Override
    void onTouchDown() {
        boardRef.rellenaSiguienteHuecoIntentoActual(currentColor);
    }
}
