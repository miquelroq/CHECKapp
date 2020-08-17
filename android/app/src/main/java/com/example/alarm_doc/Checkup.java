package com.example.alarm_doc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

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
        final TextView tv = (TextView) findViewById(R.id.number);

        LocalBroadcastManager.getInstance(this).registerReceiver(
                new BroadcastReceiver() {
                    @Override
                    public void onReceive(Context context, Intent intent) {
                        int randomNr = intent.getIntExtra(DataGenerator.EXTRA_RANDOM, 0);
                        System.out.println(randomNr);
                        tv.setText(Integer.toString(randomNr));
                    }
                }, new IntentFilter(DataGenerator.ACTION_DATAGEN_BROADCAST)
        );

        // Starting Datagen in the background when this activity is created
        Intent service = new Intent(this, DataGenerator.class);
        startService(service);

    }
}