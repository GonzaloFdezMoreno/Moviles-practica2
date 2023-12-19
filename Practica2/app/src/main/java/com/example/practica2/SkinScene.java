package com.example.practica2;

import com.example.androidengine.AndrGraphics2D;
import com.example.androidengine.TouchEvent;

import java.util.ArrayList;

public class SkinScene extends Scene{

    Button goBack;
    Button pokerBG;
    Button emojiBG;
    Button turkeyBG;
    Button santaBG;
    Button defaultBG;
    Button coinSkin;
    Button emojiSkin;
    Button foodSkin;
    Button xmasSkin;
    Button defaultSkin;

    //String[] backgroundImages = {"world1_background.jpg", "world2_bg.jpeg", "world3_bg.jpg", "world4_bg.jpeg"};

    protected SkinScene(Logic logic){
        super(logic);

        goBack = new Button(20,20,20,20, "goback.png", log.currEngine.getAudio(), log.currEngine.getSound());

        pokerBG = new Button(10,100,60,100, "world1_background.jpg", log.currEngine.getAudio(), log.currEngine.getSound());
        emojiBG = new Button(90,100,60,100, "world2_bg.jpeg", log.currEngine.getAudio(), log.currEngine.getSound());
        turkeyBG = new Button(170,100,60,100, "world3_bg.jpg", log.currEngine.getAudio(), log.currEngine.getSound());
        santaBG = new Button(250,100,60,100, "world4_bg.jpeg", log.currEngine.getAudio(), log.currEngine.getSound());
        defaultBG = new Button(330,100,60,100, "default_bg.jpg", log.currEngine.getAudio(), log.currEngine.getSound());
        coinSkin = new Button(20,300,60,60, "sprites/coins/amarillo.png", log.currEngine.getAudio(), log.currEngine.getSound());
        emojiSkin = new Button(100,300,60,60, "sprites/emojis/amarillo.png", log.currEngine.getAudio(), log.currEngine.getSound());
        foodSkin = new Button(180,300,60,60, "sprites/food/amarillo.png", log.currEngine.getAudio(), log.currEngine.getSound());
        xmasSkin = new Button(40,400,60,60, "sprites/xmas/rojo.png", log.currEngine.getAudio(), log.currEngine.getSound());
        defaultSkin = new Button(140,400,60,60, "defaultSkin.png", log.currEngine.getAudio(), log.currEngine.getSound());
    }

    public void render(AndrGraphics2D graph){
        super.render(graph);

        if(log.currBG < 4) {
            graph.drawImage(graph.createImage(backgroundImages[log.currBG]), 0, 0, 400, 600);

            graph.setColor(0xFFFFFFFF);
            graph.fillRectangle(0,0,400, 50);
            graph.setColor(0xFF000000);
        }

        goBack.render(graph);
        pokerBG.render(graph);
        emojiBG.render(graph);
        turkeyBG.render(graph);
        santaBG.render(graph);
        defaultBG.render(graph);
        coinSkin.render(graph);
        emojiSkin.render(graph);
        foodSkin.render(graph);
        xmasSkin.render(graph);
        defaultSkin.render(graph);
    }

    @Override
    public void handleInput(ArrayList<TouchEvent> event) {
        super.handleInput(event);

        if(goBack.handleInput(event)){
            log.SetScene(new MainMenu(log));
        }
        if(pokerBG.handleInput(event)){
            log.currBG=0;
        }
        if(emojiBG.handleInput(event)){
            log.currBG=1;
        }
        if(turkeyBG.handleInput(event)){
            log.currBG=2;
        }
        if(santaBG.handleInput(event)){
            log.currBG=3;
        }
        if(defaultBG.handleInput(event)){
            log.currBG=4;
        }
        if(coinSkin.handleInput(event)){
            log.currSkin=0;
        }
        if(emojiSkin.handleInput(event)){
            log.currSkin=1;
        }
        if(foodSkin.handleInput(event)){
            log.currSkin=2;
        }
        if(xmasSkin.handleInput(event)){
            log.currSkin=3;
        }
        if(defaultSkin.handleInput(event)){
            log.currSkin=4;
        }
    }
}
