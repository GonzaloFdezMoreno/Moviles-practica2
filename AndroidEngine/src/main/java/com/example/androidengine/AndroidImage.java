package com.example.androidengine;

import android.graphics.Bitmap;

import com.example.commonengine.Cimage;

public class AndroidImage implements Cimage {

    Bitmap img;

    AndroidImage(Bitmap image){
        img=image;
    }

    @Override
    public int getHeight() {
        return img.getHeight();
    }

    @Override
    public int getWidth() {
        return img.getWidth();
    }


    public Bitmap getImage() {
       return img;
    }
}
