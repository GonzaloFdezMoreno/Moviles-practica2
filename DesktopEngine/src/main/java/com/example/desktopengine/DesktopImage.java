package com.example.desktopengine;

import com.example.commonengine.Cimage;

import java.awt.Image;

public class DesktopImage implements Cimage {

    Image img;
    public DesktopImage(Image im){
        this.img=im;
    }
    @Override
    public int getHeight() {
        return img.getHeight(null);
    }

    @Override
    public int getWidth() {
        return img.getWidth(null);
    }

    public Image getImage(){return this.img;}
}
