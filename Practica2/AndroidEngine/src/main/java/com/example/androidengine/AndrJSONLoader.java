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

}
