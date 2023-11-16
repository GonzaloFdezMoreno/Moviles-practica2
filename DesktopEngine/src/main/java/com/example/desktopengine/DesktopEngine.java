package com.example.desktopengine;


import com.example.commonengine.CAudio;
import com.example.commonengine.CGraphics2D;
import com.example.commonengine.CInput;
import com.example.commonengine.CLogic;

import com.example.commonengine.Cengine;
import com.example.commonengine.EngineClass;
import com.example.commonengine.TouchEvent;

import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JFrame;

public class DesktopEngine extends EngineClass implements Runnable{

    private DesktopGraphics2D dgrph;
    JFrame jfr;
    private DesktopInput dinp;
    private CAudio audio;

    private boolean running;

    public DesktopEngine(JFrame view){
        jfr=view;

        dgrph=new DesktopGraphics2D(400,600,view);
;
        running=true;

        dinp = new DesktopInput(view);
        audio = new DesktopAudio();
    }
    @Override
    public int getWidth() {
        return jfr.getWidth();
    }

    @Override
    public int getHeight() {
        return jfr.getHeight();
    }

    @Override
    public void run() {
        /*if (renderThread != Thread.currentThread()) {
            // Evita que cualquiera que no sea esta clase llame a este Runnable en un Thread
            // Programación defensiva
            throw new RuntimeException("run() should not be called directly");
        }*/
        // Si el Thread se pone en marcha
        // muy rápido, la vista podría todavía no estar inicializada.
        while(this.running && this.jfr.getWidth() == 0);
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
            do {
                do {
                    //this.grph = (Graphics2D)this.bufferStrategy.getDrawGraphics();
                    //try {


                    dgrph.preparaFrame();

                    this.log.render(dgrph);
                    dgrph.terminaFrame();


                    //}
                    /*finally {
                        //Elimina el contexto gráfico y libera recursos del sistema realacionado
                    }*/
                } while(dgrph.conRest());
                dgrph.show();
            } while(dgrph.conLost());

            ArrayList<TouchEvent> eventlist=this.dinp.getTouchEvent();

            for(TouchEvent event : eventlist) {

                //refactor
                event.x= dgrph.refactorX(event.x);
                event.y= dgrph.refactorY(event.y);

            }
            this.log.handleEvents(eventlist,audio);


            /*
            // Posibilidad: cedemos algo de tiempo. Es una medida conflictiva...
            try { Thread.sleep(1); } catch(Exception e) {}
            */
        }
    }

    /*public void resume() {
        if (!this.running) {
            // Solo hacemos algo si no nos estábamos ejecutando ya
            // (programación defensiva)
            this.running = true;
            // Lanzamos la ejecución de nuestro método run() en un nuevo Thread.
            this.renderThread = new Thread(this);
            this.renderThread.start();
        }
    }

   */

    @Override
    public CAudio getAudio() {
        return this.audio;
    }
}