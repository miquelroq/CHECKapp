package com.example.alarm_doc;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.example.alarm_doc.services.BitalinoCapture;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * Process signals in order to extract the most relevant features to be sent to the API
 * Send to the API
 * GET the response
 * Launch activity according to the response/outcome
 *
 *
 */

// TODO:
//      * Send the collected data to Django API
//      * Wait for the response
//      * Launch Healthy/Unhealthy activity according to the received response
public class DataProcessing extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_processing);

        // When we receive the collected data, send it to the API
        LocalBroadcastManager.getInstance(DataProcessing.this).registerReceiver(
                new BroadcastReceiver() {
                    @Override
                    public void onReceive(Context context, Intent intent) {

                        HashMap<Integer, ArrayList<Integer>> values = (HashMap<Integer, ArrayList<Integer>>) intent.getExtras().get(BitalinoCapture.ACTION_CAPTURE);
                        // Send
                        Toast.makeText(context, "Ola, recebi os dados", Toast.LENGTH_SHORT).show();

                    }
                }, new IntentFilter(BitalinoCapture.ACTION_CAPTURE)
        );
    }
}