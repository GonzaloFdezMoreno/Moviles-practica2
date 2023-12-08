package com.example.androidengine;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.JsonReader;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.Object;
import java.util.ArrayList;
import java.util.List;

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
