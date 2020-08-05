package com.example.alarm_doc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

public class Checkup extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkup);

        double test_outcome = Math.random();
        final Intent intent;
        Timer timer = new Timer();

        if (test_outcome > 0.5) {
            // If user is healthy
            // Launch healthy activity
            intent = new Intent(getApplicationContext(), Healthy.class);

        } else {
            // If user is not healthy
            // Launch worried activity
            intent = new Intent(getApplicationContext(), Unhealthy.class);

        }

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                startActivity(intent);
            }
        }, 3000);

    }
}