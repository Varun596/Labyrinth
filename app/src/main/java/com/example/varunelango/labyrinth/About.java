package com.example.varunelango.labyrinth;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;


public class About extends Activity {

    TextView about;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(0xFFFFFFFF, WindowManager.LayoutParams.FLAG_FULLSCREEN | WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        about = (TextView)findViewById(R.id.AboutText);
        about.setText("Labyrinth is a game where the player has to tilt the mobile to move a ball through the maze without touching it.");

    }

    public void onClickBack(View view){

        Intent i=new Intent(this,MainActivity.class);
        startActivity(i);
        finish();
    }

}