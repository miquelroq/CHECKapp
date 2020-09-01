package com.example.alarm_doc;

import android.app.Activity;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Process;


import androidx.annotation.Nullable;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.example.alarm_doc.domain.Register;
import com.example.alarm_doc.utils.Utils;

import java.util.TimerTask;

import static android.app.PendingIntent.getActivity;
import static androidx.core.app.ShareCompat.getCallingActivity;

public class DataGenerator extends Service {

    private Looper serviceLooper;
    private ServiceHandler serviceHandler;
    public static final String
            ACTION_DATAGEN_BROADCAST = DataGenerator.class.getName() + "DataGenerator",
            EXTRA_RANDOM = "extra_random";
    public DataGenerator data_instance = this;
    public Utils utils = new Utils();
    public Activity launcher;

    // Handler that receives messages from the thread
    private final class ServiceHandler extends Handler {
        public ServiceHandler(Looper looper) {
            super(looper);
        }

        public void handleMessage(Message msg) {
            // Generate data
            try {
                Intent intent;
                for (int i = 1; i < 101; i++) {
                    sendBroadcastMessage(i);
                    Thread.sleep(4);
                }
            } catch (InterruptedException e) {
                // Restore interrupt status.
                Thread.currentThread().interrupt();
            }

            // Launch a new activity from this service.
            // Since we need to wait for it to be concluded to start it,
            // maybe it is best to launch it here rather than use IPC
            Intent outcome = new Intent(getApplicationContext(), Healthy.class);
            outcome.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(outcome);

            // Stop the service using the startId, so that we don't stop
            // the service in the middle of handling another job
            stopSelf(msg.arg1);
        }
    }

    @Override
    public void onCreate() {
        // Start up the thread running the service. Note that we create a
        // separate thread because the service normally runs in the process's
        // main thread, which we don't want to block. We also make it
        // background priority so CPU-intensive work doesn't disrupt our UI.
        HandlerThread thread = new HandlerThread("ServiceStartArguments",
                Process.THREAD_PRIORITY_BACKGROUND);
        thread.start();

        // Get the HandlerThread's Looper and use it for our Handler
        serviceLooper = thread.getLooper();
        serviceHandler = new ServiceHandler(serviceLooper);


    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this, "Generating random data", Toast.LENGTH_SHORT).show();

        // For each start request, send a message to start a job and deliver the
        // start ID so we know which request we're stopping when we finish the job
        Message msg = serviceHandler.obtainMessage();
        msg.arg1 = startId;
        serviceHandler.sendMessage(msg);

        // If we get killed, after returning from here, restart
        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        // We don't provide binding, so return null
        return null;
    }

    @Override
    public void onDestroy() {
        Toast.makeText(this, "Service concluded", Toast.LENGTH_SHORT).show();
    }

    private void sendBroadcastMessage(int i) {
        Intent intent = new Intent(ACTION_DATAGEN_BROADCAST);
        intent.putExtra(EXTRA_RANDOM, i);
        LocalBroadcastManager.getInstance(data_instance).sendBroadcast(intent);
    }

    public void setActivity(Activity a) {
        this.launcher = a;
    }

}
