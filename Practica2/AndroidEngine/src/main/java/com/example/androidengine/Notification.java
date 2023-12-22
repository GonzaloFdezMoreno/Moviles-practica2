package com.example.androidengine;

public class Notification {

    String title;
    String description;

    int time;


    public Notification(String title, String sub, int timeToShow) {
        this.title = title;

        this.description = sub;
        this.time = timeToShow;
    }

    public String GetTitle(){
        return this.title;
    }
    public String GetDescription(){
        return this.description;
    }

    public int GetTimeToShow(){
        return this.time;
    }

}
