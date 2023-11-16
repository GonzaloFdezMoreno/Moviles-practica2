package com.example.logic;

import com.example.commonengine.CAudio;
import com.example.commonengine.CGraphics2D;
import com.example.commonengine.TouchEvent;

import java.util.ArrayList;

public class ObjectMatrix extends GameObject { //utilizamos esta clase para poder agrupar objetos de tipo GameObject(o que hereden)
                                                //y poder organizarlos en una o mas filas(como las pistas)

    int numElems = 0;
    int numRows;
    double width, height;
    private ArrayList<GameObject> matrixObjects;

    public ObjectMatrix(int numRows_, int posX_, int posY_, int width_, int height_) {
        super(posX_, posY_, width_, height_);

        matrixObjects = new ArrayList<>();
        numRows = numRows_;
        width = width_;
        height = height_;
    }

    @Override
    public boolean handleInput(ArrayList<TouchEvent> event, CAudio audio) {

        for(int i = 0; i < matrixObjects.size(); ++i){
            matrixObjects.get(i).handleInput(event,audio);
        }
        return true;
    }

    @Override
    public void update(double t){
        for(int i = 0; i < matrixObjects.size(); ++i){
            matrixObjects.get(i).update(t);
        }
    }
    @Override
    public void render(CGraphics2D graph) {
        for(int i = 0; i < matrixObjects.size(); ++i){
            matrixObjects.get(i).render(graph);
        }
    }

    //añade un objeto a la coleccion de objetos
    void addObjectToMatrix(GameObject gObj){
        matrixObjects.add(gObj);
        numElems++;
        //setObjectsPositionsInMatrix(); //quitado para nuestro caso, podria ser interesante si se quiere ver actualizado en tiempo real
    }

    GameObject getElemAtIndex(int i){
        return matrixObjects.get(i);
    }

    void setObjectsPositionsInMatrix(){
        int numObjectsPerRow = matrixObjects.size() / numRows;
        int extraObjects = matrixObjects.size() % numRows; //las primeras x filas tendran un elem extra mas que numObjectsPerRow

        int index = 0; //el objeto actual que estamos colocando

        //vamos colocando fila por fila
        for (int row = 0; row < numRows; ++row) {
            int objectsInThisRow = numObjectsPerRow;
            if (extraObjects > 0) {
                objectsInThisRow++;
            }

            //colocamos todos los objetos de la fila actual
            for (int i = 0; i < objectsInThisRow; ++i) {

                if(extraObjects > 0)
                    matrixObjects.get(index).setPosX(getPosX() + i * (int)(width / matrixObjects.size()));
                else
                    matrixObjects.get(index).setPosX(getPosX() + i * (int)(width /matrixObjects.size()) + (int)(width /matrixObjects.size())/2);

                matrixObjects.get(index).setPosY((int)(height /numRows) * row + this.getPosY());
                index++;
            }

            if (extraObjects > 0) {
                extraObjects--;
            }
        }
    }


}
