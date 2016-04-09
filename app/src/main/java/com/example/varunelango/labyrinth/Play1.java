package com.example.varunelango.labyrinth;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Handler;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;


public class Play1 extends Activity {
    BallView mBallView = null;
    Handler RedrawHandler = new Handler();
    Timer mTmr = null;
    TimerTask mTsk = null;
    int mScrWidth, mScrHeight;
    android.graphics.PointF mBallPos, mBallSpd;
    Bitmap back;
    public Bundle bundle = new Bundle();
    String data;




    @Override
    protected void onCreate(Bundle savedInstanceState) {

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(0xFFFFFFFF, WindowManager.LayoutParams.FLAG_FULLSCREEN | WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play1);

        final FrameLayout mainView =
                (FrameLayout) findViewById(R.id.main_view);


        Display display = getWindowManager().getDefaultDisplay();
        mScrWidth = display.getWidth();
        mScrHeight = display.getHeight();
        mBallPos = new android.graphics.PointF();
        mBallSpd = new android.graphics.PointF();
        back = BitmapFactory.decodeResource(getResources(),R.drawable.gamebackground1);

        mBallPos.x = 0;
        mBallPos.y = 0;
        mBallSpd.x = 0;
        mBallSpd.y = 0;

        mBallView = new BallView(this, mBallPos.x, mBallPos.y, 40,back);

        mainView.addView(mBallView);
        mBallView.invalidate();

        ((SensorManager) getSystemService(Context.SENSOR_SERVICE)).registerListener(
                new SensorEventListener() {
                    @Override
                    public void onSensorChanged(SensorEvent event) {
                        mBallSpd.x = event.values[0];
                        mBallSpd.y = event.values[1];
                    }

                    @Override
                    public void onAccuracyChanged(Sensor sensor, int accuracy) {
                    }
                },
                ((SensorManager) getSystemService(Context.SENSOR_SERVICE))
                        .getSensorList(Sensor.TYPE_ACCELEROMETER).get(0),
                SensorManager.SENSOR_DELAY_NORMAL);



    }
    @Override
    public void onPause()
    {
        mTmr.cancel();
        mTmr = null;
        mTsk = null;
        super.onPause();
    }

    @Override
    public void onResume()
    {
        mTmr = new Timer();
        mTsk = new TimerTask() {
            public void run() {


                mBallPos.y += mBallSpd.x;
                mBallPos.x += mBallSpd.y;

                if (mBallPos.y > mScrHeight-150) mBallPos.y=mScrHeight-150;
                if (mBallPos.x > mScrWidth-150) mBallPos.x=mScrWidth-150;

                if (mBallPos.y < 50) mBallPos.y=50;
                if (mBallPos.x < 50) mBallPos.x=50;



                if(back.getPixel((int)(mBallPos.x+50),(int)(mBallPos.y))==Color.BLACK)startIntentLost();
                if(back.getPixel((int)(mBallPos.x-50),(int)(mBallPos.y))==Color.BLACK)startIntentLost();
                if(back.getPixel((int)(mBallPos.x),(int)(mBallPos.y-50))==Color.BLACK)startIntentLost();
                if(back.getPixel((int)(mBallPos.x),(int)(mBallPos.y+50))==Color.BLACK)startIntentLost();

                if(back.getPixel((int)(mBallPos.x+50),(int)(mBallPos.y))==Color.WHITE)startIntentWon();
                if(back.getPixel((int)(mBallPos.x-50),(int)(mBallPos.y))==Color.WHITE)startIntentWon();
                if(back.getPixel((int)(mBallPos.x),(int)(mBallPos.y-50))==Color.WHITE)startIntentWon();
                if(back.getPixel((int)(mBallPos.x),(int)(mBallPos.y+50))==Color.WHITE)startIntentWon();





                mBallView.y = mBallPos.y;
                mBallView.x = mBallPos.x;


                RedrawHandler.post(new Runnable() {
                    public void run() {
                        mBallView.invalidate();
                    }});

            }


        };
        mTmr.schedule(mTsk,10,10);
        super.onResume();
    }

    private void startIntentWon() {
        data="You Won";
        Intent i=new Intent(this,WinActivity.class);
        bundle.putString("Data",data);
        i.putExtra("Bundle",bundle);
        startActivity(i);
    }

    private void startIntentLost() {
        data="You Lost";
        Intent i=new Intent(this,WinActivity.class);
        bundle.putString("Data",data);
        i.putExtra("Bundle",bundle);
        startActivity(i);
    }


    @Override
    public void onDestroy()
    {
        super.onDestroy();
        System.runFinalizersOnExit(true);
        android.os.Process.killProcess(android.os.Process.myPid());
    }


}
