package com.example.androidengine;


public class AndroidSound {

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
