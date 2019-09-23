package com.commonsense;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity  {
    TextView txt;
    Typeface tfe;
    Thread myThread;
    private Runnable mRunnable;
    LinearLayout mLinearSplash;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        txt = findViewById(R.id.splashtext);
        Animation aniFade = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in);
        txt.startAnimation(aniFade);
        myThread = new Thread(){
            @Override
            public void run(){
                try{

                    synchronized (this){
                        wait(3000);
                    }


                } catch (InterruptedException e){
                    e.printStackTrace();
                }

                Intent i = new Intent(SplashActivity.this,OnBoardingActivity.class);
                startActivity(i);
                overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
                finish();
            }

        };
        myThread.start();

//        Handler handler = new Handler();
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//
//                Intent i = new Intent(SplashActivity.this, OnBoardingActivity.class);
//                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                startActivity(i);
//                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
//                finish();
//            }
//        }, 3500);



    }
    @Override
    public boolean onTouchEvent(MotionEvent evt){
        if(evt.getAction() == MotionEvent.ACTION_DOWN)
        {
            synchronized (myThread){
                myThread.notifyAll();
            }
        }
        return true;
    }
}
