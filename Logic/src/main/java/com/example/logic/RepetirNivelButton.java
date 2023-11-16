package com.example.logic;

import com.example.commonengine.CGraphics2D;

import jdk.internal.net.http.common.Log;

public class RepetirNivelButton extends Button{

    Logic log;
    int nivel;
    String text;
    RepetirNivelButton(Logic log_, String text_, int nivel_, int posX_, int posY_, int width_, int height_) {
        super(posX_, posY_, width_, height_);
        log = log_;
        nivel = nivel_;
        text = text_;
    }

    @Override
    void onTouchDown() {
        log.SetScene(new PlayScene(log, nivel));
    }

    @Override
    public void render(CGraphics2D graph) {
        graph.setColor(0xFF00FFFF);
        graph.fillRoundRectangle((int)getPosX(),(int)getPosY(),(int)getWidth(),(int)getHeight(),20,20);
        graph.setColor(0xFF000000);
        graph.drawText(text,(int)getPosX()+80,(int)getPosY()+20);
    }
}
