package com.example.alarm_doc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

public class Checkup extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkup);

        double test_outcome = Math.random();
        final Intent intent;
        int fatigue, chills;
        Timer timer = new Timer();
        final TextView tv = (TextView) findViewById(R.id.number);


        LocalBroadcastManager.getInstance(this).registerReceiver(
                new BroadcastReceiver() {
                    @Override
                    public void onReceive(Context context, Intent intent) {
                        int randomNr = intent.getIntExtra(DataGenerator.EXTRA_RANDOM, 0);
                        tv.setText(Integer.toString(randomNr));
                    }
                }, new IntentFilter(DataGenerator.ACTION_DATAGEN_BROADCAST)
        );

        // Collect the received dictionary
        fatigue = getIntent().getIntExtra("fatigue", 0);
        chills = getIntent().getIntExtra("chills", 0);

        // TODO:
        //      * Connect to BITalino via Bluetooth
        //      * Collect BITalino data and store it in a HashMap or ArrayLists of ints
        //      * Send the collected data to Django API
        //      * Wait for the response
        //      * Launch Healthy/Unhealthy activity according to the received response

        // Starting Datagen in the background when this activity is created
        Intent service = new Intent(this, DataGenerator.class);
        startService(service);


    }
}