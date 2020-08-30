package com.example.stopwatch;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class ClockActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clock);

    }
    public void onClickStopwatch(View view)
    {
        Intent intent=new Intent(this,StopwatchActivity.class);
        startActivity(intent);
    }
    public void onClickTimer(View view)
    {
        Intent intent=new Intent(this,TimerActivity.class);
        startActivity(intent);
    }
}