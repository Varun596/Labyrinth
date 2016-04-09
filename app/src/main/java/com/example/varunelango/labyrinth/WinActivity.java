package com.example.varunelango.labyrinth;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

public class WinActivity extends Activity {
    String TextData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_win);

        TextView win = (TextView)findViewById(R.id.WinText);

        Bundle WinData = getIntent().getBundleExtra("Bundle");
        if(WinData==null){
            return;
        }
        TextData = WinData.getString("Data");
        win.setText(TextData);

    }

    public void onClickRetry(View view){

        Intent i=new Intent(this,Play1.class);
        startActivity(i);

    }



    public void onClickBack(View view){

        Intent i=new Intent(this,MainActivity.class);
        startActivity(i);
    }


}
