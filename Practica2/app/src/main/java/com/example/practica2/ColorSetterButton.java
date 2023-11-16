package com.example.logic;

import com.example.commonengine.CGraphics2D;

public class ColorSetterButton extends Button{
    int currentColor = 0xFFFF00FF; //color por defecto, si falla algo se vera asi
    MastermindBoard boardRef;
    int assignedNum = 0;
    ColorSetterButton(MastermindBoard boardRef_, int color_, int posX_, int posY_, int width_, int height_) { //boton para insertar colores en el Intento actual
        super(posX_, posY_, width_, height_);
        currentColor = color_;
        boardRef = boardRef_;

        for (int i = 0; i < boardRef.hexColores.length; i++) {
            if (currentColor == boardRef.hexColores[i]){
                assignedNum = i + 1;
            }
        }
    }

    @Override
    public void render(CGraphics2D graph) {
        graph.setColor(currentColor);
        graph.fillCircle((int)getPosX(),(int)getPosY(),(int)getWidth(),(int)getHeight());
        graph.setColor(0xFF000000);
        if(boardRef.isDaltonismo()){
            graph.drawText(String.valueOf(assignedNum), (int) getPosX() + getWidth()/2 -3, (int) getPosY() + getHeight()/2 + 3);
        }
    }

    @Override
    void onTouchDown() {
        boardRef.rellenaSiguienteHuecoIntentoActual(currentColor);
    }
}
