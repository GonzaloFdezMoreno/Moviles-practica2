package com.example.practica2;

import com.example.androidengine.AndrGraphics2D;
import com.example.androidengine.TouchEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;


class Level{
    int codeSize, codeOpt, attempts;
    boolean repeat;
    Level(int codeSize_, int codeOpt_, boolean repeat_, int attempts_){
        codeSize = codeSize_;
        codeOpt = codeOpt_;
        repeat = repeat_;
        attempts = attempts_;
    }
}
public class MastermindBoard extends GameObject {

    Logic log; //para poder cambiar entre escenas cuando ganas, pierdes...
    int nivelDificultad;
    Random random = new Random(); //para poder generara valores aleatorios

    // introducimos aqui los colores que queremos que esten en el juego. DEBE HABER 9. Si no seleccion de dificultad fallara
    int hexColores[] = {0xffff0000,0Xffff9033,0xfffffc33,0xff00ff00,0xff33fffb,0xff334dff,0xfff433ff,0xffff33a5,0xff991111};
                        //ROJO   NARANJA  AMARILLO VERDE    CIAN     AZUL     MAGENTA  ROSA     ROJOSCURO

    //cuando creamos un tablero, asignaremos estas caracteristicas en el metodo estableceCaracteristicasTablero(int dificultad)
    static private class caractTablero{
        int maxIntentos, tamCodigo, numColoresCodigo;   //cuantas oportunidades tenemos para acertar,
                                                        //como de largo es el codigo secreto,
                                                        //de los 9 hexColores, cuantos usaremos para el codigo --> fallaria si no hay repeticiones y hay menos colores a elegir que tamCodigo
        boolean repeticionColores; //se repiten colores al crear codigo secreto
    }
    caractTablero currTableroCaracteristicas; //aqui guardamos las caractTablero del tablero en el que jugaremos
    int anchoIntento = 355, altoIntento = 40, margenAltoIntento = 45; // tamaños para los intentos en pantalla
    int numIntentoActual = 0; //controla en que Intento pintamos. Si acabamos el ultimo y no acertamos el codigo, perdemos
    Intento intentos[]; //un Intento es cada una de las barras donde el jugador coloca colores, y contiene tambien las pistas
    int codigoSecreto[];

    ObjectMatrix selector; //aqui estaran los botones para seleccionar cada codigo. Es un ObjectMatrix de objetos tipo ColorSetterButton
    int cantColoresRellenados = 0; //cuando haya tamCodigo de estos, comprobamos con pistas(estara completado un Intento)
    Map<Integer, Integer> cuantoDeCadaColorEnCodigoSecreto; //para rapidamente saber cuanto de cada color hay en el codigoSecreto(Amarillo:2, Rojo:3...)
    boolean daltonismo; //modo daltonismo, muestra numeros encima de los colores para identificarlos facilmente
    MastermindBoard(Logic log_, int nivDificultad_, int posX_, int posY_, int width_, int height_) {
        super(posX_, posY_, width_, height_);

        log = log_;

        nivelDificultad = nivDificultad_;

        boolean errNivel = estableceCaracteristicasTablero(nivDificultad_); //en funcion de la dificultad, creamos el tablero
        if(!errNivel)
            System.out.println("ERROR ESTABLECIENDO CARACT. TABLERO, NIVEL NO ESTABA ENTRE [0 Y 3]");

        //creamos la secuencia vacia de todos los intentos
        intentos = new Intento[currTableroCaracteristicas.maxIntentos];
        for (int i = 0; i < currTableroCaracteristicas.maxIntentos; ++i) {
            intentos[i] = new Intento(this, i+1, currTableroCaracteristicas.tamCodigo, getPosX(), getPosY() + margenAltoIntento * i, anchoIntento, altoIntento);
        }

        //creamos el selector
        selector = new ObjectMatrix(1,posX_ - 20, 540, anchoIntento, altoIntento);//new Selector(tamCodigo, posX_ + 150, getPosY() + 600, anchoIntento, altoIntento);
        for (int i = 0; i < currTableroCaracteristicas.numColoresCodigo; ++i){

            //podemos ver que no importa la pos en la que se colocan los objetos, pues con selector.setObjectsPositionsInMatrix(); los colocamos correctamente
            selector.addObjectToMatrix(new ColorSetterButton(this, hexColores[i], -1, -1, 30, 30, log.currEngine.getAudio()));
        }
        selector.setObjectsPositionsInMatrix(); //necesario para situar los elementos dentro del ObjectMatrix, asi no lo hacemos en cada vuelta del bucle

        //creamos codigo secreto
        codigoSecreto = new int[currTableroCaracteristicas.tamCodigo];
        cuantoDeCadaColorEnCodigoSecreto = new HashMap<>();
        estableceNuevoCodigoSecreto();

        daltonismo = false;

    }

