package com.example.commonengine;



import java.awt.List;
import java.util.Vector;

public interface Cengine {

   CAudio getAudio();
   CLogic GetLogic();

   void SetLogic(CLogic logic);
}