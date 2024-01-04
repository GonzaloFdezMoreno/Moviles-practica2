package com.example.androidengine;

import android.content.Context;
import android.content.res.AssetManager;

import java.io.IOException;
import java.io.InputStream;


public class AndrJSONLoader {
    private AssetManager aMngr;
    Context ctxt;

    AndrJSONLoader(Context ctxt_){
        ctxt = ctxt_;
        aMngr = ctxt.getAssets();
    }

    public InputStream getResource(String filename) throws IOException {
        return aMngr.open(filename);
    }

    public String[] getAssetsDirectory(String dirname) throws IOException { //para coger un directorio de la carpeta Assets
        return aMngr.list(dirname);
    }

    //para saber si existe un archivo
    public boolean containsFile(String filename) {
        try{
            aMngr.open(filename);
        }
        catch (IOException e){ //truco algo feo para si sabemos que no existe
            return false;
        }
        return true;
    }
    public Context getCtxt() {
        return ctxt;
    }
}
