package com.example.practica2;

import com.example.androidengine.AndroidAudio;
import com.example.androidengine.AndrGraphics2D;
import com.example.androidengine.TouchEvent;

import java.util.ArrayList;
import java.util.Random;

public class Intento extends GameObject { //contiene el "rectangulo" de info. Incluye numero, colores insertados, pistas y
    // lineas separadoras

    private int numIntento = -1;
    int diametroIntentoCodigo = 30;
    int diametroPista = 8;
    ObjectMatrix botonesIntentoCodigo; //aqui guardamos lo que el jugador quiere que sea su intento, se puede quitar el color haciendo click
    ObjectMatrix pistas; //aqui se dibujara cada una de las pistas
    MastermindBoard mb; //referencia para llamar a algunos metodos
    int tamCodigo;
    Intento(MastermindBoard mb_, int numIntento_, int tamCodigo_, int posX_, int posY_, int width_, int height_) {
        super(posX_, posY_, width_, height_);

        mb = mb_;

        tamCodigo = tamCodigo_;



        //introducimos circulos que marcan nuestros intentos
        botonesIntentoCodigo = new ObjectMatrix(1, getPosX() + 40, getPosY() + 5, 225, 50);
        for(int i = 0; i < tamCodigo_; ++i){
            //las posiciones que se pasen por aqui dan igual, setObjectsPositionsInMatrix() las ajusta automaticamente
            botonesIntentoCodigo.addObjectToMatrix(new IntentoButton(mb,-1, -1, diametroIntentoCodigo, diametroIntentoCodigo));
        }
        botonesIntentoCodigo.setObjectsPositionsInMatrix();



        //introducimos pistas
        pistas = new ObjectMatrix(2, getPosX() + 45 + 225 + 10 , getPosY() + 5, 125, 35);
        for(int i = 0; i < tamCodigo_; ++i){
            pistas.addObjectToMatrix(new Pista(-1, -1, diametroPista, diametroPista));
        }
        pistas.setObjectsPositionsInMatrix();



        numIntento = numIntento_;
    }

    @Override
    public boolean handleInput(ArrayList<TouchEvent> event/*, CAudio audio*/) {
        botonesIntentoCodigo.handleInput(event/*,audio*/);
        pistas.handleInput(event/*,audio*/);
        return true;
    }

    @Override
    public void update(double t){
        botonesIntentoCodigo.update(t);
        pistas.update(t);
    }
    @Override
    public void render(AndrGraphics2D graph){

        graph.drawText(String.valueOf(numIntento),getPosX() + 15, getPosY() + 30);

        graph.drawLine(getPosX() + 45, getPosY() + 5, getPosX() + 45, getPosY() + 35);

        graph.drawLine(getPosX() + 280, getPosY() + 5, getPosX() + 280, getPosY() + 35);


        graph.drawRoundRectangle(getPosX(), getPosY(), getWidth(), getHeight(), 10, 10);
        botonesIntentoCodigo.render(graph);
        pistas.render(graph);
    }

    boolean pintaPrimerBotonIntentoCodigoLibre(int newColor_){
        int i = 0;
        boolean encontrado = false;
        while(i < botonesIntentoCodigo.numElems && !encontrado){
            IntentoButton ib = (IntentoButton) botonesIntentoCodigo.getElemAtIndex(i);
            if(ib.colorSet == false){
                ib.setColor(newColor_);
                encontrado = true;
            }
            ++i;
        }
        return encontrado;
    }

    int getColorBotonAt(int i_){ //devuelve el color del boton en la pos i_
        IntentoButton ib = (IntentoButton) botonesIntentoCodigo.getElemAtIndex(i_);
        return ib.getColor();
    }

    void rellenaPistas(int[] aciertos){ //en funcion de los valores de aciertos[](0, 1, 2), rellena pistas
        for(int i = 0; i < tamCodigo; ++i){
            Pista p = (Pista)pistas.getElemAtIndex(i);
            p.setState(aciertos[i]);
        }
    }

    //version corregida de rellenaPistas para lo que nos pedia el enunciado
    void rellenaPistas2(int aciertosAbsolutos, int aciertosParciales){
        for(int i = 0; i < tamCodigo; ++i){

            Pista p = (Pista)pistas.getElemAtIndex(i);

            if(aciertosAbsolutos > 0){
                p.setState(1);
                aciertosAbsolutos--;
            }
            else if(aciertosParciales > 0){
                p.setState(2);
                aciertosParciales--;
            }
            else{
                p.setState(0);
            }

        }
    }
}
