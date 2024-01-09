package com.example.practica2;

import com.example.androidengine.AndrGraphics2D;
import com.example.androidengine.TouchEvent;

import java.util.ArrayList;

public class ConjuntoIntentos extends GameObject{

    Intento intentos[];
    MastermindBoard mb;
    int anchoIntento = 355, altoIntento = 40, margenAltoIntento = 45; // tamaños para los intentos en pantalla
    public ConjuntoIntentos(MastermindBoard mb_, int posX_, int posY_, int width_, int height_, int numIntentos_, int tamCodigo_) {
        super(posX_, posY_, width_, height_);

        //creamos la secuencia vacia de todos los intentos
        intentos = new Intento[numIntentos_];
        for (int i = 0; i < numIntentos_; ++i) {
            Intento intent = new Intento(mb_, i+1, tamCodigo_, getPosX(), getPosY() + margenAltoIntento * i, anchoIntento, altoIntento);
            intentos[i] = intent;
            addChild(intent);
        }

        mb = mb_;
    }

    @Override
    public void render(AndrGraphics2D graph) {
        super.render(graph);

        for(Intento i : intentos)
            i.render(graph);
    }

    @Override
    public void update(double delta) {
        super.update(delta);

        for(Intento i : intentos){
            i.update(delta);
        }
    }

    @Override
    public boolean handleInput(ArrayList<TouchEvent> event) {

        intentos[mb.numIntentoActual].handleInput(event);

        return super.handleInput(event);
    }
}
