package com.example.desktopengine;

import com.example.commonengine.CFont;

import java.awt.Font;
import java.io.FileInputStream;
import java.io.InputStream;

public class DesktopFont implements CFont {

    private final String fontRoute = "./Assets/";
    private Font font;


    DesktopFont(String fontname,int size,boolean bold,boolean italics){
        InputStream is  = null;
        Font awFont = null;
        try {
            is = new FileInputStream(fontRoute+fontname);
            awFont = Font.createFont(Font.TRUETYPE_FONT, is);
            awFont = awFont.deriveFont(bold ? (italics ? Font.BOLD | Font.ITALIC : Font.BOLD) :
                    (italics ? Font.ITALIC : Font.TRUETYPE_FONT), size);
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }
        font=awFont;


    }

    public Font GetFont(){return font;}
}
