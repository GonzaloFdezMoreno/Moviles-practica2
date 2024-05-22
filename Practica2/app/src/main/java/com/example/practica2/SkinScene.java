package com.example.practica2;

import com.example.androidengine.AndrGraphics2D;
import com.example.androidengine.TouchEvent;

import java.io.IOException;
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
    SceneText moneyText;
    SceneText pokerText;
    SceneText emojiBGText;
    SceneText turkeyText;
    SceneText santaText;
    SceneText coinText;
    SceneText emojiSkinText;
    SceneText foodText;
    SceneText xmasText;
    int bgWidth, bgHeight;
    protected SkinScene(Logic logic){
        super(logic);

        int windowCenterX=log.getEngine().GetGraphics().getWidth()/2,
                windowCenterY=log.getEngine().GetGraphics().getHeight()/2;

        moneyText = new SceneText(windowCenterX, 30, 100, 50, "Current Money: " + log.money);

        goBack = new Button(windowCenterX - 180,20,20,20, "goback.png", log);

        int uiWidth = 60;
        bgWidth = 60; bgHeight = 100;

        pokerBG = new Button(windowCenterX - 190,100,uiWidth,bgHeight, "levels/world1/world_bg.jpg", log);
        if(log.unlockedBackgrounds[0])
            pokerText = new SceneText(windowCenterX - 190, 230, uiWidth, 30, "GOT");
        else
            pokerText = new SceneText(windowCenterX - 190, 230, uiWidth, 30, "50");

        emojiBG = new Button(windowCenterX - 110,100,uiWidth,bgHeight, "levels/world2/world_bg.jpeg", log);
        if(log.unlockedBackgrounds[1])
            emojiBGText = new SceneText(windowCenterX - 110, 230, uiWidth, 30, "GOT");
        else
            emojiBGText = new SceneText(windowCenterX - 110, 230, uiWidth, 30, "50");

        turkeyBG = new Button(windowCenterX - 30,100,uiWidth,bgHeight, "levels/world3/world_bg.jpg", log);
        if(log.unlockedBackgrounds[2])
            turkeyText = new SceneText(windowCenterX - 30, 230, uiWidth, 30, "GOT");
        else
            turkeyText = new SceneText(windowCenterX - 30, 230, uiWidth, 30, "50");

        santaBG = new Button(windowCenterX + 50,100,uiWidth,bgHeight, "levels/world4/world_bg.jpeg", log);
        if(log.unlockedBackgrounds[3])
            santaText = new SceneText(windowCenterX + 50, 230, uiWidth, 30, "GOT");
        else
            santaText = new SceneText(windowCenterX + 50, 230, uiWidth, 30, "50");

        defaultBG = new Button(windowCenterX + 130,100,uiWidth,bgHeight, "default_bg.jpg", log);
        addGameObject(new SceneText(windowCenterX + 130, 230, uiWidth, 30, "GOT"));

        coinSkin = new Button(windowCenterX - 180,300,uiWidth,60, "sprites/coins/amarillo.png", log);
        if(log.unlockedSkins[0])
            coinText = new SceneText(windowCenterX - 180, 390, uiWidth, 30, "GOT");
        else
            coinText = new SceneText(windowCenterX - 180, 390, uiWidth, 30, "50");

        emojiSkin = new Button(windowCenterX - 40,300,uiWidth,60, "sprites/emojis/amarillo.png", log);
        if(log.unlockedSkins[1])
            emojiSkinText = new SceneText(windowCenterX - 40, 390, uiWidth, 30, "GOT");
        else
            emojiSkinText = new SceneText(windowCenterX - 40, 390, uiWidth, 30, "50");

        foodSkin = new Button(windowCenterX + 80,300,uiWidth,60, "sprites/food/amarillo.png",log);
        if(log.unlockedSkins[2])
            foodText = new SceneText(windowCenterX + 80, 390, uiWidth, 30, "GOT");
        else
            foodText = new SceneText(windowCenterX + 80, 390, uiWidth, 30, "50");

        xmasSkin = new Button(windowCenterX - 120,420,uiWidth,60, "sprites/xmas/rojo.png", log);
        if(log.unlockedSkins[3])
            xmasText = new SceneText(windowCenterX - 120, 510, uiWidth, 30, "GOT");
        else
            xmasText = new SceneText(windowCenterX - 120, 510, uiWidth, 30, "50");

        defaultSkin = new Button(windowCenterX + 40,420,uiWidth,60, "defaultSkin.png", log);
        addGameObject(new SceneText(windowCenterX + 40, 510, uiWidth, 30, "GOT"));
    }

    public void render(AndrGraphics2D graph){

        if(log.currBG < 4) {
            graph.drawImage(graph.createImage(backgroundImages[log.currBG]), log.getEngine().GetGraphics().getWidth()/2, log.getEngine().GetGraphics().getHeight()/2, 400, 600);

            graph.setColor(0xFFFFFFFF);
            graph.fillRectangle(0,0,400, 50);
            graph.setColor(0xFF000000);
        }

        super.render(graph);

        moneyText.render(graph);
        goBack.render(graph);

        pokerBG.render(graph);
        pokerText.render(graph);
        emojiBG.render(graph);
        emojiBGText.render(graph);
        turkeyBG.render(graph);
        turkeyText.render(graph);
        santaBG.render(graph);
        santaText.render(graph);
        defaultBG.render(graph);

        coinSkin.render(graph);
        coinText.render(graph);
        emojiSkin.render(graph);
        emojiSkinText.render(graph);
        foodSkin.render(graph);
        foodText.render(graph);
        xmasSkin.render(graph);
        xmasText.render(graph);
        defaultSkin.render(graph);

        switch(log.currBG){
            case 0:
                graph.setColor(0x55000000);
                graph.fillRectangle(pokerBG.getPosX() - bgWidth/2, pokerBG.getPosY() - bgHeight/2, pokerBG.getWidth(), pokerBG.getHeight());
                break;
            case 1:
                graph.setColor(0x55000000);
                graph.fillRectangle(emojiBG.getPosX()- bgWidth/2, emojiBG.getPosY() - bgHeight/2, emojiBG.getWidth(), emojiBG.getHeight());
                break;
            case 2:
                graph.setColor(0x55000000);
                graph.fillRectangle(turkeyBG.getPosX()- bgWidth/2, turkeyBG.getPosY() - bgHeight/2, turkeyBG.getWidth(), turkeyBG.getHeight());
                break;
            case 3:
                graph.setColor(0x55000000);
                graph.fillRectangle(santaBG.getPosX()- bgWidth/2, santaBG.getPosY() - bgHeight/2, santaBG.getWidth(), santaBG.getHeight());
                break;
            case 4:
                graph.setColor(0x55000000);
                graph.fillRectangle(defaultBG.getPosX()- bgWidth/2, defaultBG.getPosY() - bgHeight/2, defaultBG.getWidth(), defaultBG.getHeight());
                break;
        }
        graph.setColor(0xFF000000);

        switch(log.currSkin){
            case 0:
                graph.setColor(0x55000000);
                graph.fillCircle(coinSkin.getPosX(), coinSkin.getPosY(), coinSkin.getWidth());
                break;
            case 1:
                graph.setColor(0x55000000);
                graph.fillCircle(emojiSkin.getPosX(), emojiSkin.getPosY(), emojiSkin.getWidth());
                break;
            case 2:
                graph.setColor(0x55000000);
                graph.fillCircle(foodSkin.getPosX(), foodSkin.getPosY(), foodSkin.getWidth());
                break;
            case 3:
                graph.setColor(0x55000000);
                graph.fillCircle(xmasSkin.getPosX(), xmasSkin.getPosY(), xmasSkin.getWidth());
                break;
            case 4:
                graph.setColor(0x55000000);
                graph.fillCircle(defaultSkin.getPosX(), defaultSkin.getPosY(), defaultSkin.getWidth());
                break;
        }
        graph.setColor(0xFF000000);
    }

    @Override
    public void handleInput(ArrayList<TouchEvent> event) {
        super.handleInput(event);

        if(goBack.handleInput(event)){
            log.SetScene(new MainMenu(log));
        }
        if(pokerBG.handleInput(event)){
            if(log.money >= 50 && !log.unlockedBackgrounds[0]) {
                log.currBG = 0;
                log.money -= 50;
                log.unlockedBackgrounds[0] = true;
                moneyText.changeText("Current Money: " + log.money);
                pokerText.changeText("GOT");
                trySaveSkinBackground();
            } else if (log.unlockedBackgrounds[0]) {
                log.currBG = 0;
                trySaveSkinBackground();
            }
        }
        if(emojiBG.handleInput(event)){
            if(log.money >= 50 && !log.unlockedBackgrounds[1]) {
                log.currBG = 1;
                log.money -= 50;
                log.unlockedBackgrounds[1] = true;
                moneyText.changeText("Current Money: " + log.money);
                emojiBGText.changeText("GOT");
                trySaveSkinBackground();
            } else if (log.unlockedBackgrounds[1]) {
                log.currBG = 1;
                trySaveSkinBackground();
            }
        }
        if(turkeyBG.handleInput(event)){
            if(log.money >= 50 && !log.unlockedBackgrounds[2]) {
                log.currBG = 2;
                log.money -= 50;
                log.unlockedBackgrounds[2] = true;
                moneyText.changeText("Current Money: " + log.money);
                turkeyText.changeText("GOT");
                trySaveSkinBackground();
            } else if (log.unlockedBackgrounds[2]) {
                log.currBG = 2;
                trySaveSkinBackground();
            }
        }
        if(santaBG.handleInput(event)){
            if(log.money >= 50 && !log.unlockedBackgrounds[3]) {
                log.currBG = 3;
                log.money -= 50;
                log.unlockedBackgrounds[3] = true;
                moneyText.changeText("Current Money: " + log.money);
                santaText.changeText("GOT");
                trySaveSkinBackground();
            } else if (log.unlockedBackgrounds[3]) {
                log.currBG = 3;
                trySaveSkinBackground();
            }
        }
        if(defaultBG.handleInput(event)){
            log.currBG=4;
            trySaveSkinBackground();
        }
        if(coinSkin.handleInput(event)){
            if(log.money >= 50 && !log.unlockedSkins[0]) {
                log.currSkin = 0;
                log.money -= 50;
                log.unlockedSkins[0] = true;
                moneyText.changeText("Current Money: " + log.money);
                coinText.changeText("GOT");
                trySaveSkinBackground();
            } else if (log.unlockedSkins[0]) {
                log.currSkin = 0;
                trySaveSkinBackground();
            }
        }
        if(emojiSkin.handleInput(event)){
            if(log.money >= 50 && !log.unlockedSkins[1]) {
                log.currSkin = 1;
                log.money -= 50;
                log.unlockedSkins[1] = true;
                moneyText.changeText("Current Money: " + log.money);
                emojiSkinText.changeText("GOT");
                trySaveSkinBackground();
            } else if (log.unlockedSkins[1]) {
                log.currSkin = 1;
                trySaveSkinBackground();
            }
        }
        if(foodSkin.handleInput(event)){
            if(log.money >= 50 && !log.unlockedSkins[2]) {
                log.currSkin = 2;
                log.money -= 50;
                log.unlockedSkins[2] = true;
                moneyText.changeText("Current Money: " + log.money);
                foodText.changeText("GOT");
                trySaveSkinBackground();
            } else if (log.unlockedSkins[2]) {
                log.currSkin = 2;
                trySaveSkinBackground();
            }
        }
        if(xmasSkin.handleInput(event)){
            if(log.money >= 50 && !log.unlockedSkins[3]) {
                log.currSkin = 3;
                log.money -= 50;
                log.unlockedSkins[3] = true;
                moneyText.changeText("Current Money: " + log.money);
                xmasText.changeText("GOT");
                trySaveSkinBackground();
            } else if (log.unlockedSkins[3]) {
                log.currSkin = 3;
                trySaveSkinBackground();
            }
        }
        if(defaultSkin.handleInput(event)){
            log.currSkin=4;
            trySaveSkinBackground();
        }
    }

    private void trySaveSkinBackground() {
        try {
            log.saveGameSkins();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
