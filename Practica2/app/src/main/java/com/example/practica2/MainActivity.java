package com.example.practica2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    SurfaceView sfv;
    AndroidEngine andrEng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        this.sfv = new SurfaceView(this);
        setContentView(this.sfv);


        andrEng=new AndroidEngine(this.sfv,this);

        CLogic logic = new Logic(andrEng);

        this.andrEng.SetLogic(logic);

    }
    @Override
    protected void onResume() {
        super.onResume();
        andrEng.resume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        andrEng.pause();
    }
}