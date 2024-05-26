package com.example.practica2;

import android.content.Intent;

import com.example.androidengine.AndrGraphics2D;
import com.example.androidengine.AndroidEngine;
import com.example.androidengine.TouchEvent;

import java.io.IOException;
import java.util.ArrayList;

public class LoseWinScene extends Scene{

    Button playAgainButton = null, selectDifficultyButton = null, adButton = null;
    Button compartirButton = null, siguienteNivelButton = null, menuButton = null;

    ArrayList<Button> usedButtons;

    Logic log;

    boolean worldWin;
    int difficulty = -1;
    protected LoseWinScene(Logic logic, int[] codigoSecreto, boolean win, int intent, boolean dalton, boolean worldWin_, int difficulty_) {
        super(logic);

        String txt;

        log=logic;

        worldWin = worldWin_;

        difficulty = difficulty_;

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
            addGameObject(new SceneText(windowCenterX, windowCenterY - 70, 100, 50, "has ganado 50 monedas"));
            addGameObject(new SceneText(windowCenterX, windowCenterY - 50, 100, 50, "total de monedas: "+logic.money));
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

        usedButtons = new ArrayList<>();

        if(!worldWin){
            playAgainButton = new Button(windowCenterX,windowCenterY + 100,200,80,"Volver a jugar", 0xFF1FE3E0, log);
            selectDifficultyButton = new Button(windowCenterX,windowCenterY + 200,200,80,"Elegir dificultad", 0xFF1FE3E0, log);
            adButton = new Button(windowCenterX,windowCenterY + 300,200,80,"Ver para recompensa", 0xFF1FE3E0, log);

            usedButtons.add(playAgainButton);
            usedButtons.add(selectDifficultyButton);
            usedButtons.add(adButton);
        }
        else{
            compartirButton = new Button(windowCenterX, windowCenterY, 220, 80, "Compartir", 0xFF1FE3E0, logic);;
            siguienteNivelButton = new Button(windowCenterX, windowCenterY + 100, 220, 80, "Siguiente Nivel", 0xFF1FE3E0, logic);
            menuButton = new Button(windowCenterX, windowCenterY + 200, 220, 80, "Menú", 0xFF1FE3E0, logic);;

            usedButtons.add(compartirButton);
            usedButtons.add(siguienteNivelButton);
            usedButtons.add(menuButton);
        }


    }

    @Override
    public void update(double deltaTime) {
        super.update(deltaTime);

        for(Button b : usedButtons)
            b.update(deltaTime);
    }

    @Override
    public void render(AndrGraphics2D graph) {
        if(log.currBG < 4) {
            graph.drawImage(graph.createImage(backgroundImages[log.currBG]), log.getEngine().GetGraphics().getWidth()/2, log.getEngine().GetGraphics().getHeight()/2, 800, 1200);

        }

        super.render(graph);
        for(Button b : usedButtons)
            b.render(graph);
    }

    @Override
    public void handleInput(ArrayList<TouchEvent> event) {
        super.handleInput(event);

        if(!worldWin){
            if(playAgainButton.handleInput(event))
                playAgainButtonEffect();
            if(selectDifficultyButton.handleInput(event))
                selectDifficultyButtonEffect();
            if(adButton.handleInput(event))
                loadAdButtonEffect();
        }
        else{
            if(compartirButton.handleInput(event))
                compartirButtonEffect();
            if(siguienteNivelButton.handleInput(event))
                siguienteNivelButtonEffect();
            if(menuButton.handleInput(event))
                menuButtonEffect();
        }


    }

    void playAgainButtonEffect(){
        log.SetScene(new PlayScene(log, difficulty));
    }
    void selectDifficultyButtonEffect(){
       log.SetScene(new LevelMenu(log));
    }

    void loadAdButtonEffect(){ log.currEngine.resetLoaded();  log.currEngine.showRewardAd();  }

    void compartirButtonEffect(){
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_TEXT, "I just beat this goofy aah level!! wow!!!");
        intent.setType("text/plain");

        Intent shareIntent = Intent.createChooser(intent, null);
        ((AndroidEngine) log.getEngine()).getContext().startActivity(shareIntent);
    }
    void siguienteNivelButtonEffect(){log.SetScene(new MainMenu(log));}
    void menuButtonEffect(){log.SetScene(new MainMenu(log));}
}
