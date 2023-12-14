package com.example.practica2;

import com.example.androidengine.AndrGraphics2D;
import com.example.androidengine.TouchEvent;

import java.util.ArrayList;

public class LoseWinScene extends Scene{

    Button playAgainButton;
    Button selectDifficultyButton;
    protected LoseWinScene(Logic logic, int[] codigoSecreto, int nivel,String txt,int intent, boolean dalton) {
        super(logic);

        addGameObject(new SceneText(100, 80, 100, 50, txt));
        addGameObject(new SceneText(100, 80, 100, 50, txt));

        if (txt=="ENHORABUENA!!"){
            addGameObject(new SceneText(50, 125, 100, 50,
                    "Has averiguado el codigo en  "));
            addGameObject(new SceneText(100, 175, 100, 50,
                    intent+" intento(s)"));
        }
        else if (txt=="GAME OVER"){
            addGameObject(new SceneText(50, 125, 100, 50,
                    "Te has quedado sin intentos"));
            addGameObject(new SceneText(50, 175, 100, 50,
                    "Codigo:"));
        }

        addGameObject(new SecretCode(codigoSecreto, 150, 200, 1, 1, dalton));

        //addGameObject(new StartButton(75, 350, 300, 70,"Elegir dificultad",logic ));
        playAgainButton = new Button(100,400,200,80,"Volver a jugar", 0xFF1FE3E0, logic.currEngine.getAudio(), log.currEngine.getSound());
        selectDifficultyButton = new Button(100,500,200,80,"Elegir difificultad", 0xFF1FE3E0, logic.currEngine.getAudio(), log.currEngine.getSound());
    }

    @Override
    public void update(double deltaTime) {
        super.update(deltaTime);
        playAgainButton.update(deltaTime);
        selectDifficultyButton.update(deltaTime);
    }

    @Override
    public void render(AndrGraphics2D graph) {
        super.render(graph);
        playAgainButton.render(graph);
        selectDifficultyButton.render(graph);
    }

    @Override
    public void handleInput(ArrayList<TouchEvent> event) {
        super.handleInput(event);
        if(playAgainButton.handleInput(event))
            playAgainButtonEffect();
        if(selectDifficultyButton.handleInput(event))
            selectDifficultyButtonEffect();
    }

    void playAgainButtonEffect(){
        log.SetScene(new LevelMenu(log));
    }
    void selectDifficultyButtonEffect(){
       log.SetScene(new MainMenu(log));
    }
}
