package com.example.androidengine;

import com.example.commonengine.CSound;

public class AndroidSound implements CSound {

    int streamID;
    int id;

    public AndroidSound(int id){
        this.id = id;
    }

    public int getId(){
        return this.id;
    }

    public void setStreamID(int streamID) {
        this.streamID = streamID;
    }

    public int getStreamID() {
        return this.streamID;
    }
}