    MastermindBoard(Logic log_, Level l, int posX_, int posY_, int width_, int height_) {
        super(posX_, posY_, width_, height_);

        log = log_;

        currTableroCaracteristicas.maxIntentos = l.attempts;
        currTableroCaracteristicas.tamCodigo = l.codeSize;
        currTableroCaracteristicas.repeticionColores = l.repeat;
        currTableroCaracteristicas.numColoresCodigo = l.codeOpt;

        //creamos la secuencia vacia de todos los intentos
        intentos = new Intento[currTableroCaracteristicas.maxIntentos];
        for (int i = 0; i < currTableroCaracteristicas.maxIntentos; ++i) {
            intentos[i] = new Intento(this, i+1, currTableroCaracteristicas.tamCodigo, getPosX(), getPosY() + margenAltoIntento * i, anchoIntento, altoIntento);
        }

        //creamos el selector
        selector = new ObjectMatrix(1,posX_ - 20, 540, anchoIntento, altoIntento);//new Selector(tamCodigo, posX_ + 150, getPosY() + 600, anchoIntento, altoIntento);
        for (int i = 0; i < currTableroCaracteristicas.numColoresCodigo; ++i){

            //podemos ver que no importa la pos en la que se colocan los objetos, pues con selector.setObjectsPositionsInMatrix(); los colocamos correctamente
            selector.addObjectToMatrix(new ColorSetterButton(this, hexColores[i], -1, -1, 30, 30, log.currEngine.getAudio()));
        }
        selector.setObjectsPositionsInMatrix(); //necesario para situar los elementos dentro del ObjectMatrix, asi no lo hacemos en cada vuelta del bucle

        //creamos codigo secreto
        codigoSecreto = new int[currTableroCaracteristicas.tamCodigo];
        cuantoDeCadaColorEnCodigoSecreto = new HashMap<>();
        estableceNuevoCodigoSecreto();

        daltonismo = false;

    }
    @Override
    public boolean handleInput(ArrayList<TouchEvent> event) {

        intentos[numIntentoActual].handleInput(event);

        selector.handleInput(event);

        return true;
    }

    @Override
    public void update(double t){
        for (Intento intento : intentos) {
            intento.update(t);
        }

    }
    @Override
    public void render(AndrGraphics2D graph) {
        graph.setFont(graph.createFont("AARVC___.TTF",25,false,false));

        if(currTableroCaracteristicas.maxIntentos-numIntentoActual>1) {
            graph.drawText("Te quedan " + (currTableroCaracteristicas.maxIntentos - numIntentoActual) + " intentos", 100, 30);
        }
        else {
            graph.drawText("Te queda " + (currTableroCaracteristicas.maxIntentos - numIntentoActual) + " intento", 100, 30);
        }
        for (Intento intento : intentos) {
            intento.render(graph);
        }

        selector.render(graph);
        pintaCodigo(graph); //muestra el codigo secreto
    }

    //seteamos currTableroCaracteristicas
    boolean estableceCaracteristicasTablero(int dificultad){

        if(dificultad < 0 || dificultad > 3){
            System.out.println("Error, nivel de dificultad no valido" + dificultad);
            return false;
        }

        currTableroCaracteristicas = new caractTablero();

        switch (dificultad){
            case 0:
                currTableroCaracteristicas.maxIntentos = 6;
                currTableroCaracteristicas.tamCodigo = 4;
                currTableroCaracteristicas.numColoresCodigo = 4;
                currTableroCaracteristicas.repeticionColores = false;

                break;
            case 1:
                currTableroCaracteristicas.maxIntentos = 8;
                currTableroCaracteristicas.tamCodigo = 4;
                currTableroCaracteristicas.numColoresCodigo = 6;
                currTableroCaracteristicas.repeticionColores = false;

                break;
            case 2:
                currTableroCaracteristicas.maxIntentos = 10;
                currTableroCaracteristicas.tamCodigo = 5;
                currTableroCaracteristicas.numColoresCodigo = 8;
                currTableroCaracteristicas.repeticionColores = true;

                break;
            case 3:
                currTableroCaracteristicas.maxIntentos = 10;
                currTableroCaracteristicas.tamCodigo = 6;
                currTableroCaracteristicas.numColoresCodigo = 9;
                currTableroCaracteristicas.repeticionColores = true;

                break;
            default:
                break;
        }

        return true;
    }

