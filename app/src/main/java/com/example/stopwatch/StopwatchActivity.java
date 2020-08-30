package com.example.stopwatch;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.TextView;

public class StopwatchActivity extends AppCompatActivity {

    private boolean running;
    private boolean wasRunning;
    private int milli_seconds=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stopwatch);
        if(savedInstanceState!=null)
        {
            milli_seconds=savedInstanceState.getInt("milli_seconds");
            running=savedInstanceState.getBoolean("running");
            wasRunning=savedInstanceState.getBoolean("wasRunning");
        }
        runTimer();
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt("milli_seconds",milli_seconds);
        savedInstanceState.putBoolean("running",running);
        savedInstanceState.putBoolean("wasRunning",wasRunning);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(wasRunning)
        {
         running=true;
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        wasRunning=running;
        running=false;
    }

    public void onClickStart(View view)
    {
        running=true;
    }
    public void onClickStop(View view)
    {
        running=false;
    }
    public void onClickReset(View view)
    {
        running=false;
        milli_seconds=0;
    }
    public void onClickBack(View view)
    {
        finish();
    }
    private void runTimer()
    {
        final TextView timeView=(TextView)findViewById(R.id.time_view);
        final Handler handler=new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int seconds=milli_seconds;
                seconds/=100;
                int secs=(seconds%60);
                int minutes=(seconds%3600)/60;
                int hours=(seconds/3600);
                String time=String.format("%d:%02d:%02d",hours,minutes,secs);
                timeView.setText(time);
                if(running)
                {
                    milli_seconds++;
                }
                handler.postDelayed(this,1);
            }
        });
    }
}