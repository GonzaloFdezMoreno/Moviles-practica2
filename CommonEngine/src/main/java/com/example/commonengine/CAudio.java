package com.example.commonengine;

public interface CAudio {
    CSound newSound(String file);

    void playSound(CSound sound);

    void stopSound(CSound sound);
}
