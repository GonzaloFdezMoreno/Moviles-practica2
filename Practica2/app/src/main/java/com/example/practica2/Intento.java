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
    ArrayList<IntentoButton> botonesIntentoCodigoo;
    //ObjectMatrix botonesIntentoCodigo; //aqui guardamos lo que el jugador quiere que sea su intento, se puede quitar el color haciendo click
    ObjectMatrix pistas; //aqui se dibujara cada una de las pistas
    MastermindBoard mb; //referencia para llamar a algunos metodos
    int tamCodigo;
    Intento(MastermindBoard mb_, int numIntento_, int tamCodigo_, int posX_, int posY_, int width_, int height_) {
        super(posX_, posY_, width_, height_);

        mb = mb_;

        tamCodigo = tamCodigo_;

        //introducimos circulos que marcan nuestros intentos
        botonesIntentoCodigoo = new ArrayList<IntentoButton>(tamCodigo_);
        colocaBotonesIntentos(tamCodigo_);
        /*botonesIntentoCodigo = new ObjectMatrix(1, 40, getPosY() + 5, 225, 50);
        for(int i = 0; i < tamCodigo_; ++i){
            //las posiciones que se pasen por aqui dan igual, setObjectsPositionsInMatrix() las ajusta automaticamente
            IntentoButton ib = new IntentoButton(mb,-1, -1, diametroIntentoCodigo, diametroIntentoCodigo, mb.log.getEngine().getAudio(), mb.log.currEngine.getSound());
            botonesIntentoCodigo.addObjectToMatrix(ib);
            addChild(ib);
        }
        botonesIntentoCodigo.setObjectsPositionsInMatrix();*/



        //introducimos pistas
        pistas = new ObjectMatrix(2, getPosX() + 45 + 225 + 10 , getPosY() + 5, 125, 35);
        for(int i = 0; i < tamCodigo_; ++i){
            Pista p = new Pista(-1, -1, diametroPista, diametroPista);
            pistas.addObjectToMatrix(p);
            addChild(p);
        }
        pistas.setObjectsPositionsInMatrix();



        numIntento = numIntento_;
    }

    @Override
    public boolean handleInput(ArrayList<TouchEvent> event) {
        for(IntentoButton ib : botonesIntentoCodigoo){
            ib.handleInput(event);
        }
        pistas.handleInput(event);
        return true;
    }

    @Override
    public void update(double t){
        for(IntentoButton ib : botonesIntentoCodigoo){
            ib.update(t);
        }
        pistas.update(t);
    }
    @Override
    public void render(AndrGraphics2D graph){

        graph.setColor(0XFF000000);
        graph.drawRoundRectangle(getPosX(), getPosY(), getWidth(), getHeight(), 10, 10);
        graph.setColor(0XFF000000);
        graph.drawText(String.valueOf(numIntento),getPosX(), getPosY() + 30);

        graph.drawLine(getPosX() + 45, getPosY() + 5, getPosX() + 45, getPosY() + 35);

        graph.drawLine(getPosX() + 280, getPosY() + 5, getPosX() + 280, getPosY() + 35);

        for(IntentoButton ib : botonesIntentoCodigoo){
            ib.render(graph);
        }

        pistas.render(graph);
    }

    boolean pintaPrimerBotonIntentoCodigoLibre(int newColor_){
        int i = 0;
        boolean encontrado = false;
        while(i < botonesIntentoCodigoo.size() && !encontrado){
            IntentoButton ib = botonesIntentoCodigoo.get(i);
            if(ib.colorSet == false){
                ib.setColor(newColor_);
                encontrado = true;
            }
            ++i;
        }
        return encontrado;
    }

    int getColorBotonAt(int i_){ //devuelve el color del boton en la pos i_
        IntentoButton ib = botonesIntentoCodigoo.get(i_);
        return ib.getColor();
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

    void colocaBotonesIntentos(int tamCodigo_){

        for(int i = 0; i < tamCodigo_; ++i){
            int aux = (int)(240 / tamCodigo_);
            IntentoButton ib = new IntentoButton(mb,
                    getPosX() + 15 + i * aux + aux/2,
                    getPosY(),
                    diametroIntentoCodigo, diametroIntentoCodigo, mb.log.getEngine().getAudio(), mb.log.currEngine.getSound(), mb.log);

            botonesIntentoCodigoo.add(ib);

            addChild(ib);
        }
    }
}
