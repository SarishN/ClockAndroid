package com.example.stopwatch;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import static java.lang.String.valueOf;

public class TimerActivity extends AppCompatActivity implements NumberPicker.OnValueChangeListener {
    int hr, min, sec;
    private boolean running;
    private boolean wasRunning=false;
    private long mill_seconds = 0;
    private long mill_seconds_left = 0;
    CountDownTimer timer;
    TextView timeView;
    NumberPicker secondPicker,minutePicker,hourPicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);
        timeView=(TextView) findViewById(R.id.time_view);
        secondPicker=(NumberPicker)findViewById(R.id.secondPicker);
        minutePicker=(NumberPicker)findViewById(R.id.minutePicker);
        hourPicker=(NumberPicker)findViewById(R.id.hourPicker);
        setValues();
        if (savedInstanceState != null) {
            mill_seconds_left = savedInstanceState.getInt("milli_seconds_left");
            mill_seconds = savedInstanceState.getInt("milli_seconds");
            running = savedInstanceState.getBoolean("running");
            wasRunning = savedInstanceState.getBoolean("wasRunning");
        }
            }
void setValues()
{

    secondPicker.setMinValue(00);
    minutePicker.setMinValue(00);
    hourPicker.setMinValue(00);
    secondPicker.setMaxValue(59);
    minutePicker.setMaxValue(59);
    hourPicker.setMaxValue(24);
    secondPicker.setOnValueChangedListener(this);
    minutePicker.setOnValueChangedListener(this);
    hourPicker.setOnValueChangedListener(this);
}

    void setTimer() {
        /*Spinner hour = (Spinner) findViewById(R.id.spinnerHour);
        Spinner minute = (Spinner) findViewById(R.id.spinnerMinute);
        Spinner second = (Spinner) findViewById(R.id.spinnerSecond);
        hr = Integer.parseInt(valueOf(hour.getSelectedItem()));
        min = Integer.parseInt(valueOf(minute.getSelectedItem()));
        sec = Integer.parseInt(valueOf(second.getSelectedItem()));
        */
        sec=secondPicker.getValue();
        min=minutePicker.getValue();
        hr=hourPicker.getValue();
        mill_seconds = ((hr * 3600) + (min * 60) + (sec)) * 1000;
        mill_seconds_left=mill_seconds;
    }


    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putLong("milli_seconds", mill_seconds);
        savedInstanceState.putLong("milli_seconds_left", mill_seconds_left);
        savedInstanceState.putBoolean("running", running);
        savedInstanceState.putBoolean("wasRunning", wasRunning);
    }
/*
    @Override
    protected void onStart() {
        super.onStart();
        if (wasRunning) {
            running = true;
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        wasRunning = running;
        running = false;
    }
*/
    public void onClickStart(View view) {
        if(!wasRunning) {
            setTimer();
        }
        running = true;
        runTimer();
    }

    public void onClickStop(View view)
    {
        if(running) {
            timer.cancel();
            wasRunning = true;
            running = false;
        }
    }

    public void onClickReset(View view) {
        if(!running) {
            wasRunning = true;
        }
        else
        {
            timer.cancel();
        }
        setTimer();
        setTime(mill_seconds_left);
        running=false;
    }

    public void onClickBack(View view) {
        finish();
    }

    public void runTimer() {
        if(running)
    setTime(mill_seconds);
    wasRunning=true;
    timer = new CountDownTimer(mill_seconds_left, 1000) {
        @Override
        public void onTick(long l) {
            mill_seconds_left = l;
            setTime(l);
        }

        @Override
        public void onFinish() {
            running=false;
            Toast.makeText(getApplicationContext(), "Time Up", Toast.LENGTH_LONG).show();
        }
    }.start();
}


    private void setTime(long l) {
        l/=1000;
        int hours=(int)l/3600;
        l-=(hours*3600);
        int minutes=(int)l/60;
        l-=(minutes*60);
        int secs=(int)l;
        String time=String.format("%d:%02d:%02d",hours,minutes,secs);
        timeView.setText(time);
    }

    @Override
    public void onValueChange(NumberPicker numberPicker, int i, int i1) {
    }
}