    //creamos el codigo secreto en funcion de las caracteristicas que pasamos al tablero
    void estableceNuevoCodigoSecreto(){

        ArrayList<Integer> coloresQueUsaremos = new ArrayList<>();
        for (int i = 0; i < currTableroCaracteristicas.numColoresCodigo; ++i)
            coloresQueUsaremos.add(hexColores[i]);


        int randomInt;
        for (int i = 0; i < currTableroCaracteristicas.tamCodigo; ++i) {

            randomInt = random.nextInt(coloresQueUsaremos.size());
            int colorResultante = coloresQueUsaremos.get(randomInt);
            codigoSecreto[i] = colorResultante;

            if (cuantoDeCadaColorEnCodigoSecreto.containsKey(colorResultante)) {
                cuantoDeCadaColorEnCodigoSecreto.put(colorResultante, cuantoDeCadaColorEnCodigoSecreto.get(colorResultante) + 1);
            } else {
                cuantoDeCadaColorEnCodigoSecreto.put(colorResultante, 1);
            }

            if (!currTableroCaracteristicas.repeticionColores)
                coloresQueUsaremos.remove(randomInt);
        }

    }

    //cuando pulsamos un boton de selector, se llama a este metodo para rellenar el siguiente hueco disponible. Si estan
    //todos rellenos, comprobamos que tal le ha ido al jugador y seteamos las psitas
    void rellenaSiguienteHuecoIntentoActual(int color_){
        intentos[numIntentoActual].pintaPrimerBotonIntentoCodigoLibre(color_); //dentro hay un bucle que recorre hasta encontrarlo
        cantColoresRellenados++;

        if(cantColoresRellenados >= currTableroCaracteristicas.tamCodigo){
            compruebaPistas();
        }
    }

    //finalizado un intento, rellenamos las pistas para indicar al jugador que tal lo ha hecho
    void compruebaPistas(){
        int numAciertos = 0;
        //int aciertos[] = new int[currTableroCaracteristicas.tamCodigo]; //0 es fallo total, 1 es acierto, 2 es esta pero en otra pos
        Map<Integer, Integer> aux = new HashMap<>();
        aux.putAll(cuantoDeCadaColorEnCodigoSecreto); //copia de contenido

        //hacemos un primer recorrido para aquellos en las posiciones correctas, separamos para no hacer un bucle dentro
        //del for que, si encontramos un color, tendriamos que buscar si existe ese color bien colocado mas adelante.
        //Ej.: codigo azul, rojo --- introducimos rojo, rojo. El primero rojo debe dar que NO esta, porque hemos colocado
        //el segundo rojo en la pos correcta. Si hubieramos metido rojo, amarillo, entonces el primer rojo pasaria a ser
        //esta, pero en pos incorrecta
        for(int i = 0; i < currTableroCaracteristicas.tamCodigo; ++i){
            int colorComprobado = codigoSecreto[i];
            int colorColocado = intentos[numIntentoActual].getColorBotonAt(i);
            if(colorComprobado == colorColocado){
                //aciertos[i] = 1;
                aux.put(colorComprobado, aux.get(colorComprobado) - 1);
                ++numAciertos;
            }
        }

        //comprobamos si ha ganado
        if(numAciertos >= currTableroCaracteristicas.tamCodigo){
            log.SetScene(new LoseWinScene(log, codigoSecreto,nivelDificultad,"ENHORABUENA!!",numIntentoActual+1, daltonismo));
            return;
        }

        int numAciertosPosIncorrecta = 0;
        //pasada para los no en pos correcta y los que no estan
        for(int i = 0; i < currTableroCaracteristicas.tamCodigo; ++i){

            int colorComprobado = codigoSecreto[i];
            int colorColocado = intentos[numIntentoActual].getColorBotonAt(i);

            if(colorComprobado != colorColocado) {
                if (aux.containsKey(colorColocado) && aux.get(colorColocado) > 0) {
                    numAciertosPosIncorrecta++;
                    //aciertos[i] = 2;
                    aux.put(colorColocado, aux.get(colorColocado) - 1);
                } //else {
                    //aciertos[i] = 0;
                //}
            }
        }

        //ponemos colores a las pistas
        //intentos[numIntentoActual].rellenaPistas2(aciertos);
        intentos[numIntentoActual].rellenaPistas2(numAciertos, numAciertosPosIncorrecta);
        cantColoresRellenados = 0;

        numIntentoActual++;

        //comprobamos si ha perdido
        if(numIntentoActual >= currTableroCaracteristicas.maxIntentos){
            log.SetScene(new LoseWinScene(log, codigoSecreto,nivelDificultad,"GAME OVER",0, daltonismo));
        }
    }

    //auxiliar, para poder visualizar el codigo secreto
    void pintaCodigo(AndrGraphics2D graph){
        for(int i = 0; i < codigoSecreto.length; ++i){
            graph.setColor(codigoSecreto[i]);
            graph.fillCircle(getPosX() + 50 * i, getPosY()-25, 25);
            graph.setColor(0xFF000000);

        }
    }

    void setDaltonismo(boolean dalton){
        daltonismo = dalton;
    }

    public boolean isDaltonismo() {
        return daltonismo;
    }
}
