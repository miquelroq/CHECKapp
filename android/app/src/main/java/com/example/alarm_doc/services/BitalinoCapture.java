package com.example.alarm_doc.services;

import android.app.Service;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.ParcelUuid;
import android.util.Log;

import androidx.annotation.RequiresApi;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.bitalino.comm.BITalinoDevice;
import com.bitalino.comm.BITalinoException;
import com.bitalino.comm.BITalinoFrame;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;


public class BitalinoCapture extends Service {

    private BITalinoDevice bitalino;
    private static final String remoteDevice = "98:D3:C1:FD:2F:E5";
    public OutputStreamWriter fout = null;
    private boolean isRunning = false;
    private android.os.Handler Handler = null;
    private Runnable runnableCode = null;
    public static final String ACTION_CAPTURE = "capture";
    private BitalinoCapture data_instance = this;
    private int second = 0;
    private HashMap<Integer, ArrayList<Integer>> collectedData = new HashMap<>();

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    @Override
    public void onCreate() {
        super.onCreate();

        isRunning = true;
        boolean error = false;
        BluetoothSocket sock = null;
        BluetoothDevice dev = null;
        HandlerThread thread = new HandlerThread("SensorsDataAcquisition - Bitalino 1", android.os.Process.THREAD_PRIORITY_BACKGROUND);
        thread.start();
        Looper serviceLooper = thread.getLooper();
        this.Handler = new Handler(serviceLooper);

        collectedData.put(0, new ArrayList<Integer>());
        collectedData.put(1, new ArrayList<Integer>());
        collectedData.put(2, new ArrayList<Integer>());
        collectedData.put(3, new ArrayList<Integer>());
        collectedData.put(4, new ArrayList<Integer>());
        collectedData.put(5, new ArrayList<Integer>());


        try {
            final BluetoothManager bluetoothManager = (BluetoothManager) getSystemService(Context.BLUETOOTH_SERVICE);
            assert bluetoothManager != null;
            dev = bluetoothManager.getAdapter().getRemoteDevice(remoteDevice);
            if(dev != null) {
                ParcelUuid[] list = dev.getUuids();
                sock = dev.createRfcommSocketToServiceRecord(list[0].getUuid());
            } else {
                error = true;
            }

        } catch (IOException e) {
            error = true;
        }

        if(!error) {

            try {
                sock.connect();
            } catch (IOException e) {
                try {
                    //noinspection JavaReflectionMemberAccess
                    sock = (BluetoothSocket) dev.getClass().getMethod("createRfcommSocket", new Class[]{int.class}).invoke(dev, 1);
                    assert sock != null;
                    sock.connect();
                } catch (Exception e2) {
                    error = true;
                }
            }

            if(!error) {

                try {
                    bitalino = new BITalinoDevice(100, new int[]{0, 1, 2, 3, 4, 5});
                    bitalino.open(sock.getInputStream(), sock.getOutputStream());
                    bitalino.start();

                    this.runnableCode = new Runnable() {
                        @Override
                        public void run() {

                            try {
                                getBitalinoData();
                            } catch (BITalinoException e) {
                                e.printStackTrace();
                                Log.e("ERROR", "Error reading bitalino");
                            }
                        }

                    };

                    this.Handler.post(runnableCode);

                } catch (IOException | BITalinoException e) {
                    e.printStackTrace();
                }

            }

        }

    }

    public void getBitalinoData() throws BITalinoException {
        final int numberOfSamplesToRead = 100;
        // Repeat this the same runnable code block again another 1000 milliseconds
        if(second < 10) {
            Handler.postDelayed(runnableCode, 100);
            BITalinoFrame[] frames = bitalino.read(numberOfSamplesToRead);
            // prepare reading for upload
            for (BITalinoFrame myBitFrame : frames) {
                String line = Integer.valueOf(myBitFrame.getSequence()).toString();

                this.collectedData.get(0).add(myBitFrame.getAnalog(0));
                this.collectedData.get(1).add(myBitFrame.getAnalog(1));
                this.collectedData.get(2).add(myBitFrame.getAnalog(2));
                this.collectedData.get(3).add(myBitFrame.getAnalog(3));
                this.collectedData.get(4).add(myBitFrame.getAnalog(4));
                this.collectedData.get(5).add(myBitFrame.getAnalog(5));

                line += "\t" + myBitFrame.getAnalog(0);
                line += "\t" + myBitFrame.getAnalog(1);
                line += "\t" + myBitFrame.getAnalog(2);
                line += "\t" + myBitFrame.getAnalog(3);
                line += "\t" + myBitFrame.getAnalog(4);
                line += "\t" + myBitFrame.getAnalog(5);
                line += "\n";

                Log.d("FIXE", line);
            }

            ++second;
        } else {

            // Kill this service and broadcast the results back to checkup
            this.stopSelf();
            sendBroadcastMessage(this.collectedData);
            Log.d("stopBitalino", "stop");

        }
    }

    @Override
    public void onDestroy() {
        isRunning = false;

        try {
            if (bitalino != null) {
                bitalino.stop();
            }
            stopSelf();
        } catch (BITalinoException e) {
            e.printStackTrace();
        }
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return Service.START_STICKY;
    }

    private void sendBroadcastMessage(HashMap<Integer, ArrayList<Integer>> hm) {
        Intent intent = new Intent(ACTION_CAPTURE);
        intent.putExtra(ACTION_CAPTURE, hm);
        LocalBroadcastManager.getInstance(data_instance).sendBroadcast(intent);
    }

}
