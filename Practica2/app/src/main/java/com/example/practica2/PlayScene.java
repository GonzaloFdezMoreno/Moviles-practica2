package com.example.logic;


public class PlayScene extends Scene {

    public PlayScene(Logic logic,int i){
        super(logic);
        log=logic;
        addGameObject(new ExitButton(10,10,20,20,log));

        //creamos tablero de juego con un nivel de  dificultad i
        MastermindBoard mb = new MastermindBoard(log, i,25, 75, 1, 1 );
        addGameObject(mb);

        addGameObject(new DaltonismoButton(mb, 350, 5, 40, 40));

    }

}
