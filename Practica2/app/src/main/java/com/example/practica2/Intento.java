package com.example.practica2;

import com.example.androidengine.AndrGraphics2D;
import com.example.androidengine.TouchEvent;

import java.util.ArrayList;

public class Intento extends GameObject { //contiene el "rectangulo" de info. Incluye numero, colores insertados, pistas y
    // lineas separadoras

    private int numIntento = -1;
    int diametroIntentoCodigo = 30;
    int diametroPista = 8;
    ArrayList<IntentoButton> botonesIntentoCodigo;
    ObjectMatrix pistas; //aqui se dibujara cada una de las pistas
    MastermindBoard mb; //referencia para llamar a algunos metodos
    int tamCodigo;
    Intento(MastermindBoard mb_, int numIntento_, int tamCodigo_, int posX_, int posY_, int width_, int height_) {
        super(posX_, posY_, width_, height_);

        mb = mb_;

        tamCodigo = tamCodigo_;

        //introducimos circulos que marcan nuestros intentos
        botonesIntentoCodigo = new ArrayList<IntentoButton>(tamCodigo_);
        colocaBotonesIntentos(tamCodigo_);

        //introducimos pistas
        pistas = new ObjectMatrix(2, getPosX() + 320 , getPosY() + 10, 125, 35);
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
        for(IntentoButton ib : botonesIntentoCodigo){
            ib.handleInput(event);
        }
        pistas.handleInput(event);
        return true;
    }

    @Override
    public void update(double t){
        for(IntentoButton ib : botonesIntentoCodigo){
            ib.update(t);
        }
        pistas.update(t);
    }
    @Override
    public void render(AndrGraphics2D graph){

        graph.setColor(0XFF000000);
        graph.drawRoundRectangle(getPosX(), getPosY(), getWidth(), getHeight(), 10, 10);
        graph.setColor(0XFF000000);
        graph.drawText(String.valueOf(numIntento),getPosX() + 15, getPosY() + 30);

        graph.drawLine(getPosX() + 45, getPosY() + 5, getPosX() + 45, getPosY() + 35);

        graph.drawLine(getPosX() + 280, getPosY() + 5, getPosX() + 280, getPosY() + 35);

        for(IntentoButton ib : botonesIntentoCodigo){
            ib.render(graph);
        }

        pistas.render(graph);
    }

    boolean pintaPrimerBotonIntentoCodigoLibre(int newColor_){
        int i = 0;
        boolean encontrado = false;
        while(i < botonesIntentoCodigo.size() && !encontrado){
            IntentoButton ib = botonesIntentoCodigo.get(i);
            if(ib.colorSet == false){
                ib.setColor(newColor_);
                encontrado = true;
            }
            ++i;
        }
        return encontrado;
    }

    int getColorBotonAt(int i_){ //devuelve el color del boton en la pos i_
        IntentoButton ib = botonesIntentoCodigo.get(i_);
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
            int aux = (int)(240 / tamCodigo_); //aux nos ayuda a espaciar en función de la cantidad de pistas que tenemos. A más pistas, menos espacio entre cada IntentoButton

            IntentoButton ib = new IntentoButton(mb,
                    getPosX() + 65 + i * aux,
                    getPosY() + 20,
                    diametroIntentoCodigo, diametroIntentoCodigo, mb.log.getEngine().getAudio(), mb.log.currEngine.getSound(), mb.log);

            botonesIntentoCodigo.add(ib);

            addChild(ib);
        }
    }
}
