package com.example.desktopengine;

import com.example.commonengine.CSound;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class DesktopSound implements CSound {

    private Clip clip;

    public DesktopSound(String file){
        try {
            File audioFile = new File(file);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            this.clip = AudioSystem.getClip();
            this.clip.open(audioStream);

            this.clip.setFramePosition(0);
        }   catch (Exception e){
            System.err.println("Couldn't load audio file");
            e.printStackTrace();
        }
    }

    public Clip getClip(){
        return this.clip;
    }
}
