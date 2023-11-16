package com.example.androidengine;

import android.content.res.AssetManager;
import android.graphics.Typeface;

import com.example.commonengine.CFont;

import java.io.FileInputStream;
import java.io.InputStream;

public class AndroidFont implements CFont {

    private Typeface font;
    private int size;

    public AndroidFont(String route, AssetManager assetManager, int size, boolean isBold,boolean isItalic) {
        this.size = size;

        font = Typeface.create(new Typeface.Builder(assetManager, route).setWeight(size).build(),
                isBold ? ( isItalic ? Typeface.BOLD_ITALIC : Typeface.BOLD) : ( isItalic ? Typeface.ITALIC : Typeface.NORMAL ));



    }

    public Typeface getFont() {
        return font;
    }

    public int getSize() {
        return size;
    }

    public boolean isBold() {
        return font.isBold();
    }
    public boolean isItalic(){
        return font.isItalic();

    }
}
