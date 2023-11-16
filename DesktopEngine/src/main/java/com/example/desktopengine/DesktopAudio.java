package com.example.desktopengine;

import com.example.commonengine.CAudio;
import com.example.commonengine.CSound;

public class DesktopAudio implements CAudio {

    private final String audioRoute = "./Assets/";

    @Override
    public CSound newSound(String file) {
        DesktopSound sound = new DesktopSound(audioRoute+file);
        return sound;
    }

    @Override
    public void playSound(CSound sound) {
        DesktopSound s = (DesktopSound) sound;
        s.getClip().start();
        s.getClip().setFramePosition(0);
    }

    @Override
    public void stopSound(CSound sound) {
        DesktopSound s = (DesktopSound) sound;
        s.getClip().stop();
        s.getClip().setFramePosition(0);
    }
}
