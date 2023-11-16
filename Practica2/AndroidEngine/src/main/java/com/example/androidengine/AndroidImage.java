package com.example.androidengine;

import android.graphics.Bitmap;



public class AndroidImage {

    Bitmap img;

    AndroidImage(Bitmap image){
        img=image;
    }


    public int getHeight() {
        return img.getHeight();
    }


    public int getWidth() {
        return img.getWidth();
    }


    public Bitmap getImage() {
       return img;
    }
}
