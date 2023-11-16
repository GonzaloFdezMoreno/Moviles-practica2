package com.example.logic;

import com.example.commonengine.CGraphics2D;

public class SecretCode extends GameObject{

    int[] code;
    boolean daltonico;
    int hexColores[] = {0xffff0000,0Xffff9033,0xfffffc33,0xff00ff00,0xff33fffb,0xff334dff,0xfff433ff,0xffff33a5,0xff991111};
    public SecretCode(int[] code_, int posX_, int posY_, int width_, int height_, boolean dalton) {
        super(posX_, posY_, width_, height_);
        code = code_;
        daltonico = dalton;
    }

    @Override
    public void render(CGraphics2D graph) {
        for(int i = 0; i < code.length; ++i){
            graph.setColor(code[i]);
            graph.fillCircle(getPosX() + 35 * i, getPosY(), 25,25);
            graph.setColor(0xFF000000);
            if(daltonico){
                for(int j = 0; j < hexColores.length; j++){
                    if(code[i] == hexColores[j]){
                        graph.drawText(String.valueOf(j + 1), (int) getPosX() + 37 * i, (int) getPosY() + 23);
                    }
                }
            }
        }
    }
}
