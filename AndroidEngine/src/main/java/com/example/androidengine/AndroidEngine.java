package com.example.androidengine;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.commonengine.CAudio;
import com.example.commonengine.CInput;

import com.example.commonengine.EngineClass;
import com.example.commonengine.TouchEvent;

import java.util.ArrayList;

public class AndroidEngine extends EngineClass implements Runnable{

    private SurfaceView myView;

    private Thread renderThread;

    private boolean running;

    private CAudio audio;
    private AndrInput ainpt;

    AndrGraphics2D andgr;

    public AndroidEngine(SurfaceView sfView,Context cntxt){
        this.myView = sfView;

        AssetManager asman = cntxt.getAssets();

        this.andgr=new AndrGraphics2D(600,400,myView,cntxt);//,context);

        this.ainpt = new AndrInput(sfView);

        this.audio = new AndroidAudio(asman);

    }

    @Override
    public int getWidth(){
        return this.myView.getWidth();
    }
    @Override
    public int getHeight(){
        return this.myView.getHeight();
    }

    @Override
    public void run() {
        if (renderThread != Thread.currentThread()) {
            // Evita que cualquiera que no sea esta clase llame a este Runnable en un Thread
            // Programación defensiva
            throw new RuntimeException("run() should not be called directly");
        }

        // Si el Thread se pone en marcha
        // muy rápido, la vista podría todavía no estar inicializada.
        while(this.running && this.myView.getWidth() == 0);
        // Espera activa. Sería más elegante al menos dormir un poco.

        long lastFrameTime = System.nanoTime();

        long informePrevio = lastFrameTime; // Informes de FPS
        int frames = 0;

        // Bucle de juego principal.
        while(running) {
            long currentTime = System.nanoTime();
            long nanoElapsedTime = currentTime - lastFrameTime;
            lastFrameTime = currentTime;

            // Informe de FPS
            double elapsedTime = (double) nanoElapsedTime / 1.0E9;
            this.GetLogic().update(elapsedTime);
            if (currentTime - informePrevio > 1000000000l) {
                long fps = frames * 1000000000l / (currentTime - informePrevio);
                System.out.println("" + fps + " fps");
                frames = 0;
                informePrevio = currentTime;
            }
            ++frames;

            // Pintamos el frame

            //this.canvas = this.holder.lockCanvas();
            this.andgr.preparaFrame();
            this.log.render(andgr);
            this.andgr.terminaFrame();
            //this.holder.unlockCanvasAndPost(canvas);

            ArrayList<TouchEvent> eventlist = this.ainpt.getTouchEvent();

            for(TouchEvent event : eventlist) {
                event.x= andgr.refactorX(event.x);
                event.y= andgr.refactorY(event.y);

                //this.log.handleEvents(eventlist, audio);

            }


            this.log.handleEvents(eventlist, audio);

                /*
                // Posibilidad: cedemos algo de tiempo. Es una medida conflictiva...
                try { Thread.sleep(1); } catch(Exception e) {}
    			*/
        }
    }


    public void resume() {
        if (!this.running) {
            // Solo hacemos algo si no nos estábamos ejecutando ya
            // (programación defensiva)
            this.running = true;
            // Lanzamos la ejecución de nuestro método run() en un nuevo Thread.
            this.renderThread = new Thread(this);
            this.renderThread.start();
        }
    }

    public void pause() {
        if (this.running) {
            this.running = false;
            while (true) {
                try {
                    this.renderThread.join();
                    this.renderThread = null;
                    break;
                } catch (InterruptedException ie) {
                    // Esto no debería ocurrir nunca...
                }
            }
        }
    }


    @Override
    public CAudio getAudio() {
        return this.audio;
    }


}



