package com.example.androidengine;

import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.SoundPool;

import com.example.commonengine.CAudio;
import com.example.commonengine.CSound;

import java.io.IOException;

public class AndroidAudio implements CAudio {

    AssetManager assets;
    SoundPool soundsPool;

    public AndroidAudio(AssetManager assets){
        this.assets = assets;
        this.soundsPool = new SoundPool.Builder().setMaxStreams(10).build();
    }

    @Override
    public CSound newSound(String file) {
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

    @Override
    public void playSound(CSound sound) {
        AndroidSound s = (AndroidSound) sound;
        if(s.getStreamID() != -1)
            this.soundsPool.stop(s.getStreamID());
        int streamID = this.soundsPool.play(s.getId(), 1, 1,
                10, 0, 1);
        s.setStreamID(streamID);
    }

    @Override
    public void stopSound(CSound sound) {
        AndroidSound s = (AndroidSound) sound;
        if(s.getStreamID() != -1){
            this.soundsPool.stop(s.getId());
        }
    }
}
