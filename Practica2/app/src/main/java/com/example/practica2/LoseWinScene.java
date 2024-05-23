package com.example.practica2;

import com.example.androidengine.AndrGraphics2D;
import com.example.androidengine.TouchEvent;

import java.io.IOException;
import java.util.ArrayList;

public class LoseWinScene extends Scene{

    Button playAgainButton;
    Button selectDifficultyButton;
    Button adButton;
    Button compartirButton, siguienteNivelButton, menuButton;

    Logic log;
    protected LoseWinScene(Logic logic, int[] codigoSecreto, int nivel, boolean win, int intent, boolean dalton) {
        super(logic);

        String txt;

        log=logic;

        int windowCenterX=logic.getEngine().GetGraphics().getWidth()/2,
                windowCenterY=logic.getEngine().GetGraphics().getHeight()/2;

        if (win){
            txt="ENHORABUENA!!";
            addGameObject(new SceneText(windowCenterX, windowCenterY - 220, 100, 50, txt));

            addGameObject(new SceneText(windowCenterX, windowCenterY - 175, 100, 50,
                    "Has averiguado el codigo en  "));
            addGameObject(new SceneText(windowCenterX, windowCenterY - 125, 100, 50,
                    intent+" intento(s)"));
            logic.money += 50;
            try {
                logic.saveGameSkins();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        else{
            txt = "GAME OVER";
            addGameObject(new SceneText(windowCenterX, windowCenterY - 220, 100, 50, txt));

            addGameObject(new SceneText(windowCenterX, windowCenterY - 175, 100, 50,
                    "Te has quedado sin intentos"));
            addGameObject(new SceneText(windowCenterX, windowCenterY - 125, 100, 50,
                    "Codigo:"));
        }

        addGameObject(new SecretCode(codigoSecreto, windowCenterX - (35/2) * codigoSecreto.length, windowCenterY - 100, 1, 1, dalton, logic));

        //addGameObject(new StartButton(75, 350, 300, 70,"Elegir dificultad",logic ));
        playAgainButton = new Button(windowCenterX,windowCenterY + 100,200,80,"Volver a jugar", 0xFF1FE3E0, log);
        selectDifficultyButton = new Button(windowCenterX,windowCenterY + 200,200,80,"Elegir dificultad", 0xFF1FE3E0, log);
        adButton = new Button(windowCenterX,windowCenterY + 300,200,80,"Ver para recompensa", 0xFF1FE3E0, log);


    }

    //para cuando acabas un nivel de los mundos
    protected LoseWinScene(Logic logic, int[] codigoSecreto, boolean win, int intent, boolean dalton) {
        super(logic);

        String txt;

        log=logic;

        int windowCenterX=logic.getEngine().GetGraphics().getWidth()/2,
                windowCenterY=logic.getEngine().GetGraphics().getHeight()/2;

        if (win){
            txt="ENHORABUENA!!";
            addGameObject(new SceneText(windowCenterX, windowCenterY - 220, 100, 50, txt));

            addGameObject(new SceneText(windowCenterX, windowCenterY - 175, 100, 50,
                    "Has averiguado el codigo en  "));
            addGameObject(new SceneText(windowCenterX, windowCenterY - 125, 100, 50,
                    intent+" intento(s)"));
            logic.money += 50;
            try {
                logic.saveGameSkins();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        else{
            txt = "GAME OVER";
            addGameObject(new SceneText(windowCenterX, windowCenterY - 220, 100, 50, txt));

            addGameObject(new SceneText(windowCenterX, windowCenterY - 175, 100, 50,
                    "Te has quedado sin intentos"));
            addGameObject(new SceneText(windowCenterX, windowCenterY - 125, 100, 50,
                    "Codigo:"));
        }

        addGameObject(new SecretCode(codigoSecreto, windowCenterX - (35/2) * codigoSecreto.length, windowCenterY - 100, 1, 1, dalton, logic));

        //addGameObject(new StartButton(75, 350, 300, 70,"Elegir dificultad",logic ));
        compartirButton = new Button(windowCenterX, windowCenterY, 220, 80, "Compartir", 0xFF1FE3E0, logic);
        playAgainButton = new Button(windowCenterX,windowCenterY + 100,200,80,"Volver a jugar", 0xFF1FE3E0, log);
        selectDifficultyButton = new Button(windowCenterX,windowCenterY + 200,200,80,"Elegir dificultad", 0xFF1FE3E0, log);
        adButton = new Button(windowCenterX,windowCenterY + 300,200,80,"Ver para recompensa", 0xFF1FE3E0, log);


    }
    @Override
    public void update(double deltaTime) {
        super.update(deltaTime);
        playAgainButton.update(deltaTime);
        selectDifficultyButton.update(deltaTime);
        adButton.update(deltaTime);
    }

    @Override
    public void render(AndrGraphics2D graph) {
        if(log.currBG < 4) {
            graph.drawImage(graph.createImage(backgroundImages[log.currBG]), log.getEngine().GetGraphics().getWidth()/2, log.getEngine().GetGraphics().getHeight()/2, 400, 600);

            graph.setColor(0xFFFFFFFF);
            graph.fillRectangle(0,0,400, 50);
            graph.setColor(0xFF000000);
        }

        super.render(graph);
        playAgainButton.render(graph);
        selectDifficultyButton.render(graph);
        adButton.render(graph);
    }

    @Override
    public void handleInput(ArrayList<TouchEvent> event) {
        super.handleInput(event);
        if(playAgainButton.handleInput(event))
            playAgainButtonEffect();
        if(selectDifficultyButton.handleInput(event))
            selectDifficultyButtonEffect();
        if(adButton.handleInput(event))
            loadAdButtonEffect();
    }

    void playAgainButtonEffect(){
        log.SetScene(new LevelMenu(log));
    }
    void selectDifficultyButtonEffect(){
       log.SetScene(new MainMenu(log));
    }

    void loadAdButtonEffect(){ log.currEngine.resetLoaded();  log.currEngine.showRewardAd();  }

    void compartirButtonEffect(){}
}
