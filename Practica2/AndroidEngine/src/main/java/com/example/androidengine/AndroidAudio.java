package com.example.androidengine;

import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.SoundPool;



import java.io.IOException;

public class AndroidAudio {

    AssetManager assets;
    SoundPool soundsPool;

    public AndroidAudio(AssetManager assets){
        this.assets = assets;
        this.soundsPool = new SoundPool.Builder().setMaxStreams(10).build();
    }


    public AndroidSound newSound(String file) {
        int soundId = -1;
        try{
            AssetFileDescriptor assetDescriptor = assets.openFd(file);
            soundId = soundsPool.load(assetDescriptor, 10);
        } catch (IOException e){
            throw new RuntimeException("Couldn't load sound.");
        }

        if(soundId != -1){
            return new AndroidSound(soundId);
        }

        return null;
    }


    public void playSound(AndroidSound sound) {
        AndroidSound s = sound;
        if(s.getStreamID() != -1)
            this.soundsPool.stop(s.getStreamID());
        int streamID = this.soundsPool.play(s.getId(), 1, 1,
                10, 0, 1);
        s.setStreamID(streamID);
    }


    public void stopSound(AndroidSound sound) {
        AndroidSound s = sound;
        if(s.getStreamID() != -1){
            this.soundsPool.stop(s.getId());
        }
    }
}
