package com.example.practica2;



public class MainMenu extends Scene {


    public MainMenu(Logic logic){
        super(logic);
        log=logic;


        //grph.drawRectangle(200,200,100,100);
        //addGameObject(new StartButton(100,100,200,200,this.eng));
        //addGameObject(new MastermindBoard(3,25, 75, 1, 1 ));

        addGameObject(new SceneText(150,100,0,0,"MASTERMIND"));
        addGameObject(new StartButton(100,400,200,80,"JUGAR",this.log));




        //addGameObject(new StartButton(250,400,200,70,this.eng));


        //addGameObject(new StartButton(grph.getWidth()/3, (grph.getHeight()/5)*4,grph.getWidth()/3,grph.getHeight()/5,this.eng));


    }



}
