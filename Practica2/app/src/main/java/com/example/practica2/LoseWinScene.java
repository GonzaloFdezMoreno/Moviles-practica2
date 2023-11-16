package com.example.practica2;

public class LoseWinScene extends Scene{
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
        //addGameObject(new RepetirNivelButton(logic, "Volver a jugar", nivel, 75, 250, 300, 70));
        addGameObject(new StartButton(75, 350, 300, 70,"Elegir dificultad",logic ));

    }
}
