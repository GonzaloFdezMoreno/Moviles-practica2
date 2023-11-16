package com.example.desktopgame;

import com.example.desktopengine.DesktopEngine;
import com.example.desktopengine.DesktopGraphics2D;
import com.example.logic.Logic;

import java.awt.Color;

import javax.swing.JFrame;

public class DesktopGame {



    public static void main(String[] args) throws InterruptedException {
        DesktopEngine dskE;
        JFrame renderView = new JFrame("Mastermind");

        renderView.setSize(400, 600);
        renderView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        renderView.setIgnoreRepaint(true);

        renderView.setVisible(true);
        int intentos = 100;
        while(intentos-- > 0) {
            try {
                renderView.createBufferStrategy(2);
                break;
            }
            catch(Exception e) {
            }
        } // while pidiendo la creación de la buffeStrategy
        if (intentos == 0) {
            System.err.println("No pude crear la BufferStrategy");
            return;
        }
        else {
            // En "modo debug" podríamos querer escribir esto.
            //System.out.println("BufferStrategy tras " + (100 - intentos) + " intentos.");
        }

        dskE = new DesktopEngine(renderView);
        Logic logic = new Logic(dskE);
        dskE.SetLogic(logic);
        //dske.resume();
        //con resume no funciona
        dskE.run();


    }

